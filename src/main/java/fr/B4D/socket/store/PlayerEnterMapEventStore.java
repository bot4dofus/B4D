package fr.B4D.socket.store;

import fr.B4D.socket.event.PlayerEnterMapEvent;

/**
 * The {@code PlayerEnterMapEventStore} is used to store {@code PlayerEnterMapEvent} . This class extends {@code EventStore}.
 * 
 * @author Lucas
 *
 */
public class PlayerEnterMapEventStore extends EventStore<PlayerEnterMapEvent> {
	
	private static PlayerEnterMapEventStore instance;

	/**
	 * Returns the instance of the store.
	 * @return Instance of the store.
	 */
	public static PlayerEnterMapEventStore getInstance() {
		if(instance == null)
			instance = new PlayerEnterMapEventStore();
		return instance;
	}
	
	private PlayerEnterMapEventStore() {
		super(1);
	}
}
