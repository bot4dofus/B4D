package fr.B4D.bot;

import javax.swing.JOptionPane;

public class Logger {
	public void popUp(String log) {
		JOptionPane.showMessageDialog(null, log, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void debug(String log) {
		System.out.println(log);
	}

	public void warning(String log) {
		System.err.println(log);
	}

	public void error(String message, Exception e) {
		e.printStackTrace();
		int answer = JOptionPane.showConfirmDialog(null, message + "\n\n" + e.getMessage() + "\n\nVoulez vous envoyer le rappot d'erreur au développeur ?", "Erreur", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if (answer == JOptionPane.YES_OPTION) {
			sendRepport(message, e);
		}
	}
	
	public void sendRepport(String message, Exception e) {
		//Send repport
	}
}
