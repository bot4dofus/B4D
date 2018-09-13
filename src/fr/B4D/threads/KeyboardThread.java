package fr.B4D.threads;

import com.sun.jna.Library;
import com.sun.jna.Native;

interface User32 extends Library {
    public static User32 INSTANCE = (User32) Native.loadLibrary("User32", User32.class);
    short GetAsyncKeyState(int key);
    short GetKeyState(int key);
}

public class ThreadTouche extends Thread{

	public void run(){		
		try {
			boolean fin = false;
			char i = 0;			
			while(!fin) {
				for(i=1;i<=256;i++) {
					if(isKeyPressed(i)) {
						fin = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
	}
	
	private static boolean isKeyPressed(int key)
	{
		return User32.INSTANCE.GetAsyncKeyState(key) == -32767;
	}
	private static boolean isShiftPressed()	{
		return (User32.INSTANCE.GetKeyState(0x10) & 0x80) == 0x80;
	}
}
