package fr.B4D.socket.result;

import java.time.LocalDateTime;

/**
 * The {@code SocketResult} class represents the result of a socket. This is an abstract class.
 * @author Lucas
 *
 */
public class SocketResult {

	private LocalDateTime dateTime;
	
	/**
	 * Constructor of the socket result class.
	 */
	public SocketResult() {
		super();
		dateTime = LocalDateTime.now();
	}

	/**
	 * Returns the date and time when the event occurred.
	 * @return Date and time of the event.
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}
}
