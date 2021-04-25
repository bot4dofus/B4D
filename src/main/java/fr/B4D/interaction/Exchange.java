package fr.B4D.interaction;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;
import fr.B4D.utils.Rectangle;

/**
 * The {@code Exchange} class represents an exchanges with another player.<br><br>
 * An exchange is defined by input kamas and output kamas.
 * 
 * @author Lucas
 *
 */
public class Exchange implements Serializable{

	private static final long serialVersionUID = 6230637950441806082L;
	
	/**
	 * Timeout before exchange cancelation.
	 */
	private final int timeout = 30000;	//Timeout avant echec de l'échange
	
	/**
	 * Area where a popup shows when a player when to make an exchange.
	 */
	private final Rectangle waitForExchangeRectangle = new Rectangle(new PointF(0.3104,0.476), new PointF(0.6904,0.506));
	
	/**
	 * Regex in the chat used to detect an exchange.
	 */
	private final String waitForExchangeKey = "avec vous";
	
	/**
	 * Location of the "Yes" button when a player want to make an exchange.
	 */
	private final PointF waitForExchangeYesButton = new PointF(0.3688,0.5299);
	
	/**
	 * Input kamas area.
	 */
	private final Rectangle kamasInRectangle = new Rectangle(new PointF(0.0648,0.2525), new PointF(0.1672,0.2745));
	
	/**
	 * Output kamas location.
	 */
	private final PointF kamasOutArea = new PointF(0.4912,0.2625);
	
	/**
	 * List of valid answers.
	 */
	private static String[] validationKeys = {"oui","Oui","OUI","ui","Ui","yes","Yes","YES", "yep", "Yep", "YEP","ouai","Ouai","OUAI"};
	
	/**
	 * Location of the "Cancel" button.
	 */
	private final PointF escapeButton = new PointF(0.9752,0.0888);
	
	/**
	 * Location of the validation button.
	 */
	private final PointF validationButton = new PointF(0.3192,0.5349);
	
	/**
	 * Pseudo of the player you are making the exchange with.
	 */
	private String pseudo;
	
	/**
	 * Expected kamas input.
	 */
	private int kamasIn;
	
	/**
	 * Expected kamas output.
	 */
	private int kamasOut;
	
	/**
	 * Constructor of the {@code Exchange} class.
	 * @param kamasIn - Number of kamas in.
	 * @param kamasOut - Number of kamas out.
	 */
	public Exchange(int kamasIn, int kamasOut) {
		this.kamasIn = kamasIn;
		this.kamasOut = kamasOut;
	}
	
	/**
	 * Returns the pseudo of the player you are making the exchange with.
	 * @return Pseudo of the player, {@code null} if the name is unknown.
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Sets the pseudo of the player you are making the exchange with.
	 * @param pseudo - Pseudo of the player.
	 */
	public void setPseudo(String pseudo) {
		synchronized(this.pseudo) {
			this.pseudo = pseudo;
			pseudo.notifyAll();
		}
	}
	
	/**
	 * Waits for an exchange. This is the same as {@code waitForExchange(0)}.
	 * @return Pseudo of the player asking for an exchange.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if cannot perform the OCR operation.
	 */
	public String waitForExchange() throws B4DException {
		return waitForExchange(0);
	}

	/**
	 * Waits for an exchange for a given amout of time.
	 * @param timeout - Timeout in ms.
	 * @return Pseudo of the player asking for an exchange, {@code null} if timeout.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if cannot perform the OCR operation.
	 */
	public String waitForExchange(int timeout) throws StopProgramException, CancelProgramException, B4DException {	
		B4D.logger.debug("Attente d'un échange");
		String message = B4D.screen.waitForOCR(waitForExchangeRectangle, waitForExchangeKey, timeout);
		
		if(message == null)
			B4D.logger.debug("Aucun échange demandé (timeout)");
		else {
			pseudo = message.split(" ")[0];
			B4D.logger.debug("Echange avec [" + pseudo + "]");
			B4D.mouse.leftClick(waitForExchangeYesButton, false);
		}
		return pseudo;
	}
	
	/**
	 * Makes the exchange with the player.
	 * @param validationMessage - Question asked to the player before confirmation. This one can answer by "Yes".
	 * @return Image - Proof that the player agreed to this exchange.
	 * @throws ExchangeCanceledException if the exchange is canceled.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if cannot perform the OCR operation.
	 */
	public BufferedImage exchange(String validationMessage) throws ExchangeCanceledException, StopProgramException, CancelProgramException, B4DException {
		B4D.logger.debug("Début de l'échange");
		Message message;
		
		if(kamasOut > 0) {
			B4D.mouse.doubleLeftClick(kamasOutArea, false);
			B4D.keyboard.writeKeyboard(Integer.toString(kamasOut));
		}
		
		do {
			message = new Message(pseudo, "Entre " + kamasIn + " kamas et valide");
			message.send();
			
			do {
				if(B4D.screen.waitForOCR(kamasInRectangle, String.valueOf(kamasIn), timeout) == null)
					cancelExchange();
			}while(!isValided());
			
			do {
				message = new Message(pseudo, validationMessage);
				message.send();
				message = message.waitForReply(timeout);
				
				if(message == null)
						cancelExchange();
			}while(!contains(message.getText(), validationKeys));					
		}while(B4D.screen.OCR(kamasInRectangle).equals(String.valueOf(kamasIn)) || !isValided());

		BufferedImage image = B4D.screen.takeSreenshot();
		B4D.mouse.leftClick(validationButton, false);
		B4D.logger.debug("Echange éffectué");
		return image;
	}
	
	/**
	 * Checks whether the string contains one of the keys.
	 * @param string - String in which find a key.
	 * @param keys - List of possible keys.
	 * @return {@code true} if the string contains one of the keys, {@code false} otherwise.
	 */
	private static boolean contains(String string, String[] keys) {
		for(String key:keys) {
			if(string.contains(key))
				return true;
		}
		return false;
	}
	
	/**
	 * Cancel the exchange if still in progress. This method raise a {@code ExchangeCanceledException}.
	 * @throws ExchangeCanceledException if the exchange is canceled.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected operation occurred.
	 */
	public void cancelExchange() throws ExchangeCanceledException, StopProgramException, CancelProgramException, B4DException {
		B4D.logger.debug("Echange annulé");
		if(isInProgress())
			B4D.mouse.leftClick(escapeButton, false);
		throw new ExchangeCanceledException();
	}
	
	/**
	 * Checks whether the exchange is still in progress.
	 * @return {@code true} if the exchange is in progress, {@code false} otherwise.
	 * @throws B4DException if cannot perform the get pixel operation.
	 */
	private boolean isInProgress() throws B4DException {
		return (B4D.screen.getPixelColor(validationButton).getBlue() == 0);
	}
	
	/**
	 * Checks whether the player has confirmed the exchange.
	 * @return {@code true} if the player has confirmed, {@code false} otherwise.
	 * @throws B4DException if cannot perform the search pixel operation.
	 */
	private boolean isValided() throws B4DException {
		return B4D.screen.searchPixel(new PointF(0.3408,0.2804), new PointF(0.3408,0.2914), new Color(100, 100, 0), new Color(255, 255, 50)) != null;
	}
}
