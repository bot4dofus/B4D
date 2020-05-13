package fr.B4D.socket.parser;

import org.junit.Assert;
import org.junit.Test;

import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.SocketType;
import fr.B4D.socket.parser.HDVItemViewSocketParser;
import fr.B4D.socket.parser.SocketParser;
import fr.B4D.socket.result.HDVItemViewSocketResult;

public class HDVItemViewSocketParserTest {

	@Test
	public void test1() throws StopProgramException, CancelProgramException, InstantiationException, IllegalAccessException {
		byte[] socket = {0x59, (byte) 0xe1, 0x17, 0x00, 0x00, 0x00, 0x0f, 0x00, 0x01, (byte) 0x9b, (byte) 0xf6, 0x19, (byte) 0xf9, 0x11, 0x00, 0x00, 0x00, 0x0f, 0x00, 0x00, 0x00, 0x03, 0x01, 0x09, (byte) 0xe9, 0x03, 0x02, (byte) 0xc0};

		Assert.assertEquals(SocketType.HDV_ITEM_VIEW_SOCKET, SocketType.fromSocket(socket));
		
		DofusSocket dofusSocket = new DofusSocket(socket);
		SocketParser<?> parser = dofusSocket.getParser();

		Assert.assertEquals(HDVItemViewSocketParser.class, parser.getClass());
		
		HDVItemViewSocketResult result = new HDVItemViewSocketParser().parse(dofusSocket);
		Assert.assertEquals(new Integer(1), result.getPrice1());
		Assert.assertEquals(new Integer(9), result.getPrice10());
		Assert.assertEquals(new Integer(489), result.getPrice100());
	}
}