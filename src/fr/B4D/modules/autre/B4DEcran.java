package fr.B4D.modules.autre;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import fr.B4D.classes.Bot;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public final class B4DEcran {

	  /*****************/
	 /* COULEUR PIXEL */
	/*****************/
	
	public static Color Couleur_Pixel(Double pointF) throws AWTException {
		Robot robot = new Robot();
		Point point = B4DConversion.DoubleToPoint(pointF);
		return robot.getPixelColor(point.x, point.y);
	}
	
	  /*******************/
	 /* RECHERCHE PIXEL */
	/*******************/
	
	public static boolean Rechercher_Pixel(Double P1, Double P2, char R, boolean Rsupperior, char G, boolean Gsupperior, char B, boolean Bsupperior, Double point) throws AWTException {
		
		Point Point1 = B4DConversion.DoubleToPoint(P1);
		Point Point2 = B4DConversion.DoubleToPoint(P2);
		int Largeur = Point2.x - Point1.x, Hauteur = Point2.y - Point1.y;
		
		Color color;
		
		for(int j=0;j<Hauteur;j++) {
			for(int i=0;i<Largeur;i++) {
				Robot robot = new Robot();
				color = robot.getPixelColor(i, j);
				
				if((Rsupperior = true) != (color.getRed() > R) && (Gsupperior = true) != (color.getGreen() > G) && (Bsupperior = true) != (color.getBlue() > B)) {
					point = B4DConversion.PointToDouble(new Point(i+Point1.x, j+Point1.y));
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean Rechercher_Pixel(Double P1, Double P2, char R, boolean Rsupperior, char G, boolean Gsupperior, char B, boolean Bsupperior, ArrayList<Double> points) throws AWTException {
		
		Point Point1 = B4DConversion.DoubleToPoint(P1);
		Point Point2 = B4DConversion.DoubleToPoint(P2);
		int Largeur = Point2.x - Point1.x, Hauteur = Point2.y - Point1.y;
		
		Color color;
		boolean trouve = false;
		
		for(int j=0;j<Hauteur;j++) {
			for(int i=0;i<Largeur;i++) {
				Robot robot = new Robot();
				color = robot.getPixelColor(i, j);
				
				if((Rsupperior = true) != (color.getRed() > R) && (Gsupperior = true) != (color.getGreen() > G) && (Bsupperior = true) != (color.getBlue() > B)) {
					points.add(B4DConversion.PointToDouble(new Point(i+Point1.x, j+Point1.y)));
					trouve = true;
				}
			}
		}
		return trouve;
	}
	
	  /*******/
	 /* OCR */
	/*******/

	public static String OCR(Rectangle rectangle) throws AWTException, TesseractException, IOException {
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(rectangle);
		File file = File.createTempFile("screenshot", ".png");
		ImageIO.write(image, "png", file);
		Tesseract tessInst = new Tesseract();
		tessInst.setDatapath("./tessdata");
		return tessInst.doOCR(file);
	}
	public static String OCR(Point P1, Point P2) throws AWTException, TesseractException, IOException {
		return OCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y));
	}
	public static String OCR(Double P1, Double P2) throws AWTException, TesseractException, IOException {
		return OCR(B4DConversion.DoubleToPoint(P1), B4DConversion.DoubleToPoint(P2));
	}
	public static String Analyse_Chat_OCR() throws AWTException, TesseractException, IOException {
		return OCR(Bot.MaConfiguration.zoneDeChat);
	}
	
	  /*************/
	 /* SELECTION */
	/*************/

	public static String Analyse_Selection(Point position) throws AWTException {
		Robot robot = new Robot();
		B4DSouris.Clic_Droit(position, false);
		robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_A);
	    robot.keyRelease(KeyEvent.VK_A);
	    robot.keyPress(KeyEvent.VK_C);
	    robot.keyRelease(KeyEvent.VK_C);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    return B4DClavier.RecupererPressePapier();
	}
	public static String Analyse_Selection(Double position) throws AWTException {
		return Analyse_Selection(B4DConversion.DoubleToPoint(position));
	}
	public static String Analyse_Chat_Clic() throws AWTException {
		return Analyse_Selection(new Point((int)(Bot.MaConfiguration.zoneDeChat.x + Bot.MaConfiguration.zoneDeChat.width*0.95), (int)(Bot.MaConfiguration.zoneDeChat.y + Bot.MaConfiguration.zoneDeChat.height*0.95)));
	}
}
