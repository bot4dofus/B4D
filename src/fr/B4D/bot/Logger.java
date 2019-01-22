package fr.B4D.bot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class Logger {

	  /**************/
	 /** ATRIBUTS **/
	/**************/

	private final String path = "errors.txt";
	private final String host = "localhost";
	private final String to = "b4d@develloper.com";
	private final String fromFeedback = "feedback@b4d.com";
	private final String fromRepport = "repport@b4d.com";
	
	  /*************/
	 /** METHODS **/
	/*************/
	
	public void popUp(String log) {
		debug(this, "Popshowed : " + log);
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
		String message = e.getMessage() + "\n\n=====\n\nVoulez vous envoyer le rappot d'erreur au développeur ?";
		int answer = JOptionPane.showConfirmDialog(null, message, "Erreur", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		
		if (answer == JOptionPane.YES_OPTION) {
			if(addRepport(e))
				sendEmail(fromRepport, null, path);
		}
	}
	
	  /*******************/
	 /** EMAIL METHODS **/
	/*******************/

	private void sendFeedback(String message) {
		sendEmail(fromFeedback, message, null);
	}
	private boolean addRepport(Exception e) {		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
			PrintWriter pw = new PrintWriter(writer);
			e.printStackTrace(pw);
			pw.print("\n\n");
			writer.close();

			URI uri = this.getClass().getResource(path).toURI();
	        return Files.readAllLines(Paths.get(uri), Charset.defaultCharset()).size() > 100;
		} catch (IOException | URISyntaxException e1) {
			return false;
		}
	}
	
	
	
	private void sendEmail(String from, String message, String path) {
		
//	      Properties properties = System.getProperties();
//	      properties.setProperty("mail.smtp.host", host);
//	      Session session = Session.getDefaultInstance(properties);
//
//	      try {
//	         MimeMessage message = new MimeMessage(session);
//	         message.setFrom(new InternetAddress(from));
//	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//	         message.setSubject("This is the Subject Line!");
//	         message.setText("This is actual message");
//	         Transport.send(message);
//		
//			if(path != null) {
//		        Multipart multipart = new MimeMultipart();
//		        multipart.addBodyPart(messageBodyPart);
//		
//		        messageBodyPart = new MimeBodyPart();
//		        DataSource source = new FileDataSource(path);
//		        messageBodyPart.setDataHandler(new DataHandler(source));
//		        messageBodyPart.setFileName(filename);
//		        multipart.addBodyPart(messageBodyPart);
//		        message.setContent(multipart );
//		   }
//		
	         debug(this, "Repport sent");
//	      } catch (MessagingException mex) {
//	         mex.printStackTrace();
//	      }
		
	}
}
