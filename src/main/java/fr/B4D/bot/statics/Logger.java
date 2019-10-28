package fr.B4D.bot.statics;

import java.awt.Desktop;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/** La classe {@code Logger} permet d'accéder à toutes les méthodes liés aux logs et aux erreurs.
 */
public class Logger {

	/*************/
	/** METHODS **/
	/*************/

	/** Permet d'afficher un message dans une fenêtre graphique. Celui-ci est aussi affiché dans la console.
	 * @param log - Message à afficher.
	 */
	public void popUp(String log) {
		debug(this, "Popup showed : " + log);
		JOptionPane.showMessageDialog(null, log, "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	/** Permet d'afficher un message dans la console.
	 * Cette méthode doit être utilisé ainsi {@code B4D.logger.debug(this, "...")}.
	 * @param c - Classe appelante.
	 * @param log - Message à afficher.
	 */
	public void debug(Object c, String log) {
		System.out.println("[" + c.getClass().getName() + "] " + log);
	}

	/** Permet d'afficher un message d'alerte dans la console.
	 * @param c - Classe appelante.
	 * @param log - Message à afficher.
	 */
	public void warning(Object c, String log) {
		System.err.println("[" + c.getClass().getName() + "] " + log);
	}

	/** Permet d'afficher un message d'erreur dans une fenêtre graphique en demandant si l'utilisateur veut envoyer le signaler aux développeurs.
	 * @param e - Trace de l'erreur.
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
				URI url = new URI("https://github.com/LucBerge/B4D/issues");
				desktop.browse(url);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
