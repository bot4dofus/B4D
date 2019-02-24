package fr.B4D.bot.statics;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Configuration;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.threads.ColorThread;
import fr.B4D.threads.OCRThread;
import fr.B4D.threads.PixelThread;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/** La classe {@code Screen} permet d'accéder à toutes les méthodes liés à l'écran.
 */
public final class Screen {
	
	private Configuration configuration;
    
	/*************/
	/** BUILDER **/
	/*************/
    
	/** Constructeur de la classe {@code Screen}. 
     * @param configuration - Configuration de l'écran de jeu.
     */
    public Screen(Configuration configuration) {
    	this.configuration = configuration;
    }
	
	  /*******************/
	 /** COULEUR PIXEL **/
	/*******************/
	
	/** Permet de récupérer la couleur d'un pixel.
	 * @param point - Point en coordonnées simples.
	 * @return Couleur du pixel.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public Color getPixelColor(Point point) throws AWTException   {
		return new Robot().getPixelColor(point.x, point.y);
	}
	
	/** Permet de récupérer la couleur d'un pixel.
	 * @param point - Point en coordonnées relatives.
	 * @return Couleur du pixel.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public Color getPixelColor(PointF point) throws AWTException {
		return getPixelColor(B4D.converter.toPoint(point));
	}
	
	/** Permet de récupérer la couleur d'un pixel.
	 * @param point - Point en coordonnées du damier de dofus.
	 * @return Couleur du pixel.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public Color getPixelColor(PointD point) throws AWTException {
		return getPixelColor(B4D.converter.toPoint(point));
	}
	
	  /*********************/
	 /** RECHERCHE PIXEL **/
	/*********************/
	
	/** Permet de rechercher un pixel correspondant à un certain critère parmis une plage de pixels.
	 * @param topLeftHandCorner - Point suppérieur gauche du rectangle de recherche en coordonnées relatives.
	 * @param bottomRightHandCorner - Point inférieur droit du rectangle de recherche en coordonnées relatives.
	 * @param min - Couleur minimum.
	 * @param max - Couleur maximum.
	 * @return Position du pixel en coordonnées relatives.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public PointF searchPixel(PointF topLeftHandCorner, PointF bottomRightHandCorner, Color min, Color max) throws AWTException {
		Point Point1 = B4D.converter.toPoint(topLeftHandCorner);
		Point Point2 = B4D.converter.toPoint(bottomRightHandCorner);
		
		Color color;
		Point point;
		
		for(int y=Point1.y;y<=Point2.y;y++) {
			for(int x=Point1.x;x<=Point2.x;x++) {
				point = new Point(x, y);
				color = getPixelColor(point);
				if(isBetween(color, min, max))
					return B4D.converter.toPointF(point);
			}
		}
		return null;
	}
	
	/** Permet de rechercher tous les pixels correspondants à un certain critère parmis une plage de pixels.
	 * @param topLeftHandCorner - Point suppérieur gauche du rectangle de recherche en coordonnées relatives.
	 * @param bottomRightHandCorner - Point inférieur droit du rectangle de recherche en coordonnées relatives.
	 * @param min - Couleur minimum.
	 * @param max - Couleur maximum.
	 * @return Liste des pixels en coordonnées relatives correspondants au critère de recherche.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public ArrayList<PointF> searchPixels(PointF topLeftHandCorner, PointF bottomRightHandCorner, Color min, Color max) throws AWTException {
		
		Point Point1 = B4D.converter.toPoint(topLeftHandCorner);
		Point Point2 = B4D.converter.toPoint(bottomRightHandCorner);
		ArrayList<PointF> points = new ArrayList<PointF>();
		
		Color color;
		Point point;
		
		for(int y=Point1.y;y<=Point2.y;y++) {
			for(int x=Point1.x;x<=Point2.x;x++) {
				point = new Point(x, y);
				color = getPixelColor(point);
				
				if(isBetween(color, min, max))
					points.add(B4D.converter.toPointF(point));
			}
		}
		if (points.isEmpty())
			points = null;
		return points;
	}

	  /****************/
	 /** SCREENSHOT **/
	/****************/
	
	/** Permet de faire une capture d'écrans d'une zone précise.
	 * @param rectangle - Zone à capturer.
	 * @return Image de la zone capturée.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public BufferedImage takeSreenshot(Rectangle rectangle) throws AWTException {
		return new Robot().createScreenCapture(rectangle);
	}
	
	/** Permet de faire une capture d'écrans de la zone de jeu.
	 * @return Image de la zone de jeu.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public BufferedImage takeSreenshot() throws AWTException {
		return new Robot().createScreenCapture(configuration.getGameFrame());
	}
	
	  /*********/
	 /** OCR **/
	/*********/
	
