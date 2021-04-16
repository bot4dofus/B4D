package fr.B4D.bot.statics;

import java.awt.Desktop;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * The {@code Logger} class is used to access to the logger methods.
 * 
 * @author Lucas
 *
 */
public class Logger {

	/**
	 * Displays a popup message on screen.
	 * It is also displayed in the console.
	 * @param log - Message to display.
	 */
	public void popUp(String log) {
		popUp(new Exception().getStackTrace()[1].getClassName(), log);
	}

	/**
	 * Displays a popup message on screen.
	 * It is also displayed in the console.
	 * @param c - Calling class.
	 * @param log - Message to display.
	 */
	public void popUp(String c, String log) {
		debug(c, log);
		JOptionPane.showMessageDialog(null, log, "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Displays a message in the console.
	 * @param log - Message to display.
	 */
	public void debug(String log) {
		debug(new Exception().getStackTrace()[1].getClassName(), log);
	}

	/**
	 * Displays a message in the console.
	 * @param c - Calling class.
	 * @param log - Message to display.
	 */
	public void debug(String c, String log) {
		System.out.println("[" + c + "] " + log);
	}

	/**
	 * Displays a warning in the console.
	 * @param log - Warning to display.
	 */
	public void warning(String log) {
		warning(new Exception().getStackTrace()[1].getClassName(), log);
	}

	/**
	 * Displays a warning in the console.
	 * @param c - Calling class.
	 * @param log - Warning to display.
	 */
	public void warning(String c, String log) {
		System.err.println("[" + c + "] " + log);
	}

	/**
	 * Displays an exception on screen.
	 * It is also displayed in the console.
	 * @param e - Exception to display.
	 */
	public void error(Exception e) {
		e.printStackTrace();
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String stackTrace = sw.toString();
		
		JTextArea textarea= new JTextArea("Une erreur s'est produite. Voulez vous le signaler aux développeurs ?\n----------\n" + stackTrace);
		 textarea.setEditable(true);
		int answer = JOptionPane.showConfirmDialog(null, textarea, "Erreur", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if (answer == JOptionPane.YES_OPTION) {
			try {
				Desktop desktop = java.awt.Desktop.getDesktop();
				URI url = new URI("https://github.com/LucBerge/B4D/issues/new?assignees=LucBerge&labels=bug&template=bug_report.md&title=");
				desktop.browse(url);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
