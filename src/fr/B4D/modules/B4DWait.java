package fr.B4D.modules.autre;

import java.awt.Point;
import java.awt.Rectangle;
import fr.B4D.classes.PointF;
import fr.B4D.threads.ThreadAttente;
import fr.B4D.threads.ThreadChangementPixel;
import fr.B4D.threads.ThreadOCR;
import fr.B4D.threads.ThreadTouche;

public final class B4DAttente {
	
	  /******************/
	 /* ATTENTE SIMPLE */
	/******************/
	
	public static void Attendre(Double timeOut) {
		try {
			Thread.sleep((long) (timeOut*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	  /*******************/
	 /* ATTENTE SUR OCR */
	/*******************/
	
	public static boolean Attendre_OCR(Rectangle rectangle, String texte, Double timeOut) {
		Thread attente = new ThreadAttente(timeOut);
		Thread OCR = new ThreadOCR(rectangle, texte);
		attente.start();
		OCR.start();
		
		while(attente.isAlive() && OCR.isAlive());
		if(attente.isAlive()) {
			attente.interrupt();
			return true;
		}else {
			OCR.interrupt();
			return false;
		}
	}
	public static boolean Attendre_OCR(Point P1, Point P2, String texte, Double timeOut) {
		return Attendre_OCR(new Rectangle(P1.x,  P1.y, P2.x - P1.x, P2.y - P1.y), texte, timeOut);
	}
	public static boolean Attendre_OCR(PointF P1, PointF P2, String texte, Double timeOut) {
		return Attendre_OCR(B4DConversion.PointFToPoint(P1), B4DConversion.PointFToPoint(P2), texte, timeOut);
	}
	
	  /**********************/
	 /* ATTENTE SUR TOUCHE */
	/**********************/
	
	public static boolean Attendre_Touche(Double timeOut) {
		Thread attente = new ThreadAttente(timeOut);
		Thread Touche = new ThreadTouche();
		attente.start();
		Touche.start();
		
		while(attente.isAlive() && Touche.isAlive());
		if(attente.isAlive()) {
			attente.interrupt();
			return true;
		}else {
			Touche.interrupt();
			return false;
		}
	}
	
	  /*********************/
	 /* ATTENTE SUR PIXEL */
	/*********************/
	
	public static boolean Attendre_Changement_Pixel(Point point, Double timeOut) {
		Thread attente = new ThreadAttente(timeOut);
		Thread pixel = new ThreadChangementPixel(point);
		attente.start();
		pixel.start();
		
		while(attente.isAlive() && pixel.isAlive());
		if(attente.isAlive()) {
			attente.interrupt();
			return true;
		}else {
			pixel.interrupt();
			return false;
		}
	}
	public static boolean Attendre_Changement_Pixel(PointF point, Double timeOut) {
		return Attendre_Changement_Pixel(B4DConversion.PointFToPoint(point), timeOut);
	}
}
