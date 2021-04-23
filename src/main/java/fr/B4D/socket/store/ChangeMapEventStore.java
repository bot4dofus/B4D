package fr.B4D.socket.store;

import fr.B4D.socket.event.ChangeMapEvent;

/**
 * The {@code ChangeMapEventStore} is used to store {@code ChangeMapEvent} . This class extends {@code EventStore}.
 * 
 * @author Lucas
 *
 */
public class ChangeMapEventStore extends EventStore<ChangeMapEvent> {
	
	private static ChangeMapEventStore instance;

	/**
	 * Returns the instance of the store.
	 * @return Instance of the store.
	 */
	public static ChangeMapEventStore getInstance() {
		if(instance == null)
			instance = new ChangeMapEventStore();
		return instance;
	}
	
	private ChangeMapEventStore() {
		super(1);
	}
}
