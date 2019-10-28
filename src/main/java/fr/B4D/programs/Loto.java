package fr.B4D.programs;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.google.api.services.drive.model.File;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.google.GoogleDrive;
import fr.B4D.google.GoogleSheet;
import fr.B4D.interaction.Exchange;
import fr.B4D.interaction.ExchangeCanceledException;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.ClosestString;

public final class Loto {
	private static final String CREDENTIALS = "loto.credentials";
	private static final String PROPERTIES = "loto.properties";
	
	private static final String privateFolderIdFieldName = "privateFolderId";
	private static final String sharedFolderIdFieldName = "sharedFolderId";
	private static final String modelSheetIdFieldName = "modelSheetId";
	
	private static final String ticketPriceFieldName = "ticketPrice";
	private static final String timeoutFieldName = "timeout";
	
	private static final String welcomeMessageFieldName = "welcomeMessage";
	private static final String questionMessageFieldName = "questionMessage";
	private static final String exchangeMessageFieldName = "exchangeMessage";
	private static final String validationMessageFieldName = "validationMessage";
	private static final String thanksMessageFieldName = "thanksMessage";

	private static final String rangeTitleFieldName = "rangeTitle";
	private static final String rangeDateFieldName = "rangeDate";
	private static final String rangeHourFieldName = "rangeHour";
	private static final String rangePositionFieldName = "rangePosition";
	private static final String rangePriceFieldName = "rangePrice";
	private static final String rangeOrganiserFieldName = "rangeOrganiser";
	private static final String rangeDataFieldName = "rangeData";
	
	private static final String[] PROPERTY_FIELDS = {privateFolderIdFieldName, sharedFolderIdFieldName, modelSheetIdFieldName, ticketPriceFieldName, timeoutFieldName,	welcomeMessageFieldName, questionMessageFieldName, exchangeMessageFieldName, validationMessageFieldName, thanksMessageFieldName, rangeTitleFieldName, rangeDateFieldName, rangeHourFieldName, rangePositionFieldName, rangePriceFieldName, rangeOrganiserFieldName, rangeDataFieldName};

