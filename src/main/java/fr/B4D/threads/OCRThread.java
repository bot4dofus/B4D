package fr.B4D.threads;

import java.awt.Rectangle;

import fr.B4D.bot.B4D;

/** La classe {@code OCRThread} permet d'attendre qu'une chaine de caractère soit détectée à l'écran.
 * Cette classe étend la classe {@code Thread}.
 */
public class OCRThread extends Thread{
	private Rectangle rectangle;
	private String regex, text;

	/** Constructeur de la classe {@code OCRThread}.
	 * @param rectangle - Zone de recherche de la chaine de caractère.
	 * @param regex - Expression régulière attendue.
	 */
	public OCRThread(Rectangle rectangle, String regex) {
		this.rectangle = rectangle;
		this.regex = regex;
	}
	
	/** Retourne la chaine de caractère détecté à l'écran.
	 * @return Chaine de caractère. {@code null} si l'expréssion régulière n'est pas présente dans la chaine.
	 */
	public String getText() {
		return text;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		try{
			String ocr;
			do {
				ocr = B4D.screen.OCR(rectangle);
				Thread.sleep(100);
			}while(!ocr.contains(regex));
			text = ocr;
		}catch (Exception e){
			Thread.currentThread().interrupt();
		}
	}
}
