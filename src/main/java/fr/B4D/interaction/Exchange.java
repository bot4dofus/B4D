package fr.B4D.interaction;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import fr.B4D.bot.B4D;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;
import fr.B4D.utils.Rectangle;
import net.sourceforge.tess4j.TesseractException;

/** La classe {@code Exchange} représente un échange avec un joueur.<br><br>
 * Un échange est défini par un nombre de kamas entrants et un nombre de kamas sortants.
 */
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
	
	private String pseudo;
	private int kamasIn, kamasOut;
	
	  /*****************/
	 /** CONSTRUCTOR **/
	/*****************/
	
	/** Constructeur de la classe {@code Exchange}.
	 * @param kamasIn - Nombre de kamas attendu de la part du joueur.
	 * @param kamasOut - Nombre de kamas donné au joueur.
	 */
	public Exchange(int kamasIn, int kamasOut) {
		this.kamasIn = kamasIn;
		this.kamasOut = kamasOut;
	}
	
	  /*************/
	 /** GETTERS **/
	/*************/
	
	/** Retourne le pseudo de la personne avec qui l'échange à lieu.
	 * @return Pseudo de la personne ou {@code null} si le nom n'est pas connu.
	 */
	public String getPseudo() {
		return pseudo;
	}

	/** Modifi le pseudo du joueur avec qui l'échange à lieu.
	 * @param pseudo - Pseudo du joueur.
	 */
	public void setPseudo(String pseudo) {
		synchronized(this.pseudo) {
			this.pseudo = pseudo;
			pseudo.notifyAll();
		}
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	/** Permet d'attendre sans limite de temps un échange proposé par un joueur. Cela est identique à {@code waitForExchange(0)}.
	 * @return Pseudo du joueur ayant proposé l'échange et {@code null} si timeout.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public String waitForExchange() throws StopProgramException, CancelProgramException {
		return waitForExchange(0);
	}

	/** Permet d'attendre un échange provoqué par un joueur en présisant une durée maximal d'attente
	 * @param timeout - Durée d'attente maximale en millisecondes.
	 * @return Pseudo du joueur ayant proposé l'échange et {@code null} si timeout.
	 * @throws StopProgramException Si le programme est stoppé .
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public String waitForExchange(int timeout) throws StopProgramException, CancelProgramException {
		if(!B4D.socketListener.isAlive())
			B4D.socketListener.start();
		
		B4D.logger.debug(this, "Attente d'un échange");
		String message = B4D.screen.waitForOCR(waitForExchangeRectangle, waitForExchangeKey, timeout);
		
		if(message == null)
			B4D.logger.debug(this, "Aucun échange demandé (timeout)");
		else {
			pseudo = message.split(" ")[0];
			B4D.logger.debug(this, "Echange avec [" + pseudo + "]");
			B4D.mouse.leftClick(waitForExchangeYesButton, false);
		}
		return pseudo;
	}
	
	/** Réalise l'échange avec le joueur
	 * @param validationMessage - Question posé au joueur avant de confirmer l'échange. Celui-ci peut répondre par "".
	 * @return Image preuve du consentant du joueur.
	 * @throws ExchangeCanceledException Si l'échange est annulé.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 * @throws TesseractException Si un problème d'OCR survient.
	 */
	public BufferedImage exchange(String validationMessage) throws ExchangeCanceledException, TesseractException, StopProgramException, CancelProgramException {
		B4D.logger.debug(this, "Début de l'échange");
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
		B4D.logger.debug(this, "Echange éffectué");
		return image;
	}
	
	/** Permet de savoir si une chaine de caractère contient un des motifis correspondants.
	 * @param string - Chaine de caractère dans laquelle chercher la clef.
	 * @param keys - Tableau des clefs possibles
	 * @return {@code true} si la chaine de caractère contient une des clefs, sinon {@code false}.
	 */
	private static boolean contains(String string, String[] keys) {
		for(String key:keys) {
			if(string.contains(key))
				return true;
		}
		return false;
	}
	
	/** Annule l'échange en cours si celui-ci l'est encore.
	 * @throws ExchangeCanceledException Si l'échange est annulé.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public void cancelExchange() throws ExchangeCanceledException, StopProgramException, CancelProgramException {
		B4D.logger.debug(this, "Echange annulé");
		if(isInProgress())
			B4D.mouse.leftClick(escapeButton, false);
		throw new ExchangeCanceledException();
	}
	
	/** Permet de savoir si l'échange est encore en cours.
	 * @return {@code true} si l'échange est en cours, sinon {@code false}.
	 */
	private boolean isInProgress() {
		return (B4D.screen.getPixelColor(validationButton).getBlue() == 0);
	}
	
	/** Permet de savoir si le joueur à validé l'échange.
	 * @return {@code true} si le joueur à validé, sinon {@code false}.
	 */
	private boolean isValided() {
		return B4D.screen.searchPixel(new PointF(0.3408,0.2804), new PointF(0.3408,0.2914), new Color(100, 100, 0), new Color(255, 255, 50)) != null;
	}
}
