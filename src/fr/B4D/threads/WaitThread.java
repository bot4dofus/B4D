package fr.B4D.threads;

public class WaitThread extends Thread{
	private double timeOut;
		
	public WaitThread(double timeOut) {
		this.timeOut = timeOut;
	}
		
	public void run(){
		try{
			Thread.sleep((long) (timeOut*1000));
		}catch (Exception e){
			Thread.currentThread().interrupt();
		}
	}
}
