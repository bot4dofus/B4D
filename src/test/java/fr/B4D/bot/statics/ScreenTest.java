package fr.B4D.bot.statics;

import java.awt.AWTException;
import java.awt.Point;
import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Configuration;
import fr.B4D.filemanager.FileManagerFactory;
import fr.B4D.utils.Rectangle;

@SuppressWarnings("javadoc")
@Ignore
public class ScreenTest {

	private Screen screen;
	
	@Before
	public void before() throws ClassNotFoundException, IOException, AWTException, B4DException {
		Configuration configuration = FileManagerFactory.getConfigurationFileManager().read();
		screen = new Screen(configuration);
	}
	
	@Test
	public void test() throws B4DException {
		String out = screen.OCR(new Rectangle(new Point(0,0), new Point(500,300)));
		System.out.println(out);
	}
}
