package fr.B4D.socket.store;

import fr.B4D.socket.result.HDVItemViewSocketResult;

public class HDVItemViewSocketStore extends SocketStore<HDVItemViewSocketResult> {
	
	private static HDVItemViewSocketStore instance;

	public static HDVItemViewSocketStore getInstance() {
		if(instance == null)
			instance = new HDVItemViewSocketStore();
		return instance;
	}
	
	private HDVItemViewSocketStore() {
		super(1);
	}
}
