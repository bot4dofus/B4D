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
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.threads.ColorThread;
import fr.B4D.threads.OCRThread;
import fr.B4D.threads.PixelThread;
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
		String out = tessInst.doOCR(file);
		return out.replaceAll("\n", " ");
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

	public String getSelection(Point point) throws AWTException, UnsupportedFlavorException, IOException, StopProgramException, CancelProgramException {
		Robot robot = new Robot();
		B4D.mouse.leftClick(point, false, 100);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		B4D.wait.wait(1000);
		return B4D.keyboard.getClipboard();
	}
	public String getSelection(PointF position) throws AWTException, UnsupportedFlavorException, IOException, StopProgramException, CancelProgramException {
		return getSelection(B4D.converter.pointFToPoint(position));
	}
	
	  /************/
	 /** OTHERS **/
	/************/
	
	public boolean isBetween(Color couleur, Color min, Color max) {
		return (min.getRed() <= couleur.getRed() && couleur.getRed() <= max.getRed() && min.getGreen() <= couleur.getGreen() && couleur.getGreen() <= max.getGreen() && min.getBlue() <= couleur.getBlue() && couleur.getBlue() <= max.getBlue());
	}

	public void focusDofus() {
		// TODO Auto-generated method stub
		
	}
	
	  /*********************/
	 /** ATTENTE SUR MAP **/
	/*********************/
	
	public boolean waitForMap(int timeOut) throws StopProgramException, CancelProgramException {
		return waitForChangingPixel(B4D.converter.pointToPointF(configuration.getMinimap()), timeOut);
	}
	public boolean waitForMap() throws StopProgramException, CancelProgramException {
		return waitForMap(20000);
	}
	
	  /*********************/
	 /** ATTENTE SUR OCR **/
	/*********************/
	
	public String waitForOCR(Rectangle rectangle, String text, int timeOut) {
		OCRThread ocrThread = new OCRThread(rectangle, text);
		ocrThread.start();
		try {
			ocrThread.join(timeOut);
		} catch (InterruptedException e) {
			B4D.logger.error(e);
		}
		
		if(ocrThread.isAlive())
			ocrThread.interrupt();
		return ocrThread.getResult();
	}
	public String waitForOCR(Point P1, Point P2, String text, int timeOut) {
		return waitForOCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y), text, timeOut);
	}
	public String waitForOCR(PointF P1, PointF P2, String text, int timeOut) {
		return waitForOCR(B4D.converter.pointFToPoint(P1), B4D.converter.pointFToPoint(P2), text, timeOut);
	}
	
	  /*************************************/
	 /** ATTENTE SUR CHANGEMENT DE PIXEL **/
	/*************************************/
	
	public boolean waitForChangingPixel(Point point, int timeOut) {
		Thread pixelThread = new PixelThread(point);
		pixelThread.start();
		try {
			pixelThread.join(timeOut);
		} catch (InterruptedException e) {
			B4D.logger.error(e);
		}
		
		if(pixelThread.isAlive()) {
			pixelThread.interrupt();
			return true;
		}else
			return false;
	}
	public boolean waitForChangingPixel(PointF point, int timeOut) {
		return waitForChangingPixel(B4D.converter.pointFToPoint(point), timeOut);
	}
	
	  /**********************************/
	 /** ATTENTE SUR COULEUR DE PIXEL **/
	/**********************************/
	
	public boolean waitForColor(Point point, Color min, Color max, int timeOut) {
		Thread colorThread = new ColorThread(point, min, max);
		colorThread.start();
		try {
			colorThread.join(timeOut);
		} catch (InterruptedException e) {
			B4D.logger.error(e);
		}
		
		if(colorThread.isAlive()) {
			colorThread.interrupt();
			return true;
		}else
			return false;
	}
	public boolean waitForColor(PointF point, Color min, Color max, int timeOut) {
		return waitForColor(B4D.converter.pointFToPoint(point), min, max, timeOut);
	}
}
