package fr.B4D.socket.result;

import java.util.List;

import fr.B4D.dofus.items.Item;

public class HDVResearchSocketResult extends SocketResult{
	
	private List<Item> items;
	
	public HDVResearchSocketResult(List<Item> items) {
		this.items = items;
	}
	
	public List<Item> getItems() {
		return items;
	}
}
