package fr.B4D.log;

import javax.swing.JOptionPane;

public class Logger {
	public static void popUp(String log) {
		JOptionPane.showConfirmDialog(null, log, "Information", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
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
