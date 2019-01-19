package fr.B4D.log;

import javax.swing.JOptionPane;

public class Logger {
	public static void popUp(String log) {
		JOptionPane.showMessageDialog(null, log, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void debug(String log) {
		System.out.println(log);
	}

	public static void warning(String log) {
		System.err.println(log);
	}

	public static void error(String erreur, Exception e) {
		e.printStackTrace();
		int answer = JOptionPane.showConfirmDialog(null, erreur + "\n\n" + e.getMessage() + "\n\nVoulez vous envoyer le rappot d'erreur au développeur ?", "Erreur", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if (answer == JOptionPane.YES_OPTION) {
			//Send repport
		}
	}
}
