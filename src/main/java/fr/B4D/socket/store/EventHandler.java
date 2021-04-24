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
	 * @param socketEvent
	 */
	public void onEventReceived(T socketEvent);
}
