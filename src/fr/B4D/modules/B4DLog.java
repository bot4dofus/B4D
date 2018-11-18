package fr.B4D.modules;

import java.awt.AWTException;

public final class B4DLog {

	private boolean logStatus = true;

	  /*************/
	 /** GET/SET **/
	/*************/
	
	public boolean getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(boolean logStatus) {
		this.logStatus = logStatus;
	}

	  /*********/
	 /** LOG **/
	/*********/
	
	public static void log(String text) throws AWTException   {
		System.out.println(text);
	}
}
