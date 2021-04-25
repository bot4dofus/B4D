package fr.B4D.socket.parser;

import fr.B4D.bot.B4DException;
import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.event.DofusEvent;

/**
 * The {@code SocketParser} class is the super class of the parsers.
 * 
 * @author Lucas
 * 
 * @param <T> The type of the parser result.
 *
 */
public abstract class SocketParser<T extends DofusEvent> {
	
	/**
	 * Parse a dofus socket.
	 * @param dofusSocket - Socket to parse.
	 * @return {@code true} if the socket has been parsed, {@code false} otherwise.
	 * @throws B4DException If a B4D exception occurs.
	 */
	public abstract T parse(DofusSocket dofusSocket) throws B4DException;
}
