package fr.B4D.socket;

import fr.B4D.socket.parser.ChatMessageSocketParser;
import fr.B4D.socket.parser.HDVItemViewSocketParser;
import fr.B4D.socket.parser.HDVResearchSocketParser;

/**
 * The class {@code SocketType} defines all the known sockets and map it with the corresponding socket parser.
 * @author Lucas
 *
 */
public enum SocketType {
	
	/**
	 * Socket representing an item view in HDV.
	 */
	HDV_ITEM_VIEW_SOCKET((byte) 0x8b, HDVItemViewSocketParser.class),
	
	/**
	 * Socket representing the result of an HDV research.
	 */
	HDV_FILTER_RESULT_SOCKET((byte) 0x95, HDVResearchSocketParser.class),

	/**
	 * Socket representing a message in the chat.
	 */
	CHAT_MESSAGE_SOCKET((byte) 0x0d, ChatMessageSocketParser.class),

	/**
	 * Socket representing an opened enclose.
	 */
	DD_ENCLOSE_OPEN_9D_SOCKET((byte) 0x5d, null);
	
	/**
	 * Returns the socket type corresponding to the received socket.
	 * @param socket Byte array representing the content of the socket.
	 * @return The corresponding socket type, {@code null} if none found.
	 */
	public static SocketType fromSocket(byte[] socket) {		
		for(SocketType socketType:SocketType.values()) {
			if(socket[0] == socketType.getId())
				return socketType;
		}
		return null;
	}
	
	private byte id;
	private Class<?> parser;
	
	/**
	 * Constructor of a socket type.
	 * @param id Byte id of the socket.
	 * @param parser Corresponding parser.
	 */
	SocketType(byte id, Class<?> parser) {
		this.id = id;
		this.parser = parser;
	}
	
	/**
	 * Returns the id of the socket type.
	 * @return Id of the socket.
	 */
	public byte getId() {
		return id;
	}
	
	/**
	 * Returns the parser of the socket type.
	 * @return The parser to use.
	 */
	public Class<?> getParser() {
		return parser;
	}
}
