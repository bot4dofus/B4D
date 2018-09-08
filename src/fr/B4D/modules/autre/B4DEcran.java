package fr.B4D.modules.autre;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import fr.B4D.classes.B4DException;
import fr.B4D.classes.B4DException.Raison;
import fr.B4D.classes.Bot;
import fr.B4D.classes.PointF;
import net.sourceforge.tess4j.Tesseract;

public final class B4DEcran {

	  /*****************/
	 /* COULEUR PIXEL */
	/*****************/
	
	public static Color Couleur_Pixel(Point point) throws B4DException {
		try {
			return new Robot().getPixelColor(point.x, point.y);
		} catch (AWTException e) {
			throw new B4DException(Raison.Pixel);
		}
	}
	public static Color Couleur_Pixel(PointF pointF) throws B4DException {
		return Couleur_Pixel(B4DConversion.PointFToPoint(pointF));
	}
	
	  /*******************/
	 /* RECHERCHE PIXEL */
	/*******************/
	
	public static boolean Rechercher_Pixel(PointF P1, PointF P2, char R, boolean Rsupperior, char G, boolean Gsupperior, char B, boolean Bsupperior, PointF point) throws B4DException {
		
		Point Point1 = B4DConversion.PointFToPoint(P1);
		Point Point2 = B4DConversion.PointFToPoint(P2);
		int Largeur = Point2.x - Point1.x, Hauteur = Point2.y - Point1.y;
		
		Color color;
		
		for(int j=0;j<Hauteur;j++) {
			for(int i=0;i<Largeur;i++) {
				color = Couleur_Pixel(new Point(i, j));
				
				if((Rsupperior = true) != (color.getRed() > R) && (Gsupperior = true) != (color.getGreen() > G) && (Bsupperior = true) != (color.getBlue() > B)) {
					point = B4DConversion.PointToPointF(new Point(i+Point1.x, j+Point1.y));
					return true;
				}
			}
		}
		return false;
	}
	public static boolean Rechercher_Pixel(PointF P1, PointF P2, char R, boolean Rsupperior, char G, boolean Gsupperior, char B, boolean Bsupperior, ArrayList<PointF> points) throws B4DException {
		
		Point Point1 = B4DConversion.PointFToPoint(P1);
		Point Point2 = B4DConversion.PointFToPoint(P2);
		int Largeur = Point2.x - Point1.x, Hauteur = Point2.y - Point1.y;
		
		Color color;
		boolean trouve = false;
		
		for(int j=0;j<Hauteur;j++) {
			for(int i=0;i<Largeur;i++) {
				color = Couleur_Pixel(new Point(i, j));
				
				if((Rsupperior = true) != (color.getRed() > R) && (Gsupperior = true) != (color.getGreen() > G) && (Bsupperior = true) != (color.getBlue() > B)) {
					points.add(B4DConversion.PointToPointF(new Point(i+Point1.x, j+Point1.y)));
					trouve = true;
				}
			}
		}
		return trouve;
	}
	
	  /*******/
	 /* OCR */
	/*******/

	public static String OCR(Rectangle rectangle) throws B4DException {
		try {
			BufferedImage image = new Robot().createScreenCapture(rectangle);
			File file = File.createTempFile("screenshot", ".png");
			ImageIO.write(image, "png", file);
			Tesseract tessInst = new Tesseract();
			tessInst.setDatapath("./tessdata");
			return tessInst.doOCR(file);
		} catch (Exception e) {
			throw new B4DException(Raison.OCR);
		}
	}
	public static String OCR(Point P1, Point P2) throws B4DException {
		return OCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y));
	}
	public static String OCR(PointF P1, PointF P2) throws B4DException {
		return OCR(B4DConversion.PointFToPoint(P1), B4DConversion.PointFToPoint(P2));
	}
	public static String Analyse_Chat_OCR() throws B4DException {
		return OCR(Bot.MaConfiguration.zoneDeChat);
	}
	
	  /*************/
	 /* SELECTION */
	/*************/

	public static String Analyse_Selection(Point position) throws B4DException {
		Robot robot;
		try {
			robot = new Robot();
			B4DSouris.Clic_Droit(position, false);
			robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_A);
		    robot.keyRelease(KeyEvent.VK_A);
		    robot.keyPress(KeyEvent.VK_C);
		    robot.keyRelease(KeyEvent.VK_C);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		}catch (AWTException e) {
			throw new B4DException(Raison.Clavier);
		}
		return B4DClavier.RecupererPressePapier();
	}
	public static String Analyse_Selection(PointF position) throws B4DException {
		return Analyse_Selection(B4DConversion.PointFToPoint(position));
	}
	public static String Analyse_Chat_Clic() throws B4DException {
		return Analyse_Selection(new Point((int)(Bot.MaConfiguration.zoneDeChat.x + Bot.MaConfiguration.zoneDeChat.width*0.95), (int)(Bot.MaConfiguration.zoneDeChat.y + Bot.MaConfiguration.zoneDeChat.height*0.95)));
	}
}
