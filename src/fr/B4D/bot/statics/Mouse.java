package fr.B4D.bot.statics;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Configuration;
import fr.B4D.gui.JFrame_GetPoint;
import fr.B4D.gui.JFrame_GetPointImage;
import fr.B4D.modules.B4DWait;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;

public final class Mouse {

	private Configuration configuration;
    
	/*************/
	/** BUILDER **/
	/*************/
    
    public Mouse(Configuration configuration) {
    	this.configuration = configuration;
    }
    
	/***************/
	/** GET POINT **/
	/***************/

	public void getPoint(String text, MouseAdapter mouseAdapter) {
		JFrame_GetPoint window = new JFrame_GetPoint(text, mouseAdapter);
		window.frame.setVisible(true);
	}
	public void getPoint(String text, ImageIcon image, MouseAdapter mouseAdapter) {
		JFrame_GetPointImage window = new JFrame_GetPointImage(text, image, mouseAdapter);
		window.frame.setVisible(true);
	}
	
	public void getPoints(String text1, MouseAdapter mouseAdapter1, String text2, MouseAdapter mouseAdapter2) {
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
	public void getPoints(String text1, ImageIcon image1, MouseAdapter mouseAdapter1, String text2, ImageIcon image2, MouseAdapter mouseAdapter2) {
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
	public void place(Point position, int millis) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
		B4DWait.wait(millis);
	}
	public void place(Point position) throws AWTException {
		place(position, 0);
	}
	//PointF
	public void place(PointF position, int millis) throws AWTException {
		place(B4D.converter.pointFToPoint(position), millis);
	}
	public void place(PointF position) throws AWTException {
		place(B4D.converter.pointFToPoint(position), 0);
	}
	//PointD
	public void Place(PointD position, int millis) throws AWTException {
		place(B4D.converter.pointDToPoint(position), millis);
	}
	public void Place(PointD position) throws AWTException {
		place(B4D.converter.pointDToPoint(position), 0);
	}
	
	  /******************/
	 /** CLIQUE DROIT **/
	/******************/
	
	//Point
	public void rightClick(Point position, boolean maj, int millis) throws AWTException{
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
			
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4DWait.wait(millis);		
	}
	public void rightClick(Point position, boolean maj) throws AWTException{
		rightClick(position, maj, 1000);
	}
	public void rightClick(PointF position, boolean maj, int millis) throws AWTException{
		rightClick(B4D.converter.pointFToPoint(position), maj, millis);
	}
	public void rightClick(PointF position, boolean maj) throws AWTException{
		rightClick(B4D.converter.pointFToPoint(position), maj, 1000);
	}
	public void rightClick(PointD position, boolean maj, int millis) throws AWTException{
		rightClick(B4D.converter.pointDToPoint(position), maj, millis);
	}
	public void rightClick(PointD position, boolean maj) throws AWTException{
		rightClick(B4D.converter.pointDToPoint(position), maj, 1000);
	}
	
	  /*******************/
	 /** CLIQUE GAUCHE **/
	/*******************/
	
	public void leftClick(Point position, boolean maj, int millis) throws AWTException {
		Robot robot = new Robot();
		robot.mouseMove((int)position.getX(),(int)position.getY());
			
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4DWait.wait(millis);
	}	
	public void leftClick(Point position, boolean maj) throws AWTException{
		leftClick(position, maj, 1000);
	}	
	public void leftClick(PointF position, boolean maj, int millis) throws AWTException{
		leftClick(B4D.converter.pointFToPoint(position), maj, millis);
	}
	public void leftClick(PointF position, boolean maj) throws AWTException{
		leftClick(B4D.converter.pointFToPoint(position), maj, 1000);
	}
	public void leftClick(PointD position, boolean maj, int millis) throws AWTException{
		leftClick(B4D.converter.pointDToPoint(position), maj, millis);
	}
	public void leftClick(PointD position, boolean maj) throws AWTException{
		leftClick(B4D.converter.pointDToPoint(position), maj, 1000);
	}
	
	  /**************************/
	 /** DOUBLE CLIQUE GAUCHE **/
	/**************************/
	
	public void doubleLeftClick(Point position, boolean maj, int millis) throws AWTException {
		leftClick(position, maj, 0);
		leftClick(position, maj, millis);
	}	
	public void doubleLeftClick(Point position, boolean maj) throws AWTException{
		doubleLeftClick(position, maj, 1000);
	}
	public void doubleLeftClick(PointF position, boolean maj, int millis) throws AWTException{
		doubleLeftClick(B4D.converter.pointFToPoint(position), maj, millis);
	}
	public void doubleLeftClick(PointF position, boolean maj) throws AWTException{
		doubleLeftClick(B4D.converter.pointFToPoint(position), maj, 1000);
	}
	public void doubleLeftClick(PointD position, boolean maj, int millis) throws AWTException{
		doubleLeftClick(B4D.converter.pointDToPoint(position), maj, millis);
	}
	public void doubleLeftClick(PointD position, boolean maj) throws AWTException{
		doubleLeftClick(B4D.converter.pointDToPoint(position), maj, 1000);
	}
	
	  /**************************/
	 /** TRIPLE CLIQUE GAUCHE **/
	/**************************/
	
	public void tripleLeftClick(Point position, boolean maj, int millis) throws AWTException {
		leftClick(position, maj, 0);
		leftClick(position, maj, 0);
		leftClick(position, maj, millis);
	}	
	public void tripleLeftClick(Point position, boolean maj) throws AWTException{
		tripleLeftClick(position, maj, 1000);
	}
	public void tripleLeftClick(PointF position, boolean maj, int millis) throws AWTException{
		tripleLeftClick(B4D.converter.pointFToPoint(position), maj, millis);
	}
	public void tripleLeftClick(PointF position, boolean maj) throws AWTException{
		tripleLeftClick(B4D.converter.pointFToPoint(position), maj, 1000);
	}
	public void tripleLeftClick(PointD position, boolean maj, int millis) throws AWTException{
		tripleLeftClick(B4D.converter.pointDToPoint(position), maj, millis);
	}
	public void tripleLeftClick(PointD position, boolean maj) throws AWTException{
		tripleLeftClick(B4D.converter.pointDToPoint(position), maj, 1000);
	}

	  /**********/
	 /** CHAT **/
	/**********/
	
	public void chatClick() throws AWTException{
		leftClick(configuration.getChatBar(), false, 500);
	}
	
}
