package fr.B4D.socket.store;

import fr.B4D.socket.result.HDVItemViewEvent;

/**
 * The {@code HDVItemViewSocketStore} is used to store {@code HDVItemViewSocketResult} . This class extends {@code SocketStore}.
 * 
 * @author Lucas
 *
 */
public class HDVItemViewEventStore extends EventStore<HDVItemViewEvent> {
	
	private static HDVItemViewEventStore instance;

	/**
	 * Returns the instance of the store.
	 * @return Instance of the store.
	 */
	public static HDVItemViewEventStore getInstance() {
		if(instance == null)
			instance = new HDVItemViewEventStore();
		return instance;
	}
	
	private HDVItemViewEventStore() {
		super(1);
	}
}
