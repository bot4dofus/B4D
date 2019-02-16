package test.libs;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.*;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;

public class DumbsterTest {
	private static final String FROM = "b4d@feedback.fr";
	private static final String TO = "b4d.developer@outlook.com";
	
	private SimpleSmtpServer server;

	@Before
	public void setUp() throws Exception {
		server = SimpleSmtpServer.start(SimpleSmtpServer.AUTO_SMTP_PORT);
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void testSend() throws MessagingException {
		sendMessage(server.getPort(), FROM, "Test", "Test Body", TO);

		List<SmtpMessage> emails = server.getReceivedEmails();
		Assert.assertEquals(1, emails.size());
		SmtpMessage email = emails.get(0);
		Assert.assertEquals("Test", email.getHeaderValue("Subject"));
		Assert.assertEquals("Test Body", email.getBody());
		Assert.assertTrue(email.getHeaderNames().contains("Date"));
		Assert.assertTrue(email.getHeaderNames().contains("From"));
		Assert.assertTrue(email.getHeaderNames().contains("To"));
		Assert.assertTrue(email.getHeaderNames().contains("Subject"));
		Assert.assertTrue(email.getHeaderValues("To").contains(TO));
		Assert.assertEquals(TO, email.getHeaderValue("To"));
	}

	@Test
	public void testSendAndReset() throws MessagingException {
		sendMessage(server.getPort(), FROM, "Test", "Test Body", TO);
		Assert.assertEquals(1, server.getReceivedEmails().size());

		server.reset();
		Assert.assertEquals(0, server.getReceivedEmails().size());

		sendMessage(server.getPort(), FROM, "Test", "Test Body", TO);
		Assert.assertEquals(1, server.getReceivedEmails().size());
	}

	@Test
	public void testSendMessageWithCR() throws MessagingException {
		String bodyWithCR = "\n\nKeep these pesky carriage returns\n\n";
		sendMessage(server.getPort(), FROM, "CRTest", bodyWithCR, TO);

		List<SmtpMessage> emails = server.getReceivedEmails();
		Assert.assertEquals(1, emails.size());
		SmtpMessage email = emails.get(0);
		Assert.assertEquals(bodyWithCR, email.getBody());
	}

	@Test
	public void testSendTwoMessagesSameConnection() throws MessagingException {
		MimeMessage[] mimeMessages = new MimeMessage[2];
		Properties mailProps = getMailProperties(server.getPort());
		Session session = Session.getInstance(mailProps, null);

		mimeMessages[0] = createMessage(session, FROM, TO, "Doodle1", "Bug1");
		mimeMessages[1] = createMessage(session, FROM, TO, "Doodle2", "Bug2");

		Transport transport = session.getTransport("smtp");
		transport.connect("localhost", server.getPort(), null, null);

		for (int i = 0; i < mimeMessages.length; i++) {
			MimeMessage mimeMessage = mimeMessages[i];
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		}

		transport.close();

		Assert.assertEquals(2, server.getReceivedEmails().size());
	}

	@Test
	public void testSendTwoMsgsWithLogin() throws Exception {
		String serverHost = "localhost";
		String subject = "Test";
		String body = "Test Body";

		Properties props = System.getProperties();

		if (serverHost != null) {
			props.setProperty("mail.smtp.host", serverHost);
		}

		Session session = Session.getDefaultInstance(props, null);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM));

		InternetAddress.parse(TO, false);
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO, false));
		msg.setSubject(subject);

		msg.setText(body);
		msg.setHeader("X-Mailer", "musala");
		msg.setSentDate(new Date());
		msg.saveChanges();

		Transport transport = null;

		try {
			transport = session.getTransport("smtp");
			transport.connect(serverHost, server.getPort(), "ddd", "ddd");
			transport.sendMessage(msg, InternetAddress.parse(TO, false));
			transport.sendMessage(msg, InternetAddress.parse(TO, false));
		} finally {
			if (transport != null) {
				transport.close();
			}
		}

		List<SmtpMessage> emails = this.server.getReceivedEmails();
		Assert.assertEquals(2, emails.size());
		SmtpMessage email = emails.get(0);
		Assert.assertTrue(email.getHeaderValue("Subject").equals("Test"));
		Assert.assertTrue(email.getBody().equals("Test Body"));
	}

	private Properties getMailProperties(int port) {
		Properties mailProps = new Properties();
		mailProps.setProperty("mail.smtp.host", "localhost");
		mailProps.setProperty("mail.smtp.port", "" + port);
		mailProps.setProperty("mail.smtp.sendpartial", "true");
		return mailProps;
	}


	private void sendMessage(int port, String from, String subject, String body, String to) throws MessagingException {
		Properties mailProps = getMailProperties(port);
		Session session = Session.getInstance(mailProps, null);
		//session.setDebug(true);

		MimeMessage msg = createMessage(session, from, to, subject, body);
		Transport.send(msg);
	}

	private MimeMessage createMessage(
			Session session, String from, String to, String subject, String body) throws MessagingException {
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(body);
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		return msg;
	}
}
