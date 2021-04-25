package fr.B4D.socket.store;

/**
 * Handles an event of generic type T.
 * 
 * @author Lucas
 *
 * @param <T> Handled event type.
 */
public interface EventHandler<T> {
	
	/**
	 * Called when an event of this type occurs.
	 * @param socketEvent - Type the received event.
	 * @return {@code true} to continue receiving events, {@code false} otherwise.
	 */
	public boolean onEventReceived(T socketEvent);
}
