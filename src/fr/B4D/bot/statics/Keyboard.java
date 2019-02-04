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

import fr.B4D.bot.B4D;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.threads.KeyboardThread;

public final class Keyboard{
	
	private static Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	  /****************/
	 /** SINGLE KEY **/
	/****************/
	
	public void sendKey(int keyEvent, int time) throws AWTException, StopProgramException, CancelProgramException {
		Robot robot = new Robot();
		robot.keyPress(keyEvent);
		robot.keyRelease(keyEvent);
		B4D.wait.wait(time);
	}
	public void sendKey(int keyEvent) throws AWTException, StopProgramException, CancelProgramException {
		sendKey(keyEvent, 100);
	}
	
	  /********************/
	 /** WRITE KEYBOARD **/
	/********************/
	
	public void writeKeyboard(String text, int time) throws AWTException, StopProgramException, CancelProgramException {
		setClipboard(text);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		B4D.wait.wait(time);
	}
	public void writeKeyboard(String text) throws AWTException, StopProgramException, CancelProgramException {
		writeKeyboard(text, 500);
	}
	
	  /***************/
	 /** CLIPBOARD **/
	/***************/
	
	public void setClipboard(String text) throws AWTException {
		clipboard.setContents(new StringSelection(text), null);
	}
	public String getClipboard() throws AWTException, UnsupportedFlavorException, IOException{
        return (String) clipboard.getData(DataFlavor.stringFlavor);
	}
	
	  /************************/
	 /** ATTENTE SUR TOUCHE **/
	/************************/
	
	public boolean waitForKeyboard(int timeOut) {
		Thread keyboardThread = new KeyboardThread();
		keyboardThread.start();
		try {
			keyboardThread.join(timeOut);
		} catch (InterruptedException e) {
			B4D.logger.error(e);
		}
		
		if(keyboardThread.isAlive()) {
			keyboardThread.interrupt();
			return false;
		}else 
			return true;
	}
}
