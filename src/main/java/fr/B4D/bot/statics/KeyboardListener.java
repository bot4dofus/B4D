package fr.B4D.bot.statics;

import java.awt.event.KeyEvent;

import com.sun.jna.Library;
import com.sun.jna.Native;

import fr.B4D.bot.B4D;

interface User32 extends Library {
    public static User32 INSTANCE = (User32) Native.loadLibrary("User32", User32.class);
    short GetAsyncKeyState(int key);
    short GetKeyState(int key);
}

/**
 * The {@code KeyboardListener} is used to listen the keyboard entries.
 * 
 * @author Lucas
 *
 */
public class KeyboardListener extends Thread{
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		B4D.logger.debug("Starting the thread");
		boolean fin = false;

		while(!fin) {
			if(isShiftPressed() && isSPressed())
				B4D.wait.suspend();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				fin = true;
			}
		}
		B4D.logger.debug("Ending the thread");
	}
	
	/**
	 * Checks whether the S key is pressed.
	 * @return {@code true} if the key is pressed, {@code false} otherwise.
	 */
	private boolean isSPressed() {
		return User32.INSTANCE.GetAsyncKeyState(KeyEvent.VK_S) != 0;
	}

	/**
	 * Checks whether the Shift key is pressed.
	 * @return {@code true} if the key is pressed, {@code false} otherwise.
	 */
	private boolean isShiftPressed() {
		return (User32.INSTANCE.GetKeyState(KeyEvent.VK_SHIFT) & 0x80) == 0x80;
	}
}
