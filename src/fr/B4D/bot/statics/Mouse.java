package fr.B4D.bot.statics;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import fr.B4D.bot.B4D;
import fr.B4D.gui.JFrame_GetPoint;
import fr.B4D.gui.JFrame_GetPointImage;
import fr.B4D.modules.B4DWait;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;

public final class Mouse {
	  
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
	public static void Place(Point position, double attente) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
		B4DWait.wait(attente);
	}
	public static void Place(Point position) throws AWTException {
		Place(position, 0);
	}
	//PointF
	public static void Place(PointF position, double attente) throws AWTException {
		Place(B4D.converter.pointFToPoint(position), attente);
	}
	public static void Place(PointF position) throws AWTException {
		Place(B4D.converter.pointFToPoint(position), 0);
	}
	//PointD
	public static void Place(PointD position, double attente) throws AWTException {
		Place(B4D.converter.pointDToPoint(position), attente);
	}
	public static void Place(PointD position) throws AWTException {
		Place(B4D.converter.pointDToPoint(position), 0);
	}
	
	  /******************/
	 /** CLIQUE DROIT **/
	/******************/
	
	//Point
	public static void rightClick(Point position, boolean maj, double timeOut) throws AWTException{
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
	public static void rightClick(Point position, boolean maj) throws AWTException{
		rightClick(position, maj, 1);
	}
	public static void rightClick(PointF position, boolean maj, double timeOut) throws AWTException{
		rightClick(B4D.converter.pointFToPoint(position), maj, timeOut);
	}
	public static void rightClick(PointF position, boolean maj) throws AWTException{
		rightClick(B4D.converter.pointFToPoint(position), maj, 1);
	}
	public static void rightClick(PointD position, boolean maj, double timeOut) throws AWTException{
		rightClick(B4D.converter.pointDToPoint(position), maj, timeOut);
	}
	public static void rightClick(PointD position, boolean maj) throws AWTException{
		rightClick(B4D.converter.pointDToPoint(position), maj, 1);
	}
	
	  /*******************/
	 /** CLIQUE GAUCHE **/
	/*******************/
	
	public static void leftClick(Point position, boolean maj, double timeOut) throws AWTException {
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
	public static void leftClick(Point position, boolean maj) throws AWTException{
		leftClick(position, maj, 1);
	}	
	public static void leftClick(PointF position, boolean maj, double timeOut) throws AWTException{
		leftClick(B4D.converter.pointFToPoint(position), maj, timeOut);
	}
	public static void leftClick(PointF position, boolean maj) throws AWTException{
		leftClick(B4D.converter.pointFToPoint(position), maj, 1);
	}
	public static void leftClick(PointD position, boolean maj, double timeOut) throws AWTException{
		leftClick(B4D.converter.pointDToPoint(position), maj, timeOut);
	}
	public static void leftClick(PointD position, boolean maj) throws AWTException{
		leftClick(B4D.converter.pointDToPoint(position), maj, 1);
	}
	
	  /**************************/
	 /** DOUBLE CLIQUE GAUCHE **/
	/**************************/
	
	public static void doubleLeftClick(Point position, boolean maj, double timeOut) throws AWTException {
		leftClick(position, maj, 0);
		leftClick(position, maj, timeOut);
	}	
	public static void doubleLeftClick(Point position, boolean maj) throws AWTException{
		doubleLeftClick(position, maj, 1);
	}
	public static void doubleLeftClick(PointF position, boolean maj, double timeOut) throws AWTException{
		doubleLeftClick(B4D.converter.pointFToPoint(position), maj, timeOut);
	}
	public static void doubleLeftClick(PointF position, boolean maj) throws AWTException{
		doubleLeftClick(B4D.converter.pointFToPoint(position), maj, 1);
	}
	public static void doubleLeftClick(PointD position, boolean maj, double timeOut) throws AWTException{
		doubleLeftClick(B4D.converter.pointDToPoint(position), maj, timeOut);
	}
	public static void doubleLeftClick(PointD position, boolean maj) throws AWTException{
		doubleLeftClick(B4D.converter.pointDToPoint(position), maj, 1);
	}
	
	  /**************************/
	 /** TRIPLE CLIQUE GAUCHE **/
	/**************************/
	
	public static void tripleLeftClick(Point position, boolean maj, double timeOut) throws AWTException {
		leftClick(position, maj, 0);
		leftClick(position, maj, 0);
		leftClick(position, maj, timeOut);
	}	
	public static void tripleLeftClick(Point position, boolean maj) throws AWTException{
		tripleLeftClick(position, maj, 1);
	}
	public static void tripleLeftClick(PointF position, boolean maj, double timeOut) throws AWTException{
		tripleLeftClick(B4D.converter.pointFToPoint(position), maj, timeOut);
	}
	public static void tripleLeftClick(PointF position, boolean maj) throws AWTException{
		tripleLeftClick(B4D.converter.pointFToPoint(position), maj, 1);
	}
	public static void tripleLeftClick(PointD position, boolean maj, double timeOut) throws AWTException{
		tripleLeftClick(B4D.converter.pointDToPoint(position), maj, timeOut);
	}
	public static void tripleLeftClick(PointD position, boolean maj) throws AWTException{
		tripleLeftClick(B4D.converter.pointDToPoint(position), maj, 1);
	}
}
