package fr.B4D.interaction.chat;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Server;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

@SuppressWarnings("javadoc")
public class ChannelTest {

	private static B4D b4d;
	private static Server server;
	
	@BeforeClass
	public static void before() throws ClassNotFoundException, B4DException, IOException, AWTException {
		b4d = new B4D();
		server = b4d.getTeam().get(0).getServer();
	}
	
	@Test
	public void set1() throws StopProgramException, CancelProgramException, AWTException {
		List<Channel> toggles = Channel.displayChannels(server, Channel.TEAM, Channel.ALLIES, Channel.GROUP, Channel.GUILD);
		toggles.stream().forEach(c -> System.out.println(c));
		int answer = JOptionPane.showConfirmDialog(null, "Has the channels team, allies, group and guild been turned on ?", "Unit test", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		Assert.assertEquals(answer, JOptionPane.YES_OPTION);
	}
	
	@Test
	public void set2() throws StopProgramException, CancelProgramException, AWTException {
		List<Channel> toggles = Channel.displayChannels(server, Channel.GENERAL, Channel.BUSINESS, Channel.RECRUITMENT, Channel.PRIVATE);
		toggles.stream().forEach(c -> System.out.println(c));
		int answer = JOptionPane.showConfirmDialog(null, "Has the channels general, business, recruitment and private been turned on ?", "Unit test", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		Assert.assertEquals(answer, JOptionPane.YES_OPTION);
	}
}
