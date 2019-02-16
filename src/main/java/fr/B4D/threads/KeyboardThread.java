package fr.B4D.threads;

import com.sun.jna.Library;
import com.sun.jna.Native;

interface User32 extends Library {
    public static User32 INSTANCE = (User32) Native.loadLibrary("User32", User32.class);
    short GetAsyncKeyState(int key);
    short GetKeyState(int key);
}

public class KeyboardThread extends Thread{

	public void run(){	
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
	}
	
	private static boolean isKeyPressed(int key)
	{
		return User32.INSTANCE.GetAsyncKeyState(key) == -32767;
	}
}
