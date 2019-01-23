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

import fr.B4D.bot.B4D;
import fr.B4D.utils.PointF;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public final class B4DScreen {
	
	  /*******************/
	 /** COULEUR PIXEL **/
	/*******************/
	
	public static Color getPixelColor(Point point) throws AWTException   {
		return new Robot().getPixelColor(point.x, point.y);
	}
	public static Color getPixelColor(PointF pointF) throws AWTException {
		return getPixelColor(B4DConversion.pointFToPoint(pointF));
	}
	
	  /*********************/
	 /** RECHERCHE PIXEL **/
	/*********************/
	
	public static PointF searchPixel(PointF P1, PointF P2, Color min, Color max) throws AWTException {
		Point Point1 = B4DConversion.pointFToPoint(P1);
		Point Point2 = B4DConversion.pointFToPoint(P2);
		
		Color color;
		Point point;
		
		for(int y=Point1.y;y<=Point2.y;y++) {
			for(int x=Point1.x;x<=Point2.x;x++) {
				point = new Point(x, y);
				color = getPixelColor(point);
				System.out.println(color + "     " + point);
				if(B4DOperator.isBetween(color, min, max))
					return B4DConversion.pointToPointF(point);
			}
		}
		return null;
	}
	public static ArrayList<PointF> searchPixels(PointF P1, PointF P2, Color min, Color max) throws AWTException {
		
		Point Point1 = B4DConversion.pointFToPoint(P1);
		Point Point2 = B4DConversion.pointFToPoint(P2);
		ArrayList<PointF> points = new ArrayList<PointF>();
		
		Color color;
		Point point;
		
		for(int y=Point1.y;y<=Point2.y;y++) {
			for(int x=Point1.x;x<=Point2.x;x++) {
				point = new Point(x, y);
				color = getPixelColor(point);
				
				if(B4DOperator.isBetween(color, min, max))
					points.add(B4DConversion.pointToPointF(point));
			}
		}
		if (points.isEmpty())
			points = null;
		return points;
	}

	  /****************/
	 /** SCREENSHOT **/
	/****************/
	
	public static BufferedImage takeSreenshot(Rectangle rectangle) throws AWTException {
		return new Robot().createScreenCapture(rectangle);
	}
	public static BufferedImage takeSreenshot() throws AWTException {
		return new Robot().createScreenCapture(B4D.getConfiguration().getGameFrame());
	}
	
	  /*********/
	 /** OCR **/
	/*********/
	
	public static String OCR(Rectangle rectangle) throws AWTException, IOException, TesseractException {
		BufferedImage image = takeSreenshot(rectangle);
		File file = File.createTempFile("screenshot", ".png");
		ImageIO.write(image, "png", file);
		Tesseract tessInst = new Tesseract();
		tessInst.setLanguage("fra");
		return tessInst.doOCR(file);
	}
	public static String OCR(Point P1, Point P2) throws AWTException, IOException, TesseractException {
		return OCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y));
	}
	public static String OCR(PointF P1, PointF P2) throws AWTException, IOException, TesseractException {
		return OCR(B4DConversion.pointFToPoint(P1), B4DConversion.pointFToPoint(P2));
	}
	public static String getChatOCR() throws AWTException, IOException, TesseractException  {
		return OCR(B4D.getConfiguration().getChatFrame());
	}
	
	  /***************/
	 /** SELECTION **/
	/***************/

	public static String getSelection(Point point) throws AWTException, UnsupportedFlavorException, IOException {
		Robot robot = new Robot();
		B4DMouse.leftClick(point, false, 0.1);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		B4DWait.wait(1.0);
		return B4DKeyboard.getClipboard();
	}
	public static String getSelection(PointF position) throws AWTException, UnsupportedFlavorException, IOException {
		return getSelection(B4DConversion.pointFToPoint(position));
	}
}
