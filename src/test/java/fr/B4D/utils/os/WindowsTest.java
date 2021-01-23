package fr.B4D.utils.os;

import java.awt.AWTException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

@SuppressWarnings("javadoc")
@Ignore
public class WindowsTest {

	@Test
	public void focusAnkamaLauncher() throws StopProgramException, CancelProgramException, AWTException {
		Assert.assertTrue(new Windows().setFocus("Ankama Launcher"));
	}
}
