package fr.B4D.bot.statics;

import org.junit.Before;
import org.junit.Test;

public class LoggerTest_ {

	private Logger logger;
	
	@Before
	public void before() {
		logger = new Logger();
	}
	
	@Test
	public void addRepport() {
		logger.addRepport(new Exception("This is a test"));
	}
	
	@Test
	public void sendEmail() {
		logger.sendEmail("JUnit test", "This is a test", "errors.txt");
	}
}
