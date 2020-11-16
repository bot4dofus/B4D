package fr.B4D.socket.store;

import fr.B4D.socket.result.HDVItemViewSocketResult;

/**
 * The {@code HDVItemViewSocketStore} is used to store {@code HDVItemViewSocketResult} . This class extends {@code SocketStore}.
 * 
 * @author Lucas
 *
 */
public class HDVItemViewSocketStore extends SocketStore<HDVItemViewSocketResult> {
	
	private static HDVItemViewSocketStore instance;

	/**
	 * Returns the instance of the store.
	 * @return Instance of the store.
	 */
	public static HDVItemViewSocketStore getInstance() {
		if(instance == null)
			instance = new HDVItemViewSocketStore();
		return instance;
	}
	
	private HDVItemViewSocketStore() {
		super(1);
	}
}
