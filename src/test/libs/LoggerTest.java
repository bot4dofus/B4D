package test.libs;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import fr.B4D.bot.statics.Logger;
import fr.B4D.socket.NoSocketDetectedException;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.InvalidFilterException;

public class LoggerTest {

	private Logger logger;
	
	@Before
	public void before() throws ClassNotFoundException, IOException, CaptureDeviceLookupException, NoSocketDetectedException, CaptureDeviceOpenException, InvalidFilterException {
		logger = new Logger();
	}
	
	@Test
	public void test() {
		try {
			throw new Exception("This is a test");
		} catch (Exception e) {
			logger.addRepport(e);
			logger.sendEmail("JUnit test", "This is a test", "errors.txt");
		}
	}
}
