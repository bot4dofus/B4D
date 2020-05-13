package fr.B4D.socket;

import java.util.Arrays;

import fr.B4D.socket.parser.ChatMessageSocketParser;
import fr.B4D.socket.parser.HDVItemViewSocketParser;
import fr.B4D.socket.parser.HDVResearchSocketParser;

public enum SocketType {
	
	HDV_ITEM_VIEW_SOCKET(new byte[]{0x59, (byte) 0xe1}, 1, HDVItemViewSocketParser.class),
	HDV_RESEARCH_15_SOCKET(new byte[]{0x5a, 0x15}, 1, HDVResearchSocketParser.class),
	HDV_RESEARCH_16_SOCKET(new byte[]{0x5a, 0x16}, 2, HDVResearchSocketParser.class),

	CHAT_MESSAGE_C5_SOCKET(new byte[]{0x0d, (byte) 0xc5}, 1, ChatMessageSocketParser.class),
	CHAT_MESSAGE_CD_SOCKET(new byte[]{0x0d, (byte) 0xcd}, 1, ChatMessageSocketParser.class),
	CHAT_MESSAGE_C9_SOCKET(new byte[]{0x0d, (byte) 0xc9}, 1, ChatMessageSocketParser.class),
	CHAT_MESSAGE_C6_SOCKET(new byte[]{0x0d, (byte) 0xc6}, 2, ChatMessageSocketParser.class),
	CHAT_MESSAGE_CE_SOCKET(new byte[]{0x0d, (byte) 0xce}, 2, ChatMessageSocketParser.class),

	DD_ENCLOSE_OPEN_9D_SOCKET(new byte[]{0x5d, (byte) 0x9d}, 1, null),
	DD_ENCLOSE_OPEN_9E_SOCKET(new byte[]{0x5d, (byte) 0x9e}, 2, null),
	DD_ENCLOSE_OPEN_6E_SOCKET(new byte[]{0x5d, (byte) 0x6e}, 2, null);
	
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
