package fr.B4D.programs;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.google.api.services.drive.model.File;

import fr.B4D.bot.Person;
import fr.B4D.google.GoogleDrive;
import fr.B4D.google.GoogleSheet;
import fr.B4D.interaction.B4DExchangeCanceled;
import fr.B4D.interaction.Exchange;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.ProgramInterface;
import net.sourceforge.tess4j.TesseractException;

public final class Loto {
	private static int ticketPrice = 1;
	private static String welcomeMessage = "Bonjour, pour avoir plus d'infos sur le règlement de la roulette merci de consulter ce lien : https://urlz.fr/8g6H";
	private static String questionMessage = "Sur quel sort veux-tu parrier ?";
	private static String exchangeMessage = "Le prix du pari est actuellement de " + ticketPrice + " kamas. Il suffit de valider pour acheter un ticket. Pour éviter toute triche, il n'est pas possible d'acheter plusieurs tickets.";
	
	private static long timeout = 30000;
	
	private static String validationMessage = "Tu confirmes parier pour le prix de " + ticketPrice + " kamas sur le sort ";
	private static String tkanksMessage = "Merci d'avoir parié ! :) Tu peux consulter le tirage en cours et les tirages archivés via ce lien : https://urlz.fr/4EHx";
	
	private static final String DRIVE_ID = "1HLj2cvMY3FO1XOlNJo1KUCamKRlfyaQj";
	private static final String SHEET_ID = "1g9SQS-HXscoK9jv-2hdiA38adiv5n5BJ38a_E-QnNiw";
	
	private static String rangeTitle = "A1";
	private static String rangeDate = "A11";
	private static String rangeHour = "B11";
	private static String rangePosition = "C11";
	private static String rangePrice = "A17";
	private static String rangeOrganizer = "B17";
	private static String rangeData = "B20:B1000";
	
	public static ProgramInterface loto = new ProgramInterface() {

		private GoogleDrive drive;
		private GoogleSheet sheet;
		
		public void intro(Person person) throws IOException, GeneralSecurityException, CancelProgramException {			
			int response = JOptionPane.showConfirmDialog(null, "Voulez vous créer un nouveau tirage ?\n- Oui : Je créer un nouveau tirage\n- Non : Je saisie l'url d'un tirage existant", "Tirage", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(response == JOptionPane.CANCEL_OPTION)
				throw new CancelProgramException();
			else if (response == JOptionPane.YES_OPTION) {
				String number = (String)JOptionPane.showInputDialog(null, "Numéro du tirage :", null);
				String date = (String)JOptionPane.showInputDialog(null, "Date :", null);
				String hour = (String)JOptionPane.showInputDialog(null, "Heure :", null);
				String position = (String)JOptionPane.showInputDialog(null, "Position :", null);
				String price = (String)JOptionPane.showInputDialog(null, "Prix du ticket :", null);
				
				if(number == null || date == null || hour == null || position == null)
					throw new CancelProgramException();
				
				drive = new GoogleDrive(DRIVE_ID);
				File file = drive.copyFile(SHEET_ID, "Tirage du " + date);
				sheet = new GoogleSheet(file.getId());
				sheet.write(Arrays.asList(Arrays.asList("Tirage n°" + number + " - En cours")), rangeTitle);
				sheet.write(Arrays.asList(Arrays.asList(date)), rangeDate);
				sheet.write(Arrays.asList(Arrays.asList(hour)), rangeHour);
				sheet.write(Arrays.asList(Arrays.asList(position)), rangePosition);
				sheet.write(Arrays.asList(Arrays.asList(price)), rangePrice);
				sheet.write(Arrays.asList(Arrays.asList(person.getPseudo())), rangeOrganizer);
			}
			else {
				String url = (String)JOptionPane.showInputDialog(null, "Url du google sheet :", null);
				if(url == null)
					throw new CancelProgramException();
				sheet = new GoogleSheet(GoogleSheet.getIdFromUrl(url));
			}
		}
		
		public void cycle(Person person) throws AWTException, IOException, TesseractException, InterruptedException {			
			try {
				Exchange exchange = new Exchange(ticketPrice, 0);
				String name = exchange.waitForExchange();
				Message message = new Message(name, Channel.Private, welcomeMessage);
				message.send();
				
				String sort;
				do {
					message.reply(questionMessage);
					message = message.waitForReply(timeout);
					
					if(message == null)
						exchange.cancelExchange();
					
					sort = parseSort(message.getText());
				}while(sort == null);

				message.reply(exchangeMessage);
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:HH dd/MM/yyyy");
				LocalDateTime date = LocalDateTime.now();
				
				BufferedImage image = exchange.exchange(validationMessage + sort + " ? (" + formatter.format(date) + ")");
				message.reply(tkanksMessage);
				
				java.io.File file = java.io.File.createTempFile("b4d-temp", "png");
			    ImageIO.write(image, "png", file);
				drive.uploadFile("image/png", name, file);
				
				List<List<Object>> result = sheet.read(rangeData);
				int nb = 20 + result.size();
				
				List<List<Object>> content = Arrays.asList(Arrays.asList(name, sort));
				sheet.write(content, "B"+nb);
				
			} catch (B4DExchangeCanceled e) {
				
			}
		}

		public void outro(Person person) {
			//Do nothing for the moment
		}
		
		private String parseSort(String message) {
			return "Roulette";
		}
	};
}
