package fr.B4D.log;

public class Logger {
	public static void popUp(String log) {
		//popUp window with OK button
		System.out.println(log);
	}
	
	public static void debud(String log) {
		System.out.println(log);
	}

	public static void warning(String log) {
		System.err.println(log);
	}

	public static void error(String string, Exception ex) {
		ex.printStackTrace();
		//Send report
	}
}
