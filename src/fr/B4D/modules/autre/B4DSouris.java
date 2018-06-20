package fr.B4D.modules.autre;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D.Double;

public final class B4DSouris {
		
	  /**********/
	 /* PLACER */
	/* ********/
	
	public static void Placer(Point position, double timeOut) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
		B4DAttente.Attendre(timeOut);
	}
	public static void Placer(Point position) throws AWTException {
		Placer(position, 0);
	}
	public static void Placer(Double position, double timeOut) throws AWTException {
		Placer(B4DConversion.DoubleToPoint(position), timeOut);
	}
	public static void Placer(Double position) throws AWTException {
		Placer(B4DConversion.DoubleToPoint(position), 0);
	}
	
	  /****************/
	 /* CLIQUE DROIT */
	/****************/
	
	public static void Clic_Droit(Point position, boolean maj, double timeOut) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
		
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4DAttente.Attendre(timeOut);
	}
	public static void Clic_Droit(Point position, boolean maj) throws AWTException{
		Clic_Droit(position, maj, 1);
	}
	public static void Clic_Droit(Double position, boolean maj, double timeOut) throws AWTException{
		Clic_Droit(B4DConversion.DoubleToPoint(position), maj, timeOut);
	}
	public static void Clic_Droit(Double position, boolean maj) throws AWTException{
		Clic_Droit(B4DConversion.DoubleToPoint(position), maj, 1);
	}
	
	  /*****************/
	 /* CLIQUE GAUCHE */
	/*****************/
	
	public static void Clic_Gauche(Point position, boolean maj, double timeOut) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
		
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4DAttente.Attendre(timeOut);
	}	
	public static void Clic_Gauche(Point position, boolean maj) throws AWTException{
		Clic_Gauche(position, maj, 1);
	}	
	public static void Clic_Gauche(Double position, boolean maj, double timeOut) throws AWTException{
		Clic_Gauche(B4DConversion.DoubleToPoint(position), maj, timeOut);
	}
	public static void Clic_Gauche(Double position, boolean maj) throws AWTException{
		Clic_Gauche(B4DConversion.DoubleToPoint(position), maj, 1);
	}
	
	  /************************/
	 /* DOUBLE CLIQUE GAUCHE */
	/************************/
	
	public static void Double_Clic_Gauche(Point position, boolean maj, double timeOut) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
		
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4DAttente.Attendre(timeOut);
	}	
	public static void Double_Clic_Gauche(Point position, boolean maj) throws AWTException{
		Double_Clic_Gauche(position, maj, 1);
	}
	public static void Double_Clic_Gauche(Double position, boolean maj, double timeOut) throws AWTException{
		Double_Clic_Gauche(B4DConversion.DoubleToPoint(position), maj, timeOut);
	}
	public static void Double_Clic_Gauche(Double position, boolean maj) throws AWTException{
		Double_Clic_Gauche(B4DConversion.DoubleToPoint(position), maj, 1);
	}
	
	  /************************/
	 /* TRIPLE CLIQUE GAUCHE */
	/************************/
	
	public static void Triple_Clic_Gauche(Point position, boolean maj, double timeOut) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
		
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4DAttente.Attendre(timeOut);
	}	
	public static void Triple_Clic_Gauche(Point position, boolean maj) throws AWTException{
		Triple_Clic_Gauche(position, maj, 1);
	}
	public static void Triple_Clic_Gauche(Double position, boolean maj, double timeOut) throws AWTException{
		Triple_Clic_Gauche(B4DConversion.DoubleToPoint(position), maj, timeOut);
	}
	public static void Triple_Clic_Gauche(Double position, boolean maj) throws AWTException{
		Triple_Clic_Gauche(B4DConversion.DoubleToPoint(position), maj, 1);
	}
}