	/** Permet de faire une reconnaissance optique de caractère sur une zone précise de l'écran.
	 * @param rectangle - Zone de l'écran.
	 * @return Chaine de caractère identifiée dans la zone, {@code null} si rien n'a été trouvé.
	 * @throws TesseractException Si impossible de réaliser l'OCR.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public String OCR(Rectangle rectangle) throws AWTException, TesseractException {
		BufferedImage image = takeSreenshot(rectangle);
		Tesseract tessInst = new Tesseract();
		tessInst.setLanguage("fra");
		String out = tessInst.doOCR(image);
		return out.replaceAll("\n", " ");
	}
	
	/** Permet de faire une reconnaissance optique de caractère sur une zone précise de l'écran.
	 * @param topLeftHandCorner - Point suppérieur gauche de la zone en coordonnées simples.
	 * @param bottomRightHandCorner - Point inférieur droit de la zone en coordonnées simples.
	 * @return Chaine de caractère identifiée dans la zone, {@code null} si rien n'a été trouvé.
	 * @throws TesseractException Si impossible de réaliser l'OCR.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public String OCR(Point topLeftHandCorner, Point bottomRightHandCorner) throws AWTException, TesseractException {
		return OCR(new Rectangle(topLeftHandCorner.x,  topLeftHandCorner.y, bottomRightHandCorner.x - topLeftHandCorner.x, bottomRightHandCorner.y - topLeftHandCorner.y));
	}
	
	/** Permet de faire une reconnaissance optique de caractère sur une zone précise de l'écran.
	 * @param topLeftHandCorner - Point suppérieur gauche de la zone en coordonnées relatives.
	 * @param bottomRightHandCorner - Point inférieur droit de la zone en coordonnées relatives.
	 * @return Chaine de caractère identifiée dans la zone, {@code null} si rien n'a été trouvé.
	 * @throws TesseractException Si impossible de réaliser l'OCR.
	 * @throws AWTException Si un problème d'écran survient.
	 */
	public String OCR(PointF topLeftHandCorner, PointF bottomRightHandCorner) throws AWTException, TesseractException {
		return OCR(B4D.converter.toPoint(topLeftHandCorner), B4D.converter.toPoint(bottomRightHandCorner));
	}
	
	  /***************/
	 /** SELECTION **/
	/***************/

	/** Permet de récupérer une chaine de caractère séléctionnée en faisant un double clique et Ctrl+C.
	 * @param point - Position de la sélection en coordonnées simples.
	 * @return Chaine de caractère représentant la sélection. {@code null} si rien n'a été sélectionnée où si la sélection est vide.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws AWTException Si un problème de souris survient.
	 */
	public String getSelection(Point point) throws AWTException, StopProgramException, CancelProgramException {
		Robot robot = new Robot();
		B4D.mouse.doubleLeftClick(point, false, 100);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		B4D.wait.wait(1000);
		return B4D.keyboard.getClipboard();
	}
	
	/** Permet de récupérer une chaine de caractère séléctionnée en faisant un double clique et Ctrl+C.
	 * @param position - Position de la sélection en coordonnées relatives.
	 * @return Chaine de caractère représentant la sélection. {@code null} si rien n'a été sélectionnée où si la sélection est vide.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws AWTException Si un problème de souris survient.
	 */
	public String getSelection(PointF position) throws AWTException, StopProgramException, CancelProgramException {
		return getSelection(B4D.converter.toPoint(position));
	}
	
	  /************/
	 /** OTHERS **/
	/************/
	
	/** Permet de savoir si une couleur se trouve dans un intervale.
	 * @param couleur - Couleur du pixel.
	 * @param min - Couleur minimale.
	 * @param max - Couleur maximale.
	 * @return {@code true} si la couleur est dans l'intervale, {@code false} sinon.
	 */
	public boolean isBetween(Color couleur, Color min, Color max) {
		return (min.getRed() <= couleur.getRed() && couleur.getRed() <= max.getRed() && min.getGreen() <= couleur.getGreen() && couleur.getGreen() <= max.getGreen() && min.getBlue() <= couleur.getBlue() && couleur.getBlue() <= max.getBlue());
	}

	/** Permet donner le focus à la fenêtre de jeu.
	 */
	public void focusDofus() {
		// TODO Auto-generated method stub
	}
	
	  /*********************/
	 /** ATTENTE SUR MAP **/
	/*********************/
	
	/** Permet d'attendre l'appui sur une touche.
	 * @param timeOut - Temps d'attente avant timeout en millisecondes.
	 * @return Entier représentant la touche enfoncée. {@code -1} si timeout.
	 */
	
	/** Permet d'attendre un changement de map.
	 * @param timeOut - Temps d'attente avant timeout en millisecondes.
	 * @return {@code true} si le joueur à changé de map, {@code false} si timeout.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public boolean waitForMap(int timeOut) throws StopProgramException, CancelProgramException {
		return waitForChangingPixel(B4D.converter.toPointF(configuration.getMinimap()), timeOut) != null;
	}
	
	/** Permet d'attendre un changement de map sans limite de temps. 
	 * Cela est identique à {@code waitForMap(0)}.
	 * @return {@code true} si le joueur à changé de map, {@code false} si timeout.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public boolean waitForMap() throws StopProgramException, CancelProgramException {
		return waitForMap(0);
	}
	
	  /*********************/
	 /** ATTENTE SUR OCR **/
	/*********************/
	
