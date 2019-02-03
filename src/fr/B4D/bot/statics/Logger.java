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

public class Logger {

	/**************/
	/** ATRIBUTS **/
	/**************/

	private final String path = "errors.txt";
	private final String username = "b4d.developer@outlook.fr";
	private final String password = "*****************";

	/*************/
	/** METHODS **/
	/*************/

	public void popUp(String log) {
		debug(this, "Popup showed : " + log);
		JOptionPane.showMessageDialog(null, log, "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	public void debug(Object c, String log) {
		System.out.println("[" + c.getClass().getName() + "] " + log);
	}

	public void warning(String log) {
		System.err.println(log);
	}

	public void error(Exception e) {
		e.printStackTrace();
		if(addRepport(e)) {
			String message = e.getMessage() + "\n\n=====\n\nVoulez vous envoyer le rappot d'erreur au développeur ?";
			int answer = JOptionPane.showConfirmDialog(null, message, "Erreur", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

//			if (answer == JOptionPane.YES_OPTION)
//					sendEmail("Repport B4D", null, path);
		}
	}

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
	
	private int numberOfLines(String path) {
		int linenumber = 0;
		try{
			File file = new File(path);
			if(file.exists()){
				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);
				while (lnr.readLine() != null)
					linenumber++;
				lnr.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return linenumber;
	}

	/*******************/
	/** EMAIL METHODS **/
	/*******************/

	public void sendFeedback(String message) {
		sendEmail("Feedback B4D", message, null);
	}

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
