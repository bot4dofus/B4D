package fr.B4D.program;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serializable;

import fr.B4D.exceptions.B4DExchangeCanceled;
import fr.B4D.modules.B4DChat;
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
	
	private final String waitForExchangeKey = "souhaite faire"; 	//clef permettant de valider l'echange
	private final int waitForExchangeTimeout = 60; 					//Temps avant chaque vérification de demande d'échange
	private final Rectangle waitForExchangeRectangle = new Rectangle(new PointF(0.3762, 0.4756), new PointF(0.6238, 0.5055)); //Zone des kamas entrants
	private final PointF waitForExchangeYesButton = new PointF(0.4143, 0.5304);		//Emplacement du bouton accepter
	
	private final int iterationNumber = 2; 			//Nombre de fois que l'instruction d'échange est envoyée
	private final int iterationTimeout = 30; 		//Temps avant chaque répétition d'instruction
	private final Rectangle kamasInRectangle = new Rectangle(new PointF(0.2163, 0.2562), new PointF(0.2835, 0.2742)); //Zone somme entrante

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
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public String waitForExchange(String welcomMessage) throws AWTException, IOException, TesseractException {
		String message;
		while((message = B4DWait.waitForOCR(waitForExchangeRectangle, waitForExchangeKey, waitForExchangeTimeout)) == null);
		String name = message.split(" ")[0];
		B4DMouse.leftClick(waitForExchangeYesButton, false);
		B4DChat.sendPrivateMessage(name, welcomMessage);
		return name;
	}
	
	public void exchange(String exchangeMessage) throws B4DExchangeCanceled, AWTException, NumberFormatException, IOException, TesseractException {
		//Si kamas out > 0
			//Ajout des kamas out
		for(int i=1;i<=iterationNumber;i++) {
			B4DChat.sendPrivateMessage(name, exchangeMessage);
			String kamas = B4DWait.waitForOCR(kamasInRectangle, String.valueOf(kamasIn), iterationTimeout);
			//Attendre la validation du type (vert)
			if(kamas != null) {
				if(Integer.valueOf(B4DScreen.OCR(kamasInRectangle)) == kamasIn)
					break;
			}
			if(i == iterationNumber) {
				cancelExchange();
			}
		}
	}
	
	public Image validExchange(String validationMessage, String[] validationKeys) throws AWTException {
		//B4DChat.sendPrivateMessage(name, validationMessage);
		//WaitFotChat
		//si null
			//throw
		Image proof = B4DScreen.takeSreenshot();
		B4DMouse.leftClick(validationButton, false);
		return proof;
	}
	
	public void cancelExchange() throws B4DExchangeCanceled, AWTException {
		if(isInProgress())
			B4DKeyboard.sendKey(KeyEvent.VK_ESCAPE);
		throw new B4DExchangeCanceled();
	}
	
	public boolean isInProgress() {
		//if window still here
			//return true;
		//else
			return false;
	}
}
