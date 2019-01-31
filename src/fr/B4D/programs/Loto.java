package fr.B4D.programs;

import java.awt.AWTException;
import java.awt.Image;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import fr.B4D.bot.Person;
import fr.B4D.interaction.B4DExchangeCanceled;
import fr.B4D.interaction.Exchange;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
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
	
	public static ProgramInterface loto = new ProgramInterface() {
		
		public void intro(Person person) {
			
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
			} catch (B4DExchangeCanceled e) {
				
			}
		}

		public void outro(Person person) {
			//Do nothing for the moment
		}
	};
	
	private static String parseSort(String message) {
		return "Roulette";
	}
}
