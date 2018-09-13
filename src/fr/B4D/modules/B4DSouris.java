package fr.B4D.modules.autre;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import fr.B4D.classes.B4DException;
import fr.B4D.classes.B4DException.Raison;
import fr.B4D.classes.PointD;
import fr.B4D.classes.PointF;

public final class B4DSouris {
		
	  /************/
	 /** PLACER **/
	/************/
	
	//Point
	public static void Placer(Point position, double attente) throws B4DException {
		Robot robot;
		try {
			robot = new Robot();
			robot.mouseMove((int)position.getX(),(int)position.getY());
			B4DAttente.Attendre(attente);
		} catch (AWTException e) {
			throw new B4DException(Raison.PlacerSouris);
		}
	}
	public static void Placer(Point position) throws B4DException {
		Placer(position, 0);
	}
	//PointF
	public static void Placer(PointF position, double attente) throws B4DException {
		Placer(B4DConversion.PointFToPoint(position), attente);
	}
	public static void Placer(PointF position) throws B4DException {
		Placer(B4DConversion.PointFToPoint(position), 0);
	}
	//PointD
	public static void Placer(PointD position, double attente) throws B4DException {
		Placer(B4DConversion.PointDToPoint(position), attente);
	}
	public static void Placer(PointD position) throws B4DException {
		Placer(B4DConversion.PointDToPoint(position), 0);
	}
	
	  /******************/
	 /** CLIQUE DROIT **/
	/******************/
	
	//Point
	public static void Clic_Droit(Point position, boolean maj, double timeOut) throws B4DException{
		Robot robot;
		try {
			robot = new Robot();
			robot.mouseMove((int)position.getX(),(int)position.getY());
			
			if(maj)
				robot.keyPress(KeyEvent.VK_SHIFT);
			robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
			robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);
			if(maj)	
				robot.keyRelease(KeyEvent.VK_SHIFT);

			B4DAttente.Attendre(timeOut);
		} catch (AWTException e) {
			throw new B4DException(Raison.ClicDroit);
		}
		
	}
	public static void Clic_Droit(Point position, boolean maj) throws B4DException{
		Clic_Droit(position, maj, 1);
	}
	public static void Clic_Droit(PointF position, boolean maj, double timeOut) throws B4DException{
		Clic_Droit(B4DConversion.PointFToPoint(position), maj, timeOut);
	}
	public static void Clic_Droit(PointF position, boolean maj) throws B4DException{
		Clic_Droit(B4DConversion.PointFToPoint(position), maj, 1);
	}
	public static void Clic_Droit(PointD position, boolean maj, double timeOut) throws B4DException{
		Clic_Droit(B4DConversion.PointDToPoint(position), maj, timeOut);
	}
	public static void Clic_Droit(PointD position, boolean maj) throws B4DException{
		Clic_Droit(B4DConversion.PointDToPoint(position), maj, 1);
	}
	
	  /*******************/
	 /** CLIQUE GAUCHE **/
	/*******************/
	
	public static void Clic_Gauche(Point position, boolean maj, double timeOut) throws B4DException {
		Robot robot;
		try {
			robot = new Robot();
			robot.mouseMove((int)position.getX(),(int)position.getY());
			
			if(maj)
				robot.keyPress(KeyEvent.VK_SHIFT);
			robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
			if(maj)	
				robot.keyRelease(KeyEvent.VK_SHIFT);

			B4DAttente.Attendre(timeOut);
		} catch (AWTException e) {
			throw new B4DException(Raison.ClicGauche);
		}
	}	
	public static void Clic_Gauche(Point position, boolean maj) throws B4DException{
		Clic_Gauche(position, maj, 1);
	}	
	public static void Clic_Gauche(PointF position, boolean maj, double timeOut) throws B4DException{
		Clic_Gauche(B4DConversion.PointFToPoint(position), maj, timeOut);
	}
	public static void Clic_Gauche(PointF position, boolean maj) throws B4DException{
		Clic_Gauche(B4DConversion.PointFToPoint(position), maj, 1);
	}
	public static void Clic_Gauche(PointD position, boolean maj, double timeOut) throws B4DException{
		Clic_Gauche(B4DConversion.PointDToPoint(position), maj, timeOut);
	}
	public static void Clic_Gauche(PointD position, boolean maj) throws B4DException{
		Clic_Gauche(B4DConversion.PointDToPoint(position), maj, 1);
	}
	
	  /**************************/
	 /** DOUBLE CLIQUE GAUCHE **/
	/**************************/
	
	public static void Double_Clic_Gauche(Point position, boolean maj, double timeOut) throws B4DException {
		Clic_Gauche(position, maj, 0);
		Clic_Gauche(position, maj, timeOut);
	}	
	public static void Double_Clic_Gauche(Point position, boolean maj) throws B4DException{
		Double_Clic_Gauche(position, maj, 1);
	}
	public static void Double_Clic_Gauche(PointF position, boolean maj, double timeOut) throws B4DException{
		Double_Clic_Gauche(B4DConversion.PointFToPoint(position), maj, timeOut);
	}
	public static void Double_Clic_Gauche(PointF position, boolean maj) throws B4DException{
		Double_Clic_Gauche(B4DConversion.PointFToPoint(position), maj, 1);
	}
	public static void Double_Clic_Gauche(PointD position, boolean maj, double timeOut) throws B4DException{
		Double_Clic_Gauche(B4DConversion.PointDToPoint(position), maj, timeOut);
	}
	public static void Double_Clic_Gauche(PointD position, boolean maj) throws B4DException{
		Double_Clic_Gauche(B4DConversion.PointDToPoint(position), maj, 1);
	}
	
	  /**************************/
	 /** TRIPLE CLIQUE GAUCHE **/
	/**************************/
	
	public static void Triple_Clic_Gauche(Point position, boolean maj, double timeOut) throws B4DException {
		Clic_Gauche(position, maj, 0);
		Clic_Gauche(position, maj, 0);
		Clic_Gauche(position, maj, timeOut);
	}	
	public static void Triple_Clic_Gauche(Point position, boolean maj) throws B4DException{
		Triple_Clic_Gauche(position, maj, 1);
	}
	public static void Triple_Clic_Gauche(PointF position, boolean maj, double timeOut) throws B4DException{
		Triple_Clic_Gauche(B4DConversion.PointFToPoint(position), maj, timeOut);
	}
	public static void Triple_Clic_Gauche(PointF position, boolean maj) throws B4DException{
		Triple_Clic_Gauche(B4DConversion.PointFToPoint(position), maj, 1);
	}
	public static void Triple_Clic_Gauche(PointD position, boolean maj, double timeOut) throws B4DException{
		Triple_Clic_Gauche(B4DConversion.PointDToPoint(position), maj, timeOut);
	}
	public static void Triple_Clic_Gauche(PointD position, boolean maj) throws B4DException{
		Triple_Clic_Gauche(B4DConversion.PointDToPoint(position), maj, 1);
	}
}
