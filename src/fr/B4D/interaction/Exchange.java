package fr.B4D.interaction;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;

import fr.B4D.bot.B4D;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.modules.B4DKeyboard;
import fr.B4D.modules.B4DMouse;
import fr.B4D.modules.B4DScreen;
import fr.B4D.modules.B4DWait;
import fr.B4D.utils.PointF;
import fr.B4D.utils.Rectangle;
import net.sourceforge.tess4j.TesseractException;

public class Exchange implements Serializable{

	private static final long serialVersionUID = 6230637950441806082L;
	
	  /***************/
	 /** CONSTANTS **/
	/***************/
	
	private final String waitForExchangeKey = "souhaite"; //clef permettant de valider l'echange
	
	private final Rectangle waitForExchangeRectangle = new Rectangle(new PointF(0.3762, 0.4756), new PointF(0.6238, 0.5055)); //Zone des kamas entrants
	private final PointF waitForExchangeYesButton = new PointF(0.4143, 0.5304);		//Emplacement du bouton accepter
	
	private final int timeout = 30000; 												//Timeout avant echec de l'�change
	private final Rectangle kamasInRectangle = new Rectangle(new PointF(0.2163, 0.2562), new PointF(0.2835, 0.2742)); //Zone somme entrante

	private final PointF escapeButton = new PointF(0.8093, 0.0888);			//Emplacement de la croix
	private final PointF validationButton = new PointF(0.3491, 0.5334);		//Emplacement du bouton de validation de l'echange
	
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
	
	/** Permet d'attendre un �change provoqu� par un joueur.
	 * @return le nom du joueur ayant demand� l'�change
	 * @throws AWTException si impossible de valider l'�change
	 */
	public String waitForExchange() throws AWTException {
		return waitForExchange(0);
	}

	/** Permet d'attendre un �change provoqu� par un joueur.
	 * @param timeout le nombre de millisecondes � attendre avant timeout
	 * @return le nom du joueur ayant demand� l'�change. {@code null} si timeout
	 * @throws AWTException si impossible de valider l'�change
	 */
	public String waitForExchange(long timeout) throws AWTException {
		B4D.logger.debug(this, "Attente d'un �change");
		String message;
		while((message = B4DWait.waitForOCR(waitForExchangeRectangle, waitForExchangeKey, timeout)) == null);
		String name = message.split(" ")[0];
		B4DMouse.leftClick(waitForExchangeYesButton, false);
		return name;
	}
//	public String waitForExchange(long timeout) throws AWTException {
//		if(!B4D.getSocketListener().isAlive())
//			B4D.getSocketListener().start();
//		
//		try {
//			synchronized(name){
//				if(name == null) 
//					name.wait(timeout);
//				if(name != null)
//					B4DMouse.leftClick(waitForExchangeYesButton, false);
//			}
//		}
//		catch(InterruptedException e) {
//			B4D.logger.error(e);
//		}
//		return name;
//	}
	
	/**
	 * @param validationMessage
	 * @param validationKeys
	 * @return
	 * @throws B4DExchangeCanceled si 
	 */
	public Image exchange(String validationMessage, String[] validationKeys) throws B4DExchangeCanceled, AWTException, TesseractException, NumberFormatException, IOException {
		B4D.logger.debug(this, "D�but de l'�change");
		Message message;
		
		if(kamasOut > 0) {
			//Clique sur le champs kamas
			B4DKeyboard.writeKeyboard(Integer.toString(kamasOut));
		}
		
		do {
			if(B4DWait.waitForOCR(kamasInRectangle, String.valueOf(kamasIn), timeout) == null)
				cancelExchange();
		}while(B4DScreen.searchPixel(new PointF(0.396, 0.2804), new PointF(0.396, 0.2914), new Color(100, 100, 0), new Color(255, 255, 50)) == null);

		do {				
			message = new Message(name, Channel.Private, validationMessage);
			message.send();
			message = message.waitForReply(timeout);
			
			if(message == null || !contains(message.getText(), validationKeys))
				cancelExchange();
				
		}while(Integer.parseInt(B4DScreen.OCR(kamasInRectangle)) != kamasIn); //&& B4D);

		Image image = B4DScreen.takeSreenshot();
		B4DMouse.leftClick(validationButton, false);
		
		B4D.logger.debug(this, "Echange �ffectu�");
		return image;
	}
	
	private static boolean contains(String string, String[] keys) {
		for(String key:keys) {
			if(string.contains(key))
				return true;
		}
		return false;
	}
	
	public void cancelExchange() throws B4DExchangeCanceled, AWTException {
		B4D.logger.debug(this, "Echange annul�");
		if(isInProgress())
			B4DMouse.leftClick(escapeButton, false);
		throw new B4DExchangeCanceled();
	}
	
	private boolean isInProgress() {
		//if window still here
			//return true;
		//else
			return false;
	}
}
