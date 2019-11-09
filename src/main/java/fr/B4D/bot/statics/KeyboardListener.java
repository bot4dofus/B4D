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

/** La classe {@code KeyboardListener} permet d'écouter les touches enfoncées par l'utilisateur.<br><br>
 * Cette classe étend la classe {@code Thread}.
 */
public class KeyboardListener extends Thread{
	
	  /*********/
	 /** RUN **/
	/*********/
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
		B4D.logger.debug("Lancement du thread");
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
		B4D.logger.debug("Fin du thread");
	}
	
	  /*************/
	 /** METHODS **/
	/*************/
	
	/** Permet de savoir si la touche S est enfoncée.
	 * @return {@code true} si la touche est enfoncée, {@code false} sinon.
	 */
	private boolean isSPressed() {
		return User32.INSTANCE.GetAsyncKeyState(KeyEvent.VK_S) != 0;
	}
	
	/** Permet de savoir si la touche Shift (Maj) est enfoncée.
	 * @return {@code true} si la touche est enfoncée, {@code false} sinon.
	 */
	private boolean isShiftPressed() {
		return (User32.INSTANCE.GetKeyState(KeyEvent.VK_SHIFT) & 0x80) == 0x80;
	}
}
