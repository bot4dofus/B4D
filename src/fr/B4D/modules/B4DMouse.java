package fr.B4D.modules;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import fr.B4D.classes.PointD;
import fr.B4D.classes.PointF;
import fr.B4D.gui.JFrame_GetPoint;
import fr.B4D.gui.JFrame_GetPointImage;

public final class B4DSouris {
		
	  /***************/
	 /** GET POINT **/
	/***************/

	public static void getPoint(String text, MouseAdapter mouseAdapter) {
		JFrame_GetPoint window = new JFrame_GetPoint(text, mouseAdapter);
		window.frame.setVisible(true);
	}
	public static void getPoint(String text, ImageIcon image, MouseAdapter mouseAdapter) {
		JFrame_GetPointImage window = new JFrame_GetPointImage(text, image, mouseAdapter);
		window.frame.setVisible(true);
	}
	
	public static void getPoints(String text1, MouseAdapter mouseAdapter1, String text2, MouseAdapter mouseAdapter2) {
		JFrame_GetPoint window1 = new JFrame_GetPoint(text1, mouseAdapter1);
		JFrame_GetPoint window2 = new JFrame_GetPoint(text2, mouseAdapter2);
		window1.frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				window2.frame.setVisible(true);
			}
		});
		window1.frame.setVisible(true);
	}
	public static void getPoints(String text1, ImageIcon image1, MouseAdapter mouseAdapter1, String text2, ImageIcon image2, MouseAdapter mouseAdapter2) {
		JFrame_GetPointImage window1 = new JFrame_GetPointImage(text1, image1, mouseAdapter1);
		JFrame_GetPointImage window2 = new JFrame_GetPointImage(text2, image2, mouseAdapter2);
		window1.frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				window2.frame.setVisible(true);
			}
		});
		window1.frame.setVisible(true);
	}
	
	  /************/
	 /** PLACER **/
	/************/
	
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
