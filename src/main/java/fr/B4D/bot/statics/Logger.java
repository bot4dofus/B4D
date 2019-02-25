package fr.B4D.bot.statics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import fr.B4D.bot.B4DException;

/** La classe {@code Logger} permet d'accéder à toutes les méthodes liés aux logs et aux erreurs.
 */
public class Logger {

	/**************/
	/** ATRIBUTS **/
	/**************/

	private final String path = "errors.txt";
	private final String username = "b4d.developer@outlook.com";
	private final String password = "*****************";

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

	/** Permet d'afficher un message d'erreur dans une fenêtre graphique en demandant si l'utilisateur veut envoyer le rapport d'erreur.
	 * @param e - Trace de l'erreur.
	 */
	public void error(Exception e) {
		e.printStackTrace();

		boolean canSendRepport = true;
		if(e instanceof B4DException)
			canSendRepport = ((B4DException)e).canSendRepport();

		if(!canSendRepport)
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		else {
			boolean sendRepport = addRepport(e);
			if(sendRepport) {
				String message;
				if(e.getMessage() == null)
					message = "Une erreur s'est produite.\nVoulez vous envoyer le rappot d'erreur aux développeurs ?";
				else
					message = e.getMessage() + "\nVoulez vous envoyer le rappot d'erreur aux développeurs ?";
				int answer = JOptionPane.showConfirmDialog(null, message, "Erreur", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

				//			if (answer == JOptionPane.YES_OPTION)
				//				sendEmail("Repport B4D", null, path);
			}else
				JOptionPane.showConfirmDialog(null, e.getMessage() + "\n\nLorsque qu'un envoi du rapport d'erreur vous sera proposé, dites \"Oui\" pour signaler cette erreur aux développeurs.", "Erreur", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
		}
	}

	/** Permet d'ajouter la trace d'éxecution de l'erreur dans un fichier texte.
	 * @param e - Exception de l'erreur.
	 * @return {@code true} si le fichier texte dépasse 100 lignes, {@code false} sinon.
	 */
	public boolean addRepport(Exception e) {		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
			PrintWriter pw = new PrintWriter(writer);
			e.printStackTrace(pw);
			pw.print("\n\n");
			writer.close();
			return numberOfLines(path) > 100;
		} catch (IOException e1) {
			return false;
		}
	}

	/** Permet de compter le nombre ligne d'un fichier.
	 * @param path - Chemin vers le fichier.
	 * @return - Nombre de ligne du fichier. {@code -1} si e fichier n'existe pas.
	 */
	private int numberOfLines(String path) {
		try{
			int linenumber = 0;
			File file = new File(path);
			if(file.exists()){
				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);
				while (lnr.readLine() != null)
					linenumber++;
				lnr.close();
			}
			return linenumber;
		}catch(IOException e){
			return -1;
		}
	}

	/*******************/
	/** EMAIL METHODS **/
	/*******************/

	/** Permet d'envoyer un commentaire à l'auteur via email.
	 * Cela est identique à {@code sendEmail("Feedback B4D", message, null)}.
	 * @param message - Commentaire à destination de l'auteur.
	 */
	public void sendFeedback(String message) {
		sendEmail("Feedback B4D", message, null);
	}

	/** Permet d'envoyer un email à l'auteur.
	 * @param subject - Objet de l'email.
	 * @param message - Contenu de l'email. Aucun contenu n'est ajouté à l'email si {@code null}.
	 * @param path - Chemin vers le fichier à attacher à l'email.  Aucun fichier n'est attaché à l'email si {@code null}.
	 */
	public void sendEmail(String subject, String message, String path) {

		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", "smtp-mail.outlook.com");
		properties.put("mail.smtp.port", "587");

		properties.put("mail.smtp.user", username);
		properties.put("mail.smtp.password", password);

		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			MimeMessage mail = new MimeMessage(session);
			mail.setFrom(new InternetAddress(username));
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
			mail.setSubject(subject);

			Multipart multipart = new MimeMultipart();

			if(message != null) {
				BodyPart bodyPart = new MimeBodyPart();
				bodyPart.setText(message);
				multipart.addBodyPart(bodyPart);
			}

			if(path != null) {
				BodyPart bodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(path);
				bodyPart.setDataHandler(new DataHandler(source));
				bodyPart.setFileName(path);
				multipart.addBodyPart(bodyPart);
			}
			mail.setContent(multipart);
			Transport.send(mail);
			debug(this, "Repport sent");

			File file = new File(path);
			if(file.exists())
				file.delete();

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
