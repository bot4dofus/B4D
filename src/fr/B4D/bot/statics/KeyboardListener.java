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

public class KeyboardListener extends Thread{
	
	  /*********/
	 /** RUN **/
	/*********/
	
	public void run(){
		try {
			B4D.logger.debug(this, "Lancement du thread");
			boolean fin = false;

			while(!fin) {
				if(isShiftPressed() && isSPressed())
					B4D.wait.suspend();
				Thread.sleep(500);
			}
			B4D.logger.debug(this, "Fin du thread");
		} catch (InterruptedException e) {
			B4D.logger.error(e);
		}
	}
	
	  /*************/
	 /** METHODS **/
	/*************/
	
	private boolean isSPressed() {
		return User32.INSTANCE.GetAsyncKeyState(KeyEvent.VK_S) != 0;
	}
	private boolean isShiftPressed() {
		return (User32.INSTANCE.GetKeyState(KeyEvent.VK_SHIFT) & 0x80) == 0x80;
	}
}
