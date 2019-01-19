package fr.B4D.bot;

import javax.swing.JOptionPane;

import com.sun.jna.Library;
import com.sun.jna.Native;

import fr.B4D.program.Program;

interface User32 extends Library {
    public static User32 INSTANCE = (User32) Native.loadLibrary("User32", User32.class);
    short GetAsyncKeyState(int key);
    short GetKeyState(int key);
}

public class KeyboardListener extends Thread{

	private Thread program;
	
	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/
	public void setProgram(Program program) {
		this.program = program;
	}
	
	  /*********/
	 /** RUN **/
	/*********/
	
	public void run(){
		boolean fin = false;
		char i = 0;

		while(!fin) {
			for(i=1;i<=256;i++) {
				if(isShiftPressed()) {
					if(isSPressed()){
						int response = JOptionPane.showConfirmDialog(null, "Êtes vous sûr de vouloir stoper le bot ?", "Stop", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.YES_OPTION) {
							program.interrupt();
							System.out.println("Yes button clicked");
						}
					}else if(isPPressed()){
						System.out.println("P pressed");
						program.suspend();
						B4D.logger.popUp("Le bot a été mis sur pause. Appuyez sur 'Shift + L' pour le remettre sur lecture.");
					}else if(isLPressed()){
						System.out.println("L pressed");
						int response = JOptionPane.showConfirmDialog(null, "Êtes vous sûr de vouloir remettre le bot sur lecture ?", "Lecture", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);						
						if (response == JOptionPane.YES_OPTION) {
							program.resume();
							System.out.println("Yes button clicked");
						}
					}
				}
			}
		}
	}
	
	  /*************/
	 /** METHODS **/
	/*************/
	
	private static boolean isSPressed()
	{
		return User32.INSTANCE.GetAsyncKeyState('s') == -32767;
	}
	private static boolean isPPressed()
	{
		return User32.INSTANCE.GetAsyncKeyState('p') == -32767;
	}
	private static boolean isLPressed()
	{
		return User32.INSTANCE.GetAsyncKeyState('l') == -32767;
	}
	private static boolean isShiftPressed()	{
		return (User32.INSTANCE.GetKeyState(0x10) & 0x80) == 0x80;
	}
}
