package fr.B4D.modules;

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
import fr.B4D.classes.B4DException.Reason;
import fr.B4D.classes.Bot;
import fr.B4D.classes.PointF;
import net.sourceforge.tess4j.Tesseract;

public final class B4DScreen {

	  /*****************/
	 /* COULEUR PIXEL */
	/*****************/
	
	public static Color getPixelColor(Point point) throws B4DException {
		try {
			return new Robot().getPixelColor(point.x, point.y);
		} catch (AWTException e) {
			throw new B4DException(Reason.Pixel);
		}
	}
	public static Color getPixelColor(PointF pointF) throws B4DException {
		return getPixelColor(B4DConversion.pointFToPoint(pointF));
	}
	
	  /*******************/
	 /* RECHERCHE PIXEL */
	/*******************/
	
	public static boolean searchPixel(PointF P1, PointF P2, Color min, Color max, PointF point) throws B4DException {
		
		Point Point1 = B4DConversion.pointFToPoint(P1);
		Point Point2 = B4DConversion.pointFToPoint(P2);
		int width = Point2.x - Point1.x, height = Point2.y - Point1.y;
		
		Color color;
		
		for(int j=0;j<height;j++) {
			for(int i=0;i<width;i++) {
				color = getPixelColor(new Point(i, j));
				
				if(B4DOperator.isBetween(color, min, max)) {
					point = B4DConversion.pointToPointF(new Point(i+Point1.x, j+Point1.y));
					return true;
				}
			}
		}
		return false;
	}
	public static boolean searchPixels(PointF P1, PointF P2, Color min, Color max, ArrayList<PointF> points) throws B4DException {
		
		Point Point1 = B4DConversion.pointFToPoint(P1);
		Point Point2 = B4DConversion.pointFToPoint(P2);
		int width = Point2.x - Point1.x, height = Point2.y - Point1.y;
		
		Color color;
		boolean found = false;
		
		for(int j=0;j<height;j++) {
			for(int i=0;i<width;i++) {
				color = getPixelColor(new Point(i, j));
				
				if(B4DOperator.isBetween(color, min, max)) {
					points.add(B4DConversion.pointToPointF(new Point(i+Point1.x, j+Point1.y)));
					found = true;
				}
			}
		}
		return found;
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
			throw new B4DException(Reason.OCR);
		}
	}
	public static String OCR(Point P1, Point P2) throws B4DException {
		return OCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y));
	}
	public static String OCR(PointF P1, PointF P2) throws B4DException {
		return OCR(B4DConversion.pointFToPoint(P1), B4DConversion.pointFToPoint(P2));
	}
	public static String getChatOCR() throws B4DException {
		return OCR(Bot.MyConfiguration.chatFrame);
	}
	
	  /*************/
	 /* SELECTION */
	/*************/

	public static String getSelection(Point point) throws B4DException {
		Robot robot;
		try {
			robot = new Robot();
			B4DSouris.Clic_Droit(point, false);
			robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_A);
		    robot.keyRelease(KeyEvent.VK_A);
		    robot.keyPress(KeyEvent.VK_C);
		    robot.keyRelease(KeyEvent.VK_C);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		}catch (AWTException e) {
			throw new B4DException(Reason.Keyboard);
		}
		return B4DKeyboard.RecupererPressePapier();
	}
	public static String getSelection(PointF position) throws B4DException {
		return getSelection(B4DConversion.pointFToPoint(position));
	}
	public static String getChatSelection() throws B4DException {
		return getSelection(new Point((int)(Bot.MyConfiguration.chatFrame.x + Bot.MyConfiguration.chatFrame.width*0.95), (int)(Bot.MyConfiguration.chatFrame.y + Bot.MyConfiguration.chatFrame.height*0.95)));
	}
}
