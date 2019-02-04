package fr.B4D.interaction;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import fr.B4D.bot.B4D;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;
import fr.B4D.utils.Rectangle;
import net.sourceforge.tess4j.TesseractException;

public class Exchange implements Serializable{

	private static final long serialVersionUID = 6230637950441806082L;
	
	  /***************/
	 /** CONSTANTS **/
	/***************/
	
	private final int timeout = 30000;	//Timeout avant echec de l'échange
	
	private final Rectangle waitForExchangeRectangle = new Rectangle(new PointF(0.3104,0.476), new PointF(0.6904,0.506)); 	//Zone des kamas entrants
	private final String waitForExchangeKey = "avec vous"; 						//clef permettant de valider l'echange
	private final PointF waitForExchangeYesButton = new PointF(0.3688,0.5299);	//Emplacement du bouton accepter
	
	private final Rectangle kamasInRectangle = new Rectangle(new PointF(0.0648,0.2525), new PointF(0.1672,0.2745)); 		//Zone somme entrante
	private final PointF kamasOutArea = new PointF(0.4912,0.2625);
	
	private static String[] validationKeys = {"oui","Oui","OUI","ui","Ui","yes","Yes","YES", "yep", "Yep", "YEP","ouai","Ouai","OUAI"};
	
	private final PointF escapeButton = new PointF(0.9752,0.0888);			//Emplacement de la croix
	private final PointF validationButton = new PointF(0.3192,0.5349);		//Emplacement du bouton de validation de l'echange
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private String name;
	private int kamasIn, kamasOut;
	
	  /*****************/
	 /** CONSTRUCTOR **/
	/*****************/
	
	public Exchange(int kamasIn, int kamasOut) {
		this.kamasIn = kamasIn;
		this.kamasOut = kamasOut;
	}
	
	  /*************/
	 /** GETTERS **/
	/*************/
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		synchronized(this.name) {
			this.name = name;
			name.notifyAll();
		}
	}
	
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	/** Permet d'attendre un échange provoqué par un joueur.
	 * @return le nom du joueur ayant demandé l'échange
	 * @throws AWTException si impossible de valider l'échange
	 * @throws StopProgramException 
	 * @throws CancelProgramException 
	 */
	public String waitForExchange() throws AWTException, StopProgramException, CancelProgramException {
		return waitForExchange(0);
	}

	/** Permet d'attendre un échange provoqué par un joueur.
	 * @param timeout le nombre de millisecondes à attendre avant timeout
	 * @return le nom du joueur ayant demandé l'échange. {@code null} si timeout
	 * @throws AWTException si impossible de valider l'échange
	 * @throws StopProgramException 
	 * @throws CancelProgramException 
	 */
	public String waitForExchange(int timeout) throws StopProgramException, AWTException, CancelProgramException {
		if(!B4D.socketListener.isAlive())
			B4D.socketListener.start();
		
		B4D.logger.debug(this, "Attente d'un échange");
		String message = B4D.screen.waitForOCR(waitForExchangeRectangle, waitForExchangeKey, timeout);
		
		if(message == null)
			B4D.logger.debug(this, "Aucun échange demandé (timeout)");
		else {
			name = message.split(" ")[0];
			B4D.logger.debug(this, "Echange avec [" + name + "]");
			B4D.mouse.leftClick(waitForExchangeYesButton, false);
		}
		return name;
	}
	
	public BufferedImage exchange(String validationMessage) throws B4DExchangeCanceled, AWTException, TesseractException, NumberFormatException, IOException, StopProgramException, CancelProgramException {
		B4D.logger.debug(this, "Début de l'échange");
		Message message;
		
		if(kamasOut > 0) {
			B4D.mouse.doubleLeftClick(kamasOutArea, false);
			B4D.keyboard.writeKeyboard(Integer.toString(kamasOut));
		}
		
		do {
			message = new Message(name, "Entre " + kamasIn + " kamas et valide");
			message.send();
			
			do {
				if(B4D.screen.waitForOCR(kamasInRectangle, String.valueOf(kamasIn), timeout) == null)
					cancelExchange();
			}while(!isValided());
			
			do {
				message = new Message(name, validationMessage);
				message.send();
				message = message.waitForReply(timeout);
				
				if(message == null)
						cancelExchange();
			}while(!contains(message.getText(), validationKeys));					
		}while(B4D.screen.OCR(kamasInRectangle).equals(String.valueOf(kamasIn)) || !isValided());

		BufferedImage image = B4D.screen.takeSreenshot();
		B4D.mouse.leftClick(validationButton, false);
		B4D.logger.debug(this, "Echange éffectué");
		return image;
	}
	
	private static boolean contains(String string, String[] keys) {
		for(String key:keys) {
			if(string.contains(key))
				return true;
		}
		return false;
	}
	
	public void cancelExchange() throws B4DExchangeCanceled, AWTException, StopProgramException, CancelProgramException {
		B4D.logger.debug(this, "Echange annulé");
		if(isInProgress())
			B4D.mouse.leftClick(escapeButton, false);
		throw new B4DExchangeCanceled();
	}
	
	private boolean isInProgress() {
		try {
			return (B4D.screen.getPixelColor(validationButton).getBlue() == 0);
		} catch (AWTException e) {
			return true;
		}
	}
	
	private boolean isValided() throws AWTException {
		return B4D.screen.searchPixel(new PointF(0.3408,0.2804), new PointF(0.3408,0.2914), new Color(100, 100, 0), new Color(255, 255, 50)) != null;
	}
}
