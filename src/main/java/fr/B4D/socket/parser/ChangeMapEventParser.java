package fr.B4D.socket.parser;

import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.event.ChangeMapEvent;

/**
 * The {@code ChangeMapEventParser} class is used to parse a socket relative to a player moving from a map to another.
 * 
 * @author Lucas
 *
 */
public class ChangeMapEventParser extends SocketParser<ChangeMapEvent>{

	@Override
	public ChangeMapEvent parse(DofusSocket dofusSocket) {
		ChangeMapEvent result = new ChangeMapEvent();
		return result;
	}
}
