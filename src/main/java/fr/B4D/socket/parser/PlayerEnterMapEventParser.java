package fr.B4D.socket.parser;

import java.util.Arrays;

import fr.B4D.bot.B4DException;
import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.DofusSocketIterator;
import fr.B4D.socket.event.PlayerEnterMapEvent;

/**
 * The {@code ChangeMapEventParser} class is used to parse a socket relative to a player moving from a map to another.
 * 
 * @author Lucas
 *
 */
public class PlayerEnterMapEventParser extends SocketParser<PlayerEnterMapEvent>{

	/**
	 * Delimiter before the player name.
	 */
	public static final byte[] START_DELIMITER = {0x00, 0x00, 0x00};
	
	/**
	 * Delimiter after the player name.
	 */
	public static final byte[] END_DELIMITER = {0x1b, 0x49, 0x00, 0x08};
	
	@Override
	public PlayerEnterMapEvent parse(DofusSocket dofusSocket) throws B4DException {
		DofusSocketIterator iterator = new DofusSocketIterator(dofusSocket);
		String pseudo;
		do {
			iterator.moveAfterPattern(START_DELIMITER);
			Integer pseudoLenght = iterator.getNextSocketElement(1).asSmallEndian();
			pseudo = iterator.getNextSocketElement(pseudoLenght).asString();			
		}while(!Arrays.equals(iterator.getNextSocketElement(4).getPayload(), END_DELIMITER));

		PlayerEnterMapEvent event = new PlayerEnterMapEvent(pseudo);
		return event;
	}
}
