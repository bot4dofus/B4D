package fr.B4D.interaction.chat;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;

public class ChannelTest_ {

	private static boolean setUpDone = false;
	
	@Before
	public void before() throws ClassNotFoundException, B4DException, IOException, CaptureDeviceLookupException, CaptureDeviceOpenException, AWTException {
		if(!setUpDone) {
			@SuppressWarnings("unused")
			B4D b4d = new B4D();
			setUpDone = true;
		}
	}
	
	@Test
	public void set1() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		List<Channel> toggles = Channel.displayChannels(Channel.TEAM, Channel.ALLIES, Channel.GROUP, Channel.GUILD);
		toggles.stream().forEach(c -> System.out.println(c));
		Thread.sleep(1000);
	}
	
	@Test
	public void set2() throws StopProgramException, CancelProgramException, AWTException, InterruptedException {
		List<Channel> toggles = Channel.displayChannels(Channel.GENERAL, Channel.BUSINESS, Channel.RECRUITMENT, Channel.PRIVATE);
		toggles.stream().forEach(c -> System.out.println(c));
		Thread.sleep(1000);
	}
}
