package fr.B4D.threads;

import javax.swing.JOptionPane;

public class StopThread extends Thread{

	private Thread thread;
	
	public StopThread(Thread thread) {
		this.thread = thread;
	}
	
	public void run(){
		boolean fin = false;
		char i = 0;

		while(!fin) {
			for(i=1;i<=256;i++) {
				if(isShiftPressed()) {
					if(isSPressed()){
						System.out.println("S pressed");
						int response = JOptionPane.showConfirmDialog(null, "Êtes vous sûr de vouloir stoper le bot ?", "Stop", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (response == JOptionPane.YES_OPTION) {
							thread.interrupt();
							System.out.println("Yes button clicked");
						}
					}else if(isPPressed()){
						System.out.println("P pressed");
						thread.suspend();
						JOptionPane.showConfirmDialog(null, "Le bot a été mis sur pause. Appuyez sur 'Shift + L' pour le remettre sur lecture.", "Pause", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}else if(isLPressed()){
						System.out.println("L pressed");
						int response = JOptionPane.showConfirmDialog(null, "Êtes vous sûr de vouloir remettre le bot sur lecture ?", "Lecture", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);						
						if (response == JOptionPane.YES_OPTION) {
							thread.resume();
							System.out.println("Yes button clicked");
						}
					}
				}
			}
		}
	}
	
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
