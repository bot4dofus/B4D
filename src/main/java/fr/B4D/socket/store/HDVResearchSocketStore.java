package fr.B4D.socket.store;

import fr.B4D.socket.result.HDVFilterResultEvent;

/**
 * The {@code HDVResearchSocketStore} is used to store {@code HDVResearchSocketResult} . This class extends {@code SocketStore}.
 * 
 * @author Lucas
 *
 */
public class HDVResearchSocketStore extends EventStore<HDVFilterResultEvent> {
	
	private static HDVResearchSocketStore instance;

	/**
	 * Returns the instance of the store.
	 * @return Instance of the store.
	 */
	public static HDVResearchSocketStore getInstance() {
		if(instance == null)
			instance = new HDVResearchSocketStore();
		return instance;
	}
	
	private HDVResearchSocketStore() {
		super(3);
	}
}