	public final static Program LOTO = new Program(Place.Astrub, Category.Jeux, "Argent", "Loto", new Channel[] {Channel.GENERAL, Channel.PRIVATE}, Status.AVAILABLE, new ProgramInterface() {

		private Properties properties;
		private GoogleDrive drive;
		private GoogleSheet sheet;
		private File imageFolder;

		public void intro(Person person) throws CancelProgramException {	

			properties = loadProperties();
			
			try {
				int response = JOptionPane.showConfirmDialog(null, "Voulez vous créer un nouveau tirage ?\n- Oui : Je créer un nouveau tirage\n- Non : Reprendre le tirage en cours", "Tirage", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.CANCEL_OPTION)
					throw new CancelProgramException("Vous avez annulé le programme.");
				try {
					drive = new GoogleDrive(properties.getProperty(privateFolderIdFieldName), CREDENTIALS);
				}catch (Exception e) {
					throw new CancelProgramException("Fichier de certificat \"" + CREDENTIALS + "\" manquant ou érroné.");
				}

				if (response == JOptionPane.YES_OPTION) {

					JTextField numberField = new JTextField();
					JTextField dateField = new JTextField();
					JTextField hourField = new JTextField();
					JTextField positionField = new JTextField();
					JTextField priceField = new JTextField();
					JTextField organiserField = new JTextField();
					Object[] message = {
							"Numéro du tirage :", numberField,
							"Date :", dateField,
							"Heure :", hourField,
							"Position :", positionField,
							"Prix du ticket :", priceField,
							"Organiser :", organiserField,
					};
					int option = JOptionPane.showConfirmDialog(null, message, "Merci de renseigner les champs suivants :", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.CANCEL_OPTION)
						throw new CancelProgramException("Vous avez annulé le programme.");

					String number = numberField.getText();
					String date = dateField.getText();
					String hour = hourField.getText();
					String position = positionField.getText();
					String price = priceField.getText();
					String organiser = priceField.getText();

					if(number == null || date == null || hour == null || position == null)
						throw new CancelProgramException("Tous les champs doivent être remplis.");

					String title = "Tirage n°" + number + " - En cours";

					//Create the image folder
					imageFolder = drive.createFolder("Tirage n°" + number + " - Images");

					//Copy the original sheet and moved it
					File file = drive.copyFile(properties.getProperty(modelSheetIdFieldName), title);
					file = drive.moveFile(file.getId(), properties.getProperty(sharedFolderIdFieldName));

					//Get the sheet
					sheet = new GoogleSheet(file.getId(), CREDENTIALS);
					sheet.write(Arrays.asList(Arrays.asList(title)), properties.getProperty(rangeTitleFieldName));
					sheet.write(Arrays.asList(Arrays.asList(date)), properties.getProperty(rangeDateFieldName));
					sheet.write(Arrays.asList(Arrays.asList(hour)), properties.getProperty(rangeHourFieldName));
					sheet.write(Arrays.asList(Arrays.asList(position)), properties.getProperty(rangePositionFieldName));
					sheet.write(Arrays.asList(Arrays.asList(price)), properties.getProperty(rangePriceFieldName));
					sheet.write(Arrays.asList(Arrays.asList(organiser)), properties.getProperty(rangeOrganiserFieldName));
				}
				else {
					//Get the image folder
					File imageFolder = drive.listFolders().stream().filter(f -> f.getName().contains("Tirage")).findFirst().orElse(null);
					if(imageFolder == null)
						throw new CancelProgramException("Impossible de trouver le dossier image.");

					//Search the sheet
					drive.stepInto(properties.getProperty(sharedFolderIdFieldName));
					File sheetFile = drive.listFiles().stream().filter(f -> f.getName().contains("Tirage")).findFirst().orElse(null);
					if(sheetFile == null)
						throw new CancelProgramException("Impossible de trouver le google sheet du tirage en cours.");

					//Get the sheet
					sheet = new GoogleSheet(sheetFile.getId(), CREDENTIALS);
					drive.stepBack();
				}
			}catch (GeneralSecurityException e) {
				throw new CancelProgramException("Certificat érroné.");
			}catch (IOException e) {
				throw new CancelProgramException("Cannot create, write, read, copy or move a file.");
			}
		}

		public void cycle(Person person) throws StopProgramException, NumberFormatException, CancelProgramException, B4DException {			
			try {
				Exchange exchange = new Exchange(Integer.parseInt(properties.getProperty(ticketPriceFieldName)), 0);
				String name = exchange.waitForExchange();
				Message message = new Message(name, properties.getProperty(welcomeMessageFieldName));
				message.send();

				String sort;
				do {
					message.reply(properties.getProperty(questionMessageFieldName));
					message = message.waitForReply(Long.parseLong(properties.getProperty(timeoutFieldName)));

					if(message == null)
						exchange.cancelExchange();

					sort = parseSort(message.getText());
				}while(sort == null);

				message.reply(String.format(properties.getProperty(exchangeMessageFieldName), properties.getProperty(ticketPriceFieldName)));

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:HH dd/MM/yyyy");
				LocalDateTime date = LocalDateTime.now();

				BufferedImage image = exchange.exchange(String.format(properties.getProperty(validationMessageFieldName), properties.getProperty(ticketPriceFieldName), sort, formatter.format(date)));
				message.reply(properties.getProperty(thanksMessageFieldName));

				java.io.File file = java.io.File.createTempFile("b4d-temp", "png");
				ImageIO.write(image, "png", file);
				
				drive.stepInto(imageFolder.getId());
				drive.uploadFile("image/png", name, file);
				drive.stepBack();
				
				// OR
				//File uploadedImage = drive.uploadFile("image/png", name, file);
				//drive.moveFile(uploadedImage.getId(), imageFolder.getId());

				List<List<Object>> result = sheet.read(rangeDataFieldName);
				int nb = 20;
				if(result != null)
					nb += result.size();

				List<List<Object>> content = Arrays.asList(Arrays.asList(name, sort));
				sheet.write(content, "B"+nb);

			} catch (ExchangeCanceledException e) {
				//Do nothing
			}catch (IOException e) {
				throw new CancelProgramException("Cannot create, write, read, copy or move a file.");
			}
		}

		public void outro(Person person) {
			//Do nothing for the moment
		}

		private String parseSort(String message) {
			ClosestString loto = new ClosestString("2 PA", "2 PM", "5 PO", "50% CC", "50 Domages", "50 Fuite", "-50 Fuite", "50 Tacle", "-50 Tacle", "50 Retrait PA", "50 Retrait PM", "100 Domage poussée", "100 Soins", "400 Agilité", "400 Chance", "400 Force", "400 Intelligence");
			return loto.getClosest(message);
		}
		
		private Properties loadProperties() throws CancelProgramException {
			Properties properties = new Properties();
			try {
				InputStream input = new FileInputStream(PROPERTIES); //load a properties file
				properties.load(input);
				Set<Object> fields = properties.keySet();
				
				for(String field:PROPERTY_FIELDS){
					if(!fields.contains(field))
						throw new CancelProgramException("Le fichier \"" + PROPERTIES + "\" doit contenir le champs \"" + field + "\"");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return properties;
		}
	});
}
