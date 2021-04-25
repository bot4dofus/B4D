package fr.B4D.socket;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class SocketElementTest {
	
	@Test
	public void testSmallEndian() {
		byte[] array = {0x00, 0x45};
		DofusSocketElement element = new DofusSocketElement(array);
		Assert.assertEquals(new Integer(69), element.asSmallEndian());
	}
}
