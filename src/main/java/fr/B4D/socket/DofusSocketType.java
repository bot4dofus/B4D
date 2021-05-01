package fr.B4D.socket;

import fr.B4D.socket.parser.ChatMessageSocketParser;
import fr.B4D.socket.parser.HDVItemViewSocketParser;
import fr.B4D.socket.parser.HDVResearchSocketParser;
import fr.B4D.socket.parser.ChangeMapEventParser;
import fr.B4D.socket.parser.PlayerEnterMapEventParser;

/**
 * The class {@code SocketType} defines all the known sockets and map it with the corresponding socket parser.
 * 
 *
 * 
 * @author Lucas
 *
 */
public enum DofusSocketType {

	//TODO The socket ids changes for every update. We need to load the ids from a bundle file in (or next to) the jar file. This file would be updated on startup.
	//CURRENT IDS FOR VERSION 2.59
	
	/**
	 * Socket representing an item view in HDV.
	 */
	HDV_ITEM_VIEW_SOCKET((byte) 0x33, HDVItemViewSocketParser.class),
	
	/**
	 * Socket representing the result of an HDV filter, only for the equipment HDV.
	 */
	HDV_FILTER_EQUIPMENT_SOCKET((byte) 0x75, HDVResearchSocketParser.class),

	/**
	 * Socket representing a message in the chat.
	 */
	CHAT_MESSAGE_SOCKET((byte) 0x03, ChatMessageSocketParser.class),

	/**
	 * Socket representing an opened enclose.
	 */
	DD_ENCLOSE_OPEN_SOCKET((byte) 0x00, null), //TODO find the id

	/**
	 * Socket representing a move from a map to another.
	 */
	CHANGE_MAP_SOCKET((byte) 0x4f, ChangeMapEventParser.class),

	/**
	 * Socket representing a player entering a map.
	 */
	PLAYER_ENTER_MAP_SOCKET((byte) 0x85, PlayerEnterMapEventParser.class);
	
	/**
	 * Returns the socket type corresponding to the received socket.
	 * @param socket Byte array representing the content of the socket.
	 * @return The corresponding socket type, {@code null} if none found.
	 */
	public static DofusSocketType fromSocket(byte[] socket) {		
		for(DofusSocketType socketType:DofusSocketType.values()) {
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
	DofusSocketType(byte id, Class<?> parser) {
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
