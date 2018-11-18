package fr.B4D.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public final class B4DKeyboard{
	
	static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
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
