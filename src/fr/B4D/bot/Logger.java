package fr.B4D.bot;

import javax.swing.JOptionPane;

public class Logger {

	  /**************/
	 /** ATRIBUTS **/
	/**************/

	private final String host = "localhost";
	private final String to = "b4d@develloper.com";
	private final String fromFeedback = "feedback@b4d.com";
	private final String fromRepport = "repport@b4d.com";
	
	  /*************/
	 /** METHODS **/
	/*************/
	
	public void popUp(String log) {
		JOptionPane.showMessageDialog(null, log, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void debug(String log) {
		System.out.println(log);
	}

	public void warning(String log) {
		System.err.println(log);
	}

	public void error(Exception e) {
		e.printStackTrace();
		int answer = JOptionPane.showConfirmDialog(null, e.getMessage() + "\n\nVoulez vous envoyer le rappot d'erreur au développeur ?", "Erreur", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		if (answer == JOptionPane.YES_OPTION) {
			sendRepport(e);
		}
	}
	
	  /*******************/
	 /** EMAIL METHODS **/
	/*******************/

	private void sendFeedback(String message) {
		sendEmail(fromFeedback, message, null);
	}
	private void sendRepport(Exception e) {
		sendEmail(fromFeedback, null, e);
	}
	
	private void sendEmail(String from, String message, Exception e) {
		
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
//	         debug("Repport sent");
//	      } catch (MessagingException mex) {
//	         mex.printStackTrace();
//	      }
		
	}
}
