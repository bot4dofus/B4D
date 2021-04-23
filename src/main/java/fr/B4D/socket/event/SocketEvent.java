package fr.B4D.socket.event;

import java.time.LocalDateTime;

/**
 * The {@code SocketResult} class represents the result of a socket. This is an abstract class.
 * 
 * @author Lucas
 *
 */
public class SocketEvent {

	private LocalDateTime dateTime;
	
	/**
	 * Constructor of the socket result class.
	 */
	public SocketEvent() {
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
