package fr.B4D.modules.autre;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D.Double;

public final class B4DSouris {
		
	  /**********/
	 /* PLACER */
	/**********/
	
	public static void Placer(Point position, double timeOut) {
		try {
			new Robot().mouseMove((int)position.getX(),(int)position.getY());
		} catch (Exception e) {
			e.printStackTrace();
		}
		B4DAttente.Attendre(timeOut);
	}
	public static void Placer(Point position) {
		Placer(position, 0);
	}
	public static void Placer(Double position, double timeOut) {
		Placer(B4DConversion.DoubleToPoint(position), timeOut);
	}
	public static void Placer(Double position) {
		Placer(B4DConversion.DoubleToPoint(position), 0);
	}
	
	  /****************/
	 /* CLIQUE DROIT */
	/****************/
	
	public static void Clic_Droit(Point position, boolean maj, double timeOut) {
		Placer(position);
		try {
			Robot robot = new Robot();
			if(maj)
				robot.keyPress(KeyEvent.VK_SHIFT);
			robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
			robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);
			if(maj)	
				robot.keyRelease(KeyEvent.VK_SHIFT);
		} catch(Exception e){
			e.printStackTrace();			
		}
		B4DAttente.Attendre(timeOut);
	}
	public static void Clic_Droit(Point position, boolean maj){
		Clic_Droit(position, maj, 1);
	}
	
	  /*****************/
	 /* CLIQUE GAUCHE */
	/*****************/
	
	public static void Clic_Gauche(Point position, boolean maj, double timeOut) {
		Placer(position);
		try {
			Robot robot = new Robot();
			if(maj)
				robot.keyPress(KeyEvent.VK_SHIFT);
			robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
			if(maj)	
				robot.keyRelease(KeyEvent.VK_SHIFT);
		} catch(Exception e){
			e.printStackTrace();			
		}
		B4DAttente.Attendre(timeOut);
	}	
	public static void Clic_Gauche(Point position, boolean maj){
		Clic_Gauche(position, maj, 1);
	}
	
	  /************************/
	 /* DOUBLE CLIQUE GAUCHE */
	/************************/
	
	public static void Double_Clic_Gauche(Point position, boolean maj, double timeOut) {
		Placer(position);
		try {
			Robot robot = new Robot();
			if(maj)
				robot.keyPress(KeyEvent.VK_SHIFT);
			robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
			robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
			if(maj)	
				robot.keyRelease(KeyEvent.VK_SHIFT);
		} catch(Exception e){
			e.printStackTrace();			
		}
		B4DAttente.Attendre(timeOut);
	}	
	public static void Double_Clic_Gauche(Point position, boolean maj){
		Double_Clic_Gauche(position, maj, 1);
	}
	
	  /************************/
	 /* TRIPLE CLIQUE GAUCHE */
	/************************/
	
	public static void Triple_Clic_Gauche(Point position, boolean maj, double timeOut) {
		Placer(position);
		try {
			Robot robot = new Robot();
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
		} catch(Exception e){
			e.printStackTrace();			
		}
		B4DAttente.Attendre(timeOut);
	}	
	public static void Triple_Clic_Gauche(Point position, boolean maj){
		Triple_Clic_Gauche(position, maj, 1);
	}
}
