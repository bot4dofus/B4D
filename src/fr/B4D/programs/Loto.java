package fr.B4D.programs;

import java.awt.AWTException;
import java.awt.Image;
import java.io.IOException;

import fr.B4D.bot.Person;
import fr.B4D.interaction.B4DExchangeCanceled;
import fr.B4D.interaction.Exchange;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.ProgramInterface;
import net.sourceforge.tess4j.TesseractException;

public final class Loto {
	private static int ticketPrice = 1;
	private static String welcomeMessage = "Bonjour, pour avoir plus d'infos sur le règlement de la roulette merci de consulter ce lien : https://urlz.fr/8g6H.";
	private static String questionMessage = "Sur quel sort veux-tu voter ?";
	private static String exchangeMessage = "Le prix du pari est actuellement de " + ticketPrice + " kamas. Il suffit de valider pour acheter un ticket. Pour éviter toute triche, il n'est pas possible d'acheter plusieurs tickets.";
	
	private static int timeout = 200000;
	
	private static String validationMessage = "Tu confirmes parier pour le prix de " + ticketPrice + " kamas sur ";
	private static String[] validationKeys = {"oui","Oui","OUI","ui","Ui","yes","Yes","YES", "yep", "Yep", "YEP","ouai","Ouai","OUAI"};
	private static String tkanksMessage = "Merci d'avoir parié ! :) Tu peux consulter le tirage en cours et les tirages archivés via ce lien : https://urlz.fr/4EHx";
	
	public static ProgramInterface loto = new ProgramInterface() {

		public void run(Person person) throws AWTException, IOException, TesseractException, InterruptedException {			
			try {
				Exchange exchange = new Exchange(ticketPrice,0);
				String name = exchange.waitForExchange();
				Message message = new Message(name, Channel.Private, welcomeMessage);
				message.send();
				Thread.sleep(1000);
				message.reply(exchangeMessage);
				Thread.sleep(1000);
				
				String sort;
				do {
					message.reply(questionMessage);
					message = message.waitForReply(timeout);
					
					if(message == null)
						exchange.cancelExchange();
					
					sort = parseSort(message.getText());
				}while(sort == null);
				
				Image image = exchange.exchange(validationMessage + sort + " ?", validationKeys);
				message.reply(tkanksMessage);
				
				
				
				
				//https://docs.google.com/spreadsheets/d/1g9SQS-HXscoK9jv-2hdiA38adiv5n5BJ38a_E-QnNiw/edit#gid=1102216258
				
			} catch (B4DExchangeCanceled e) {
				
			}
		}
	};
	
	private static String parseSort(String message) {
		return "Roulette";
	}
}
