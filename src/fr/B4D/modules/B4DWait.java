package fr.B4D.modules;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import fr.B4D.bot.Configuration;
import fr.B4D.threads.ColorThread;
import fr.B4D.threads.KeyboardThread;
import fr.B4D.threads.OCRThread;
import fr.B4D.threads.PixelThread;
import fr.B4D.utils.PointF;

public final class B4DWait {
	
	  /******************/
	 /* ATTENTE SIMPLE */
	/******************/
	
	public static void wait(double timeOut) {
		try {
			Thread.sleep((long) (timeOut*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	  /*******************/
	 /* ATTENTE SUR OCR */
	/*******************/
	
	public static String waitForOCR(Rectangle rectangle, String text, double timeOut) {
		OCRThread ocrThread = new OCRThread(rectangle, text);
		ocrThread.start();
		try {
			ocrThread.join((long) (timeOut*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(ocrThread.isAlive())
			ocrThread.interrupt();
		return ocrThread.getResult();
	}
	public static String waitForOCR(Point P1, Point P2, String text, double timeOut) {
		return waitForOCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y), text, timeOut);
	}
	public static String waitForOCR(PointF P1, PointF P2, String text, double timeOut) {
		return waitForOCR(B4DConversion.pointFToPoint(P1), B4DConversion.pointFToPoint(P2), text, timeOut);
	}
	
	  /**********************/
	 /* ATTENTE SUR TOUCHE */
	/**********************/
	
	public static boolean waitForKeyboard(double timeOut) {
		Thread keyboardThread = new KeyboardThread();
		keyboardThread.start();
		try {
			keyboardThread.join((long) (timeOut*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		
		if(keyboardThread.isAlive()) {
			keyboardThread.interrupt();
			return false;
		}else 
			return true;
	}
	
	  /*************************************/
	 /** ATTENTE SUR CHANGEMENT DE PIXEL **/
	/*************************************/
	
	public static boolean waitForChangingPixel(Point point, double timeOut) {
		Thread pixelThread = new PixelThread(point);
		pixelThread.start();
		try {
			pixelThread.join((long) (timeOut*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		
		if(pixelThread.isAlive()) {
			pixelThread.interrupt();
			return true;
		}else
			return false;
	}
	public static boolean waitForChangingPixel(PointF point, double timeOut) {
		return waitForChangingPixel(B4DConversion.pointFToPoint(point), timeOut);
	}
	
	  /**********************************/
	 /** ATTENTE SUR COULEUR DE PIXEL **/
	/**********************************/
	
	public static boolean waitForColor(Point point, Color min, Color max, double timeOut) {
		Thread colorThread = new ColorThread(point, min, max);
		colorThread.start();
		try {
			colorThread.join((long) (timeOut*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		
		if(colorThread.isAlive()) {
			colorThread.interrupt();
			return true;
		}else
			return false;
	}
	public static boolean waitForColor(PointF point, Color min, Color max, double timeOut) {
		return waitForColor(B4DConversion.pointFToPoint(point), min, max, timeOut);
	}
	
	  /*********************/
	 /** ATTENTE SUR MAP **/
	/*********************/
	
	public static boolean waitForMap(double timeOut) {
		return waitForChangingPixel(B4DConversion.pointToPointF(Configuration.getInstance().minimap), timeOut);
	}
	public static boolean waitForMap() {
		return waitForMap(15);
	}
}
