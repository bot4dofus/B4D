package fr.B4D.interaction;

import java.awt.AWTException;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;

public class StatusTest {

	@SuppressWarnings("unused")
	private static B4D b4d;
	
	@BeforeClass
	public static void before() throws ClassNotFoundException, B4DException, IOException, CaptureDeviceLookupException, CaptureDeviceOpenException, AWTException {
		b4d = new B4D();
	}
	
	@Test
	public void setAvailable() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		Status.AVAILABLE.setStatus();
		int answer = JOptionPane.showConfirmDialog(null, "Has the status been set to available ?", "Unit test", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		Assert.assertEquals(answer, JOptionPane.YES_OPTION);
	}
	
	@Test
	public void setAbsent() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		Status.ABSENT.setStatus();
		int answer = JOptionPane.showConfirmDialog(null, "Has the status been set to absent ?", "Unit test", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		Assert.assertEquals(answer, JOptionPane.YES_OPTION);
	}
	
	@Test
	public void setPrivate() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		Status.PRIVATE.setStatus();
		int answer = JOptionPane.showConfirmDialog(null, "Has the status been set to private ?", "Unit test", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		Assert.assertEquals(answer, JOptionPane.YES_OPTION);
	}
	
	@Test
	public void setSolo() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		Status.SOLO.setStatus();
		int answer = JOptionPane.showConfirmDialog(null, "Has the status been set to solo ?", "Unit test", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		Assert.assertEquals(answer, JOptionPane.YES_OPTION);
	}
}
