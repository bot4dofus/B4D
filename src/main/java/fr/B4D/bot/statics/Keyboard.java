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

/**
 * The {@code Keyboard} class is used to access to the keyboard methods.
 * 
 * @author Lucas
 *
 */
public final class Keyboard{

	/**
	 * Robot performing a key press.
	 */
	private Robot robot;

	/**
	 * Constructor of the {@code Keyboard} class.
	 * @throws AWTException if the platform configuration does not allow low-level input control.
     */
    public Keyboard() throws AWTException {
		this.robot = new Robot();
    }
	
	/**
	 * Simulates a key press on the keyboard.
	 * @param keyEvent - Integer representing the key to press.
	 * @param time - Time to wait after the key press.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public void sendKey(int keyEvent, int time) throws StopProgramException, CancelProgramException {
		robot.keyPress(keyEvent);
		robot.keyRelease(keyEvent);
		B4D.wait.sleep(time);
	}
	
	/**
	 * Simulates a key press on the keyboard and wait for 100 ms after the key press.
	 * This is the same as {@code sendKey(keyEvent, 100)}.
	 * @param keyEvent - Integer representing the key to press.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public void sendKey(int keyEvent) throws StopProgramException, CancelProgramException {
		sendKey(keyEvent, 100);
	}
	
	/**
	 * Simulates key press on the keyboard.<br><br>
	 * This method actually copy the string in the clipboard and paste it where the cursor is.
	 * It is faster than pressing all the letters one by one.
	 * @param text - Text to write.
	 * @param time - Time to wait after pasting.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public void writeKeyboard(String text, int time) throws StopProgramException, CancelProgramException {
		setClipboard(text);		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		B4D.wait.sleep(time);
	}
	
	/**
	 * Simulates key press on the keyboard and wait for 500 ms after pasting.<br><br>
	 * This is the same as {@code sendKey(keyEvent, 500)}.
	 * This method actually copy the string in the clipboard and paste it where the cursor is.
	 * It is faster than pressing all the letters one by one.
	 * @param text - Text to write.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public void writeKeyboard(String text) throws StopProgramException, CancelProgramException {
		writeKeyboard(text, 500);
	}
	
	/**
	 * Defines the text to copy in the clipboard.
	 * @param text - Text to copy.
	 */
	public void setClipboard(String text) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(new StringSelection(text), null);
	}
	
	/**
	 * Returns the current string in the clipboard.
	 * @return String contained in the clipboard, {@code null} if the data in clipboard is not a string or if or if the clipboard is empty
	 */
	public String getClipboard(){
        try {
    		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			return (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (IOException | UnsupportedFlavorException e) {
			return null;
		}
	}
	
	/** Permet d'attendre l'appui sur une touche.
	 * Wait for a key to be pressed.
	 * @param timeout - Timeout in ms.
	 * @return Integer representing the key pressed, {@code -1} if timeout.
	 */
	public int waitForKeyboard(int timeout) {
		//To be implemented using KeyboardListener and keys queue just like Chat and messages works
		return -1;
	}
}
