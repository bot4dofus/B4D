package fr.B4D.threads;

public class StopThread extends Thread{

	private Thread thread;
	
	public StopThread(Thread thread) {
		this.thread = thread;
	}
	
	public void run(){
		boolean fin = false;
		char i = 0;

		try {
			while(!fin) {
				for(i=1;i<=256;i++) {
					if(isShiftPressed()) {
						if(isSPressed()){
							thread.interrupt();
						}else if(isPPressed()){
							thread.wait();
						}else if(isLPressed()){
							thread.notify();						
						}
					}
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
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
