package fr.B4D.socket.result;

/**
 * The {@code HDVItemViewSocketResult} class represents the result of an {@code HDVItemViewSocketParser}. This class extends {@code SocketResult}.
 * 
 * @author Lucas
 *
 */
public class HDVItemViewSocketResult extends SocketResult{
	
	/**
	 * Price of the item per stack of 1.
	 */
	private Integer price1;
	
	/**
	 * Price of the item per stack of 10.
	 */
	private Integer price10;
	
	/**
	 * Price of the item per stack of 100.
	 */
	private Integer price100;
	
	/**
	 * Constructs a {@code HDVItemViewSocketResult}.
	 * @param price1 - Price of the item per stack of 1.
	 * @param price10 - Price of the item per stack of 10.
	 * @param price100 - Price of the item per stack of 100.
	 */
	public HDVItemViewSocketResult(Integer price1, Integer price10, Integer price100) {
		this.price1 = price1;
		this.price10 = price10;
		this.price100 = price100;
	}
	
	/**
	 * Returns the price of the item per stack of 1.
	 * @return Price of the stack.
	 */
	public Integer getPrice1() {
		return price1;
	}

	/**
	 * Returns the price of the item per stack of 10.
	 * @return Price of the stack.
	 */
	public Integer getPrice10() {
		return price10;
	}

	/**
	 * Returns the price of the item per stack of 100.
	 * @return Price of the stack.
	 */
	public Integer getPrice100() {
		return price100;
	}
}
