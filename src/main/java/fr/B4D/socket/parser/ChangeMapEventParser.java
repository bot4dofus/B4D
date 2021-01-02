package fr.B4D.socket.parser;

import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.result.ChangeMapEvent;
import fr.B4D.socket.store.ChangeMapEventStore;

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
		ChangeMapEventStore.getInstance().addSocketResult(result);
		return result;
	}
}
