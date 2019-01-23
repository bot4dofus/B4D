package fr.B4D.bot.statics;

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
import fr.B4D.bot.Configuration;
import fr.B4D.modules.B4DWait;
import fr.B4D.utils.PointF;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public final class Screen {
	
	private Configuration configuration;
    
	/*************/
	/** BUILDER **/
	/*************/
    
    public Screen(Configuration configuration) {
    	this.configuration = configuration;
    }
	
	  /*******************/
	 /** COULEUR PIXEL **/
	/*******************/
	
	public Color getPixelColor(Point point) throws AWTException   {
		return new Robot().getPixelColor(point.x, point.y);
	}
	public Color getPixelColor(PointF pointF) throws AWTException {
		return getPixelColor(B4D.converter.pointFToPoint(pointF));
	}
	
	  /*********************/
	 /** RECHERCHE PIXEL **/
	/*********************/
	
	public PointF searchPixel(PointF P1, PointF P2, Color min, Color max) throws AWTException {
		Point Point1 = B4D.converter.pointFToPoint(P1);
		Point Point2 = B4D.converter.pointFToPoint(P2);
		
		Color color;
		Point point;
		
		for(int y=Point1.y;y<=Point2.y;y++) {
			for(int x=Point1.x;x<=Point2.x;x++) {
				point = new Point(x, y);
				color = getPixelColor(point);
				System.out.println(color + "     " + point);
				if(isBetween(color, min, max))
					return B4D.converter.pointToPointF(point);
			}
		}
		return null;
	}
	public ArrayList<PointF> searchPixels(PointF P1, PointF P2, Color min, Color max) throws AWTException {
		
		Point Point1 = B4D.converter.pointFToPoint(P1);
		Point Point2 = B4D.converter.pointFToPoint(P2);
		ArrayList<PointF> points = new ArrayList<PointF>();
		
		Color color;
		Point point;
		
		for(int y=Point1.y;y<=Point2.y;y++) {
			for(int x=Point1.x;x<=Point2.x;x++) {
				point = new Point(x, y);
				color = getPixelColor(point);
				
				if(isBetween(color, min, max))
					points.add(B4D.converter.pointToPointF(point));
			}
		}
		if (points.isEmpty())
			points = null;
		return points;
	}

	  /****************/
	 /** SCREENSHOT **/
	/****************/
	
	public BufferedImage takeSreenshot(Rectangle rectangle) throws AWTException {
		return new Robot().createScreenCapture(rectangle);
	}
	public BufferedImage takeSreenshot() throws AWTException {
		return new Robot().createScreenCapture(configuration.getGameFrame());
	}
	
	  /*********/
	 /** OCR **/
	/*********/
	
	public String OCR(Rectangle rectangle) throws AWTException, IOException, TesseractException {
		BufferedImage image = takeSreenshot(rectangle);
		File file = File.createTempFile("screenshot", ".png");
		ImageIO.write(image, "png", file);
		Tesseract tessInst = new Tesseract();
		tessInst.setLanguage("fra");
		return tessInst.doOCR(file);
	}
	public String OCR(Point P1, Point P2) throws AWTException, IOException, TesseractException {
		return OCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y));
	}
	public String OCR(PointF P1, PointF P2) throws AWTException, IOException, TesseractException {
		return OCR(B4D.converter.pointFToPoint(P1), B4D.converter.pointFToPoint(P2));
	}
	public String getChatOCR() throws AWTException, IOException, TesseractException  {
		return OCR(configuration.getChatFrame());
	}
	
	  /***************/
	 /** SELECTION **/
	/***************/

	public String getSelection(Point point) throws AWTException, UnsupportedFlavorException, IOException {
		Robot robot = new Robot();
		Mouse.leftClick(point, false, 0.1);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		B4DWait.wait(1.0);
		return Keyboard.getClipboard();
	}
	public String getSelection(PointF position) throws AWTException, UnsupportedFlavorException, IOException {
		return getSelection(B4D.converter.pointFToPoint(position));
	}
	
	  /***************/
	 /** OPERATORS **/
	/***************/
	
	public boolean isBetween(Color couleur, Color min, Color max) {
		return (min.getRed() <= couleur.getRed() && couleur.getRed() <= max.getRed() && min.getGreen() <= couleur.getGreen() && couleur.getGreen() <= max.getGreen() && min.getBlue() <= couleur.getBlue() && couleur.getBlue() <= max.getBlue());
	}
}
