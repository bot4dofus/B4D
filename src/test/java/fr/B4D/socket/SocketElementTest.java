package fr.B4D.socket;

import org.junit.Assert;
import org.junit.Test;

import fr.B4D.socket.SocketElement;

@SuppressWarnings("javadoc")
public class SocketElementTest {
	
	@Test
	public void testSmallEndian() {
		byte[] array = {0x00, 0x45};
		SocketElement element = new SocketElement(array);
		Assert.assertEquals(new Integer(69), element.asSmallEndian());
	}
}
