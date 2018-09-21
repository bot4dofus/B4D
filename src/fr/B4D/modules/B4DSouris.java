package fr.B4D.modules;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import fr.B4D.classes.PointD;
import fr.B4D.classes.PointF;

public final class B4DSouris {
		
	  /************/
	 /** PLACER **/
	/**
	 * @throws AWTException **********/
	
	//Point
	public static void Placer(Point position, double attente) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
		B4DWait.wait(attente);
	}
	public static void Placer(Point position) throws AWTException {
		Placer(position, 0);
	}
	//PointF
	public static void Placer(PointF position, double attente) throws AWTException {
		Placer(B4DConversion.pointFToPoint(position), attente);
	}
	public static void Placer(PointF position) throws AWTException {
		Placer(B4DConversion.pointFToPoint(position), 0);
	}
	//PointD
	public static void Placer(PointD position, double attente) throws AWTException {
		Placer(B4DConversion.pointDToPoint(position), attente);
	}
	public static void Placer(PointD position) throws AWTException {
		Placer(B4DConversion.pointDToPoint(position), 0);
	}
	
	  /******************/
	 /** CLIQUE DROIT **/
	/******************/
	
	//Point
	public static void Clic_Droit(Point position, boolean maj, double timeOut) throws AWTException{
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
			
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4DWait.wait(timeOut);		
	}
	public static void Clic_Droit(Point position, boolean maj) throws AWTException{
		Clic_Droit(position, maj, 1);
	}
	public static void Clic_Droit(PointF position, boolean maj, double timeOut) throws AWTException{
		Clic_Droit(B4DConversion.pointFToPoint(position), maj, timeOut);
	}
	public static void Clic_Droit(PointF position, boolean maj) throws AWTException{
		Clic_Droit(B4DConversion.pointFToPoint(position), maj, 1);
	}
	public static void Clic_Droit(PointD position, boolean maj, double timeOut) throws AWTException{
		Clic_Droit(B4DConversion.pointDToPoint(position), maj, timeOut);
	}
	public static void Clic_Droit(PointD position, boolean maj) throws AWTException{
		Clic_Droit(B4DConversion.pointDToPoint(position), maj, 1);
	}
	
	  /*******************/
	 /** CLIQUE GAUCHE **/
	/*******************/
	
	public static void Clic_Gauche(Point position, boolean maj, double timeOut) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
			
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4DWait.wait(timeOut);
	}	
	public static void Clic_Gauche(Point position, boolean maj) throws AWTException{
		Clic_Gauche(position, maj, 1);
	}	
	public static void Clic_Gauche(PointF position, boolean maj, double timeOut) throws AWTException{
		Clic_Gauche(B4DConversion.pointFToPoint(position), maj, timeOut);
	}
	public static void Clic_Gauche(PointF position, boolean maj) throws AWTException{
		Clic_Gauche(B4DConversion.pointFToPoint(position), maj, 1);
	}
	public static void Clic_Gauche(PointD position, boolean maj, double timeOut) throws AWTException{
		Clic_Gauche(B4DConversion.pointDToPoint(position), maj, timeOut);
	}
	public static void Clic_Gauche(PointD position, boolean maj) throws AWTException{
		Clic_Gauche(B4DConversion.pointDToPoint(position), maj, 1);
	}
	
	  /**************************/
	 /** DOUBLE CLIQUE GAUCHE **/
	/**************************/
	
	public static void Double_Clic_Gauche(Point position, boolean maj, double timeOut) throws AWTException {
		Clic_Gauche(position, maj, 0);
		Clic_Gauche(position, maj, timeOut);
	}	
	public static void Double_Clic_Gauche(Point position, boolean maj) throws AWTException{
		Double_Clic_Gauche(position, maj, 1);
	}
	public static void Double_Clic_Gauche(PointF position, boolean maj, double timeOut) throws AWTException{
		Double_Clic_Gauche(B4DConversion.pointFToPoint(position), maj, timeOut);
	}
	public static void Double_Clic_Gauche(PointF position, boolean maj) throws AWTException{
		Double_Clic_Gauche(B4DConversion.pointFToPoint(position), maj, 1);
	}
	public static void Double_Clic_Gauche(PointD position, boolean maj, double timeOut) throws AWTException{
		Double_Clic_Gauche(B4DConversion.pointDToPoint(position), maj, timeOut);
	}
	public static void Double_Clic_Gauche(PointD position, boolean maj) throws AWTException{
		Double_Clic_Gauche(B4DConversion.pointDToPoint(position), maj, 1);
	}
	
	  /**************************/
	 /** TRIPLE CLIQUE GAUCHE **/
	/**************************/
	
	public static void Triple_Clic_Gauche(Point position, boolean maj, double timeOut) throws AWTException {
		Clic_Gauche(position, maj, 0);
		Clic_Gauche(position, maj, 0);
		Clic_Gauche(position, maj, timeOut);
	}	
	public static void Triple_Clic_Gauche(Point position, boolean maj) throws AWTException{
		Triple_Clic_Gauche(position, maj, 1);
	}
	public static void Triple_Clic_Gauche(PointF position, boolean maj, double timeOut) throws AWTException{
		Triple_Clic_Gauche(B4DConversion.pointFToPoint(position), maj, timeOut);
	}
	public static void Triple_Clic_Gauche(PointF position, boolean maj) throws AWTException{
		Triple_Clic_Gauche(B4DConversion.pointFToPoint(position), maj, 1);
	}
	public static void Triple_Clic_Gauche(PointD position, boolean maj, double timeOut) throws AWTException{
		Triple_Clic_Gauche(B4DConversion.pointDToPoint(position), maj, timeOut);
	}
	public static void Triple_Clic_Gauche(PointD position, boolean maj) throws AWTException{
		Triple_Clic_Gauche(B4DConversion.pointDToPoint(position), maj, 1);
	}
}
