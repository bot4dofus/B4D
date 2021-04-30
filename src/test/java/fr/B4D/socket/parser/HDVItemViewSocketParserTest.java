package fr.B4D.socket.parser;

import org.junit.Assert;
import org.junit.Test;

import fr.B4D.bot.B4DException;
import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.DofusSocketType;
import fr.B4D.socket.event.HDVItemViewEvent;

@SuppressWarnings("javadoc")
public class HDVItemViewSocketParserTest {

	//Fleche du chafer (2297, 1, 9, 489)
	@Test
	public void test1() throws B4DException {
		byte[] socket = {(byte) 0x33, (byte) 0xe1, 0x17, 0x00, 0x00, 0x00, 0x0f, 0x00, 0x01, (byte) 0x9b, (byte) 0xf6, 0x19, (byte) 0xf9, 0x11, 0x00, 0x00, 0x00, 0x0f, 0x00, 0x00, 0x00, 0x03, 0x01, 0x09, (byte) 0xe9, 0x03, 0x02, (byte) 0xc0};

		Assert.assertEquals(DofusSocketType.HDV_ITEM_VIEW_SOCKET, DofusSocketType.fromSocket(socket));
		
		DofusSocket dofusSocket = new DofusSocket(socket);
		SocketParser<?> parser = dofusSocket.getParser();

		Assert.assertEquals(HDVItemViewSocketParser.class, parser.getClass());
		
		HDVItemViewEvent result = new HDVItemViewSocketParser().parse(dofusSocket);
		Assert.assertEquals(new Integer(2297), result.getId());
		Assert.assertEquals(new Integer(1), result.getPrice1());
		Assert.assertEquals(new Integer(9), result.getPrice10());
		Assert.assertEquals(new Integer(489), result.getPrice100());
	}
	
	//Aile de Bourdard (648, 19, 995, 14995)
	@Test
	public void test2() throws B4DException {
		byte[] socket = {(byte) 0x33, (byte) 0xd5, 0x18, 0x00, 0x00, 0x00, 0x68, 0x00, 0x01, (byte) 0xa2, (byte) 0xe5, 0x0b, (byte) 0x88, 0x05, 0x00, 0x00, 0x00, 0x68, 0x00, 0x00, 0x00, 0x03, 0x13, (byte) 0xe3, 0x07, (byte) 0x93, 0x75, 0x11, 0x5c};

		Assert.assertEquals(DofusSocketType.HDV_ITEM_VIEW_SOCKET, DofusSocketType.fromSocket(socket));
		
		DofusSocket dofusSocket = new DofusSocket(socket);
		SocketParser<?> parser = dofusSocket.getParser();
		
		Assert.assertEquals(HDVItemViewSocketParser.class, parser.getClass());
		
		HDVItemViewEvent result = new HDVItemViewSocketParser().parse(dofusSocket);
		Assert.assertEquals(new Integer(648), result.getId());
		Assert.assertEquals(new Integer(19), result.getPrice1());
		Assert.assertEquals(new Integer(995), result.getPrice10());
		Assert.assertEquals(new Integer(14995), result.getPrice100());
	}
	
	//Item not for sale (0, 0, 0, 0)
	@Test
	public void test3() throws B4DException {
		byte[] socket = {(byte) 0x33, (byte) 0xd5, 0x06, 0x00, 0x00, 0x00, 0x0f, 0x00, 0x00, 0x11, 0x5c};

		Assert.assertEquals(DofusSocketType.HDV_ITEM_VIEW_SOCKET, DofusSocketType.fromSocket(socket));
		
		DofusSocket dofusSocket = new DofusSocket(socket);
		SocketParser<?> parser = dofusSocket.getParser();
		
		Assert.assertEquals(HDVItemViewSocketParser.class, parser.getClass());
		
		HDVItemViewEvent result = new HDVItemViewSocketParser().parse(dofusSocket);
		Assert.assertEquals(new Integer(0), result.getId());
		Assert.assertEquals(new Integer(0), result.getPrice1());
		Assert.assertEquals(new Integer(0), result.getPrice10());
		Assert.assertEquals(new Integer(0), result.getPrice100());
	}
}