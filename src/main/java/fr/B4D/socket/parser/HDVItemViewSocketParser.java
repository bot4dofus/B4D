package fr.B4D.socket.parser;

import java.util.Arrays;
import java.util.List;

import fr.B4D.socket.DofusSocket;
import fr.B4D.socket.SocketElement;
import fr.B4D.socket.SocketUtils;
import fr.B4D.socket.result.HDVItemViewSocketResult;
import fr.B4D.socket.store.HDVItemViewSocketStore;

public class HDVItemViewSocketParser extends SocketParser<HDVItemViewSocketResult>{

	public static final byte[] DELIMITER = {0x00, 0x00, 0x00, 0x03};

	//		... | 0x00 0x00 0x00 0x03 | Price 1 (Big endian) | Price 10 (Big endian) | Price 100 (Big endian) | ...
	//		    |          4          |           x          |            x          |             x          |
	
	public HDVItemViewSocketParser() {
		super();
	}

	public HDVItemViewSocketResult parse(DofusSocket dofusSocket) {
		
		Integer index = SocketUtils.findArray(DELIMITER, dofusSocket.getPayload());

		if(index == null)
			return null;
		
		SocketElement numbersElement = new SocketElement(Arrays.copyOfRange(dofusSocket.getPayload(), index + DELIMITER.length, dofusSocket.getPayload().length));
		List<Integer> numbers = numbersElement.asBigEndians();
		
		Integer price1 = numbers.size() >= 1 ? numbers.get(0) : 0;
		Integer price10 = numbers.size() >= 2 ? numbers.get(1) : 0;
		Integer price100 = numbers.size() >= 3 ? numbers.get(2) : 0;
		
		HDVItemViewSocketResult result = new HDVItemViewSocketResult(price1, price10, price100);
		HDVItemViewSocketStore.getInstance().addSocketResult(result);
		return result;
	}
}
