package fr.B4D.modules;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import fr.B4D.classes.Bot;
import fr.B4D.classes.PointF;
import fr.B4D.threads.WaitThread;
import fr.B4D.threads.PixelThread;
import fr.B4D.threads.ColorThread;
import fr.B4D.threads.OCRThread;
import fr.B4D.threads.KeyboardThread;

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
	
	public static boolean waitForOCR(Rectangle rectangle, String text, double timeOut) {
		Thread waitThread = new WaitThread(timeOut);
		Thread OCRThread = new OCRThread(rectangle, text);
		waitThread.start();
		OCRThread.start();
		
		while(waitThread.isAlive() && OCRThread.isAlive());
		if(waitThread.isAlive()) {
			waitThread.interrupt();
			return true;
		}else {
			OCRThread.interrupt();
			return false;
		}
	}
	public static boolean waitForOCR(Point P1, Point P2, String text, double timeOut) {
		return waitForOCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y), text, timeOut);
	}
	public static boolean waitForOCR(PointF P1, PointF P2, String text, double timeOut) {
		return waitForOCR(B4DConversion.pointFToPoint(P1), B4DConversion.pointFToPoint(P2), text, timeOut);
	}
	
	  /**********************/
	 /* ATTENTE SUR TOUCHE */
	/**********************/
	
	public static boolean waitForKeyboard(double timeOut) {
		Thread waitThread = new WaitThread(timeOut);
		Thread keyboardThread = new KeyboardThread();
		waitThread.start();
		keyboardThread.start();
		
		while(waitThread.isAlive() && keyboardThread.isAlive());
		if(waitThread.isAlive()) {
			waitThread.interrupt();
			return true;
		}else {
			keyboardThread.interrupt();
			return false;
		}
	}
	
	  /*************************************/
	 /** ATTENTE SUR CHANGEMENT DE PIXEL **/
	/*************************************/
	
	public static boolean waitForChangingPixel(Point point, double timeOut) {
		Thread waitThread = new WaitThread(timeOut);
		Thread pixelThread = new PixelThread(point);
		waitThread.start();
		pixelThread.start();
		
		while(waitThread.isAlive() && pixelThread.isAlive());
		if(waitThread.isAlive()) {
			waitThread.interrupt();
			return true;
		}else {
			pixelThread.interrupt();
			return false;
		}
	}
	public static boolean waitForChangingPixel(PointF point, double timeOut) {
		return waitForChangingPixel(B4DConversion.pointFToPoint(point), timeOut);
	}
	
	  /**********************************/
	 /** ATTENTE SUR COULEUR DE PIXEL **/
	/**********************************/
	
	public static boolean waitForColor(Point point, Color min, Color max, double timeOut) {
		Thread waitThread = new WaitThread(timeOut);
		Thread colorThread = new ColorThread(point, min, max);
		waitThread.start();
		colorThread.start();
		
		while(waitThread.isAlive() && colorThread.isAlive());
		if(waitThread.isAlive()) {
			waitThread.interrupt();
			return true;
		}else {
			colorThread.interrupt();
			return false;
		}
	}
	public static boolean waitForColor(PointF point, Color min, Color max, double timeOut) {
		return waitForColor(B4DConversion.pointFToPoint(point), min, max, timeOut);
	}
	
	  /*********************/
	 /** ATTENTE SUR MAP **/
	/*********************/
	
	public static boolean waitForMap(double timeOut) {
		return waitForChangingPixel(B4DConversion.pointToPointF(Bot.MyConfiguration.minimap), timeOut);
	}
	public static boolean waitForMap() {
		return waitForMap(15);
	}
}
