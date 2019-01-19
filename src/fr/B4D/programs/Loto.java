package fr.B4D.programs;

import java.awt.AWTException;
import java.io.IOException;

import fr.B4D.interaction.B4DExchangeCanceled;
import fr.B4D.interaction.Exchange;
import fr.B4D.modules.B4DChat;
import fr.B4D.program.ProgramInterface;
import net.sourceforge.tess4j.TesseractException;

public final class Loto {
	private static int ticketPrice = 10000;
	private static String welcomeMessage = "Bonjour, pour avoir plus d'infos sur le règlement de la roulette merci de consulter ce lien : https://urlz.fr/8g6H.";
	private static String exchangeMessage = "Le prix du pari est actuellement de " + ticketPrice + ", insérez cette somme et validez pour jouer un ticket.";
	private static String validationMessage = "Confirmez vous vouloir parier sur [bonus] pour le prix de " + ticketPrice + " kamas ?";
	private static String[] validationKeys = {"oui","Oui","OUI","ui","Ui","yes","Yes","YES"};
	private static String tkanksMessage = "Merci d'avoir parié. Vous pouvez consulter le tirage en cours et les tirages archivés via ce lien : https://urlz.fr/4EHx";
	
	public static ProgramInterface loto = new ProgramInterface() {

		public void run() throws AWTException, IOException, TesseractException {			
			try {
				Exchange exchange = new Exchange(ticketPrice,0);
				String name = exchange.waitForExchange(welcomeMessage);
				//Sur quel sort veux tu voter ?
				//Attente de la reponse
				//Et tu sur ?
				//Attente de la reponse
				exchange.exchange(exchangeMessage);
				exchange.validExchange(validationMessage, validationKeys);
				//Screenshot
				B4DChat.sendChat(tkanksMessage);
			} catch (B4DExchangeCanceled e) {
				
			}
		}
	};
}
