package fr.B4D.interaction;

import java.awt.AWTException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;

public class StatusTest_ {

	@SuppressWarnings("unused")
	private B4D b4d;
	
	@BeforeClass
	public void before() throws ClassNotFoundException, B4DException, IOException, CaptureDeviceLookupException, CaptureDeviceOpenException, AWTException {
		b4d = new B4D();
	}
	
	@Test
	public void setAvailable() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		Assert.assertEquals(true, Status.AVAILABLE.setStatus());
		Thread.sleep(1000);
	}
	
	@Test
	public void setAbsent() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		Assert.assertEquals(true, Status.ABSENT.setStatus());
		Thread.sleep(1000);
	}
	
	@Test
	public void setPrivate() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		Assert.assertEquals(true, Status.PRIVATE.setStatus());
		Thread.sleep(1000);
	}
	
	@Test
	public void setSolo() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		Assert.assertEquals(true, Status.SOLO.setStatus());
		Thread.sleep(1000);
	}
}
