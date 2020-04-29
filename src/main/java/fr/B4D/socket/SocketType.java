package fr.B4D.socket;

import java.util.Arrays;

import fr.B4D.socket.parser.ChatMessageSocketParser;
import fr.B4D.socket.parser.HDVItemViewSocketParser;

public enum SocketType {
	
	HDV_ITEM_VIEW_SOCKET(new byte[]{0x59, (byte) 0xe1}, 1, HDVItemViewSocketParser.class),
	
	CHAT_MESSAGE_C5_SOCKET(new byte[]{0x0d, (byte) 0xc5}, 1, ChatMessageSocketParser.class),
	CHAT_MESSAGE_CD_SOCKET(new byte[]{0x0d, (byte) 0xcd}, 1, ChatMessageSocketParser.class),
	CHAT_MESSAGE_C9_SOCKET(new byte[]{0x0d, (byte) 0xc9}, 1, ChatMessageSocketParser.class),
	CHAT_MESSAGE_C6_SOCKET(new byte[]{0x0d, (byte) 0xc6}, 2, ChatMessageSocketParser.class),
	CHAT_MESSAGE_CE_SOCKET(new byte[]{0x0d, (byte) 0xce}, 2, ChatMessageSocketParser.class);
	
	public static SocketType fromSocket(byte[] socket) {
		byte[] id = Arrays.copyOfRange(socket, 0, 2);
		
		for(SocketType socketType:SocketType.values()) {
			if(Arrays.equals(id, socketType.getId()))
				return socketType;
		}
		return null;
	}
	
	private byte[] id;
	private Integer bytesSocketLength;
	private Class<?> parser;
	
	SocketType(byte[] id, Integer bytesSocketLength, Class<?> parser) {
		this.id = id;
		this.bytesSocketLength = bytesSocketLength;
		this.parser = parser;
	}
	
	public byte[] getId() {
		return id;
	}

	public Integer getBytesSocketLength() {
		return bytesSocketLength;
	}
	
	public Class<?> getParser() {
		return parser;
	}
}
