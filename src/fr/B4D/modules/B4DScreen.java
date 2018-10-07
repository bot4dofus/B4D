package fr.B4D.modules;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import fr.B4D.classes.Bot;
import fr.B4D.classes.PointF;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public final class B4DScreen {

	  /*****************/
	 /* COULEUR PIXEL */
	/*****************/
	
	public static Color getPixelColor(Point point) throws AWTException   {
		return new Robot().getPixelColor(point.x, point.y);
	}
	public static Color getPixelColor(PointF pointF) throws AWTException {
		return getPixelColor(B4DConversion.pointFToPoint(pointF));
	}
	
	  /*******************/
	 /* RECHERCHE PIXEL */
	/*******************/
	
	public static boolean searchPixel(PointF P1, PointF P2, Color min, Color max, PointF point) throws AWTException {
		
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
	public static boolean searchPixels(PointF P1, PointF P2, Color min, Color max, ArrayList<PointF> points) throws AWTException {
		
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

	public static String OCR(Rectangle rectangle) throws AWTException, IOException, TesseractException {
		BufferedImage image = new Robot().createScreenCapture(rectangle);
		File file = File.createTempFile("screenshot", ".png");
		ImageIO.write(image, "png", file);
		Tesseract tessInst = new Tesseract();
		tessInst.setDatapath("./tessdata");
		return tessInst.doOCR(file);
	}
	public static String OCR(Point P1, Point P2) throws AWTException, IOException, TesseractException {
		return OCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y));
	}
	public static String OCR(PointF P1, PointF P2) throws AWTException, IOException, TesseractException {
		return OCR(B4DConversion.pointFToPoint(P1), B4DConversion.pointFToPoint(P2));
	}
	public static String getChatOCR() throws AWTException, IOException, TesseractException  {
		return OCR(Bot.configuration.chatFrame);
	}
	
	  /*************/
	 /* SELECTION */
	/*************/

	public static String getSelection(Point point) throws AWTException, UnsupportedFlavorException, IOException {
		Robot robot = new Robot();
		B4DMouse.rightClick(point, false);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		return B4DKeyboard.RecupererPressePapier();
	}
	public static String getSelection(PointF position) throws AWTException, UnsupportedFlavorException, IOException {
		return getSelection(B4DConversion.pointFToPoint(position));
	}
	public static String getChatSelection() throws AWTException, UnsupportedFlavorException, IOException  {
		return getSelection(new Point((int)(Bot.configuration.chatFrame.x + Bot.configuration.chatFrame.width*0.95), (int)(Bot.configuration.chatFrame.y + Bot.configuration.chatFrame.height*0.95)));
	}
}
