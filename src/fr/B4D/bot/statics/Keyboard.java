package fr.B4D.bot.statics;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import fr.B4D.modules.B4DWait;

public final class Keyboard{
	
	private static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	  /****************/
	 /** SINGLE KEY **/
	/****************/
	
	public static void sendKey(int keyEvent, Double time) throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(keyEvent);
		robot.keyRelease(keyEvent);
		B4DWait.wait(time);
	}
	public static void sendKey(int keyEvent) throws AWTException {
		sendKey(keyEvent, 0.1);
	}
	
	  /********************/
	 /** WRITE KEYBOARD **/
	/********************/
	
	public static void writeKeyboard(String text, double time) throws AWTException {
		setClipboard(text);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		B4DWait.wait(time);
	}
	public static void writeKeyboard(String text) throws AWTException {
		writeKeyboard(text, 0.5);
	}
	
	  /***************/
	 /** CLIPBOARD **/
	/***************/
	
	public static void setClipboard(String text) throws AWTException {
		clipboard.setContents(new StringSelection(text), null);
	}
	public static String getClipboard() throws AWTException, UnsupportedFlavorException, IOException{
        return (String) clipboard.getData(DataFlavor.stringFlavor);
	}
}
