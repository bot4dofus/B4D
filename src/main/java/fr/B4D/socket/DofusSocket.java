package fr.B4D.socket;

import java.util.Arrays;

import fr.B4D.socket.parser.SocketParser;
import fr.B4D.utils.HexHelper;

public class DofusSocket {

	//		| Socket Id | Socket length | payload | ...
	//		|     2     |        1      |    x    |
	
	private SocketType socketType;
	private int length;
	private byte[] payload;

	public DofusSocket(byte[] socket) throws IllegalArgumentException {
		if(socket.length < 4)
			throw new IllegalArgumentException("Cannot be less than 4.");			

		this.socketType = SocketType.fromSocket(socket);
		if(this.socketType == null)
			throw new IllegalArgumentException("Unknown socket : [" + HexHelper.toString(socket) + "]");
		
		this.length = new SocketElement(Arrays.copyOfRange(socket, 2, 2+socketType.getBytesSocketLength())).asSmallEndian();
		this.payload = Arrays.copyOfRange(socket, 2+socketType.getBytesSocketLength(), 2+socketType.getBytesSocketLength()+length);
	}

	public int getLength() {
		return length;
	}

	public byte[] getPayload() {
		return payload;
	}
	
	public SocketParser<?> getParser() throws InstantiationException, IllegalAccessException {
		return (SocketParser<?>) socketType.getParser().newInstance();
	}
}

