package fr.B4D.dofus.items;

/** The {@code Stack} represent a stack of items. A {@code Stack} has an item and an amount.
 * 
 * @author Lucas
 * 
 */
public class Stack {
	
	private Item item;
	private int amount;
	
	/** Construct a {@code Stack} with an item and an amount of this item.
	 * @param item - Item of the stack.
	 * @param amount - Amount of items.
	 */
	public Stack(Item item, int amount) {
		super();
		
		if(item == null)
			throw new IllegalArgumentException("The stack item cannot be null.");
		
		this.item = item;
		this.amount = amount;
	}
	
	/** Return the item.
	 * @return - Item of the {@code Stack}.
	 */
	public Item getItem() {
		return item;
	}
	
	/** Return the amount.
	 * @return - Amount of the {@code Stack}.
	 */
	public int getAmount() {
		return amount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stack other = (Stack) obj;
		if (amount != other.amount)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stack [item=" + item.getName() + ", amount=" + amount + "]";
	}
}
