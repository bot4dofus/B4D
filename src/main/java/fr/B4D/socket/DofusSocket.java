package fr.B4D.socket;

import java.util.Arrays;

import fr.B4D.bot.B4DException;
import fr.B4D.socket.event.DofusEvent;
import fr.B4D.socket.parser.SocketParser;
import fr.B4D.utils.HexHelper;

/**
 * The {@code DofusSocket} class represent a dofus socket.
 * 
 * @author Lucas
 *
 */
public class DofusSocket {

	/**
	 * Mask to get the size of the socket length.
	 */
	public static int SOCKET_LENGTH_MASK = 0x03;
	
	//		| Socket Id | Flags ? | Socket length | payload | ...
	//		|     1     |    1    |       x       |    x    |
	
	private DofusSocketType socketType;
	private int socketLengthSize;
	private int length;
	private byte[] payload;

	/**
	 * Constructs a {@code DofusSocket} from bytes.
	 * @param socket - Bytes of the socket.
	 * @throws IllegalArgumentException If the argument socket argument is wrong.
	 */
	public DofusSocket(byte[] socket) throws IllegalArgumentException {
		if(socket == null)
			throw new IllegalArgumentException("Cannot be null.");	
		
		if(socket.length < 4)
			throw new IllegalArgumentException("Cannot be less than 4.");			

		this.socketType = DofusSocketType.fromSocket(socket);
		if(this.socketType == null)
			throw new IllegalArgumentException("Unknown socket : [" + HexHelper.toString(socket) + "]");
		
		
		this.socketLengthSize = socket[1] & SOCKET_LENGTH_MASK;
		this.length = new DofusSocketElement(Arrays.copyOfRange(socket, 2, 2+socketLengthSize)).asSmallEndian();
		this.payload = Arrays.copyOfRange(socket, 2+socketLengthSize, 2+socketLengthSize+length);
	}

	/**
	 * Returns the size the socket length.
	 * @return Size of the socket length.
	 */
	public int getSocketLengthSize() {
		return socketLengthSize;
	}
	
	/**
	 * Returns the socket length.
	 * @return Socket length.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns the payload of the socket.
	 * @return Payload of the socket.
	 */
	public byte[] getPayload() {
		return payload;
	}
	
	/**
	 * Returns an instance of the parser used to parse this socket.
	 * @return Instance of the parser to use.
	 * @throws B4DException If could instantiate the parser.
	 */
	@SuppressWarnings("unchecked")
	public SocketParser<DofusEvent> getParser() throws B4DException {
		try {
			return (SocketParser<DofusEvent>) socketType.getParser().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new B4DException(e);
		}
	}
}

