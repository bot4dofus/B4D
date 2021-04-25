package fr.B4D.socket.event;

import java.util.List;

import fr.B4D.dofus.items.Item;

/**
 * The {@code HDVItemViewSocketResult} class represents the result of an {@code HDVResearchSocketResult}. This class extends {@code SocketResult}.
 * 
 * @author Lucas
 *
 */
public class HDVFilterResultEvent extends DofusEvent{
	
	/**
	 * List of items in the result.
	 */
	private List<Item> items;
	
	/**
	 * Items in the result.
	 * @param items - List of items in the result.
	 */
	public HDVFilterResultEvent(List<Item> items) {
		this.items = items;
	}
	
	/**
	 * Returns the items in the result.
	 * @return List of items in the result.
	 */
	public List<Item> getItems() {
		return items;
	}
}
