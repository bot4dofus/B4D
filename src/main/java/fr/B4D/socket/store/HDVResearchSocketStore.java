package fr.B4D.socket.store;

import fr.B4D.socket.result.HDVResearchSocketResult;

public class HDVResearchSocketStore extends SocketStore<HDVResearchSocketResult> {
	
	private static HDVResearchSocketStore instance;

	public static HDVResearchSocketStore getInstance() {
		if(instance == null)
			instance = new HDVResearchSocketStore();
		return instance;
	}
	
	private HDVResearchSocketStore() {
		super(3);
	}
}
