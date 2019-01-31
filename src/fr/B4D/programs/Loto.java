package fr.B4D.programs;

import java.awt.AWTException;
import java.awt.Image;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import fr.B4D.bot.Person;
import fr.B4D.google.sheet.SheetsServiceUtil;
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
	
	private static String range = "B20:B1000";
	
	public static ProgramInterface loto = new ProgramInterface() {

		private Sheets sheets;
		private String id = "1g9SQS-HXscoK9jv-2hdiA38adiv5n5BJ38a_E-QnNiw";
		
		public void intro(Person person) throws IOException, GeneralSecurityException, CancelProgramException {
			sheets = SheetsServiceUtil.getSheetsService();
			
			int response = JOptionPane.showConfirmDialog(null, "Voulez vous créer un nouveau tirage ?\n- Oui : Je créer un nouveau tirage\n- Non : Je saisie l'url d'un tirage existant", "Tirage", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(response == JOptionPane.CANCEL_OPTION)
				throw new CancelProgramException();
//			else if (response == JOptionPane.YES_OPTION) {
//				//Créer un nouveau google sheet à partir d'une copie du l'original
//				//Rempli les champs
//			}
			else {
				String url = (String)JOptionPane.showInputDialog(null, "Saisissez l'url du google sheet.", null);
				if(url == null)
					throw new CancelProgramException();
				System.out.println(url);
				id = SheetsServiceUtil.getIdFromUrl(url);
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
				
				Image image = exchange.exchange(validationMessage + sort + " ? (" + formatter.format(date) + ")");
				message.reply(tkanksMessage);
				
				List<String> ranges = Arrays.asList(range);
				BatchGetValuesResponse readResult = sheets.spreadsheets().values()
						.batchGet(id)
						.setRanges(ranges)
						.execute();
				int nb = 20 + readResult.getValueRanges().get(0).getValues().size();
				
				ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays.asList(name, sort)));
				sheets.spreadsheets().values()
						.update(id, "B"+nb, body)
						.setValueInputOption("RAW")
						.execute();
				
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
