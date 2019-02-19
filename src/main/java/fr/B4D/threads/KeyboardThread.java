package fr.B4D.threads;

import com.sun.jna.Library;
import com.sun.jna.Native;

interface User32 extends Library {
	public static User32 INSTANCE = (User32) Native.loadLibrary("User32", User32.class);
	short GetAsyncKeyState(int key);
	short GetKeyState(int key);
}

/** La classe {@code KeyboardThread} permet d'attendre l'appui sur une touche.<br><br>
 * Cette classe étend la classe {@code Thread}.
 */
public class KeyboardThread extends Thread{

	private int key = -1;

	/** Retourne la touche enfoncé par le joueur.
	 * @return Couleur du pixel. {@code -1} si aucune touche n'a été enfoncée.
	 */
	public int getKey() {
		return this.key;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){	
		try {
			char i = 0;	
			while(key == -1) {
				for(i=1;i<=256;i++) {
					if(isKeyPressed(i)) {
						key = i;
						break;
					}
				}
				Thread.sleep(100);
			} 
		}catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private static boolean isKeyPressed(int key)
	{
		return User32.INSTANCE.GetAsyncKeyState(key) == -32767;
	}
}
