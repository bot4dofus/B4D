package fr.B4D.socket.parser;

import java.util.List;

import fr.B4D.bot.B4DException;
import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.DofusSocketIterator;
import fr.B4D.socket.event.HDVItemViewEvent;

/**
 * The {@code HDVItemViewSocketParser} class is used to parse a socket relative to an item view in an HDV.
 * 
 * @author Lucas
 *
 */
public class HDVItemViewSocketParser extends SocketParser<HDVItemViewEvent>{

	/**
	 * Delimiter before the useful data.
	 */
	public static final byte[] DELIMITER = {0x00, 0x00, 0x00, 0x03};

	//		... | 0x00 0x00 0x00 0x03 | Price 1 (Big endian) | Price 10 (Big endian) | Price 100 (Big endian) | ...
	//		    |          4          |           x          |            x          |             x          |

	@Override
	public HDVItemViewEvent parse(DofusSocket dofusSocket) throws B4DException {
		DofusSocketIterator iterator = new DofusSocketIterator(dofusSocket);

		iterator.skip(9);

		Integer id;
		Integer price1;
		Integer price10;
		Integer price100;
		
		try {
			id = iterator.getNextSocketElement(2).asBigEndian();
	
			Integer index = iterator.moveAfterPattern(DELIMITER);
			List<Integer> numbers = iterator.getNextSocketElement(dofusSocket.getPayload().length-index).asBigEndians();
	
			price1 = numbers.size() >= 1 ? numbers.get(0) : 0;
			price10 = numbers.size() >= 2 ? numbers.get(1) : 0;
			price100 = numbers.size() >= 3 ? numbers.get(2) : 0;
		}catch(B4DException e) {	//If the item is not in sale
			id = 0;
			price1 = 0;
			price10 = 0;
			price100 = 0;
		}

		HDVItemViewEvent result = new HDVItemViewEvent(id, price1, price10, price100);
		return result;
	}
}