	/** Permet d'attendre qu'une chaine de caractère soit détectée à l'écran.
	 * @param rectangle - Zone de l'écran.
	 * @param regex - Exprésion régulière que doit contenir la chaine de caractère.
	 * @param timeOut - Temps d'attente avant timeout en millisecondes.
	 * @return Chaine de caractère identifiée dans la zone, {@code null} si timeout.
	 */
	public String waitForOCR(Rectangle rectangle, String regex, int timeOut) {
		OCRThread ocrThread = new OCRThread(rectangle, regex);
		ocrThread.start();
		try {
			ocrThread.join(timeOut);
		} catch (InterruptedException e) {
			B4D.logger.error(e);
		}
		
		if(ocrThread.isAlive())
			ocrThread.interrupt();
		return ocrThread.getText();
	}

	/** Permet d'attendre qu'une chaine de caractère soit détectée à l'écran.
	 * @param topLeftHandCorner - Point suppérieur gauche de la zone en coordonnées simples.
	 * @param bottomRightHandCorner - Point inférieur droit de la zone en coordonnées simples.
	 * @param regex - Exprésion régulière que doit contenir la chaine de caractère.
	 * @param timeOut - Temps d'attente avant timeout en millisecondes.
	 * @return Chaine de caractère identifiée dans la zone, {@code null} si timeout.
	 */
	public String waitForOCR(Point topLeftHandCorner, Point bottomRightHandCorner, String regex, int timeOut) {
		return waitForOCR(new Rectangle(topLeftHandCorner.x,  topLeftHandCorner.y, bottomRightHandCorner.x - topLeftHandCorner.x, bottomRightHandCorner.y - topLeftHandCorner.y), regex, timeOut);
	}
	
	/** Permet d'attendre qu'une chaine de caractère soit détectée à l'écran.
	 * @param topLeftHandCorner - Point suppérieur gauche de la zone en coordonnées relatives.
	 * @param bottomRightHandCorner - Point inférieur droit de la zone en coordonnées relatives.
	 * @param regex - Exprésion régulière que doit contenir la chaine de caractère.
	 * @param timeOut - Temps d'attente avant timeout en millisecondes.
	 * @return Chaine de caractère identifiée dans la zone, {@code null} si timeout.
	 */
	public String waitForOCR(PointF topLeftHandCorner, PointF bottomRightHandCorner, String regex, int timeOut) {
		return waitForOCR(B4D.converter.toPoint(topLeftHandCorner), B4D.converter.toPoint(bottomRightHandCorner), regex, timeOut);
	}
	
	  /*************************************/
	 /** ATTENTE SUR CHANGEMENT DE PIXEL **/
	/*************************************/
	
	/** Permet d'attendre qu'un pixel change de couleur.
	 * @param point - Position du pixel en coordonnées simples.
	 * @param timeOut - Temps d'attente avant timeout en millisecondes.
	 * @return Nouvelle couleur du pixel, {@code null} si timeout.
	 */
	public Color waitForChangingPixel(Point point, int timeOut) {
		PixelThread pixelThread = new PixelThread(point);
		pixelThread.start();
		try {
			pixelThread.join(timeOut);
		} catch (InterruptedException e) {
			B4D.logger.error(e);
		}
		
		if(pixelThread.isAlive())
			pixelThread.interrupt();
		return pixelThread.getColor();
	}
	
	/** Permet d'attendre qu'un pixel change de couleur.
	 * @param point - Position du pixel en coordonnées relatives.
	 * @param timeOut - Temps d'attente avant timeout en millisecondes.
	 * @return Nouvelle couleur du pixel, {@code null} si timeout.
	 */
	public Color waitForChangingPixel(PointF point, int timeOut) {
		return waitForChangingPixel(B4D.converter.toPoint(point), timeOut);
	}
	
	  /**********************************/
	 /** ATTENTE SUR COULEUR DE PIXEL **/
	/**********************************/
	
	/** Permet d'attendre qu'un pixel soit compris dans un intervale de couleur.
	 * @param point - Position du pixel en coordonnées simples.
	 * @param min - Couleur minimale.
	 * @param max - Couleur maximale.
	 * @param timeOut - Temps d'attente avant timeout en millisecondes.
	 * @return Couleur du pixel, {@code null} si timeout.
	 */
	public Color waitForColor(Point point, Color min, Color max, int timeOut) {
		ColorThread colorThread = new ColorThread(point, min, max);
		colorThread.start();
		try {
			colorThread.join(timeOut);
		} catch (InterruptedException e) {
			B4D.logger.error(e);
		}
		
		if(colorThread.isAlive())
			colorThread.interrupt();
		return colorThread.getColor();
	}
	
	/** Permet d'attendre qu'un pixel soit compris dans un intervale de couleur.
	 * @param point - Position du pixel en coordonnées relatives.
	 * @param min - Couleur minimale.
	 * @param max - Couleur maximale.
	 * @param timeOut - Temps d'attente avant timeout en millisecondes.
	 * @return Couleur du pixel, {@code null} si timeout.
	 */
	public Color waitForColor(PointF point, Color min, Color max, int timeOut) {
		return waitForColor(B4D.converter.toPoint(point), min, max, timeOut);
	}
}
