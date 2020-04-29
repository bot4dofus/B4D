package fr.B4D.socket.result;

public class HDVItemViewSocketResult extends SocketResult{
	
	private Integer price1, price10, price100;
	
	public HDVItemViewSocketResult(Integer price1, Integer price10, Integer price100) {
		this.price1 = price1;
		this.price10 = price10;
		this.price100 = price100;
	}
	
	public Integer getPrice1() {
		return price1;
	}

	public Integer getPrice10() {
		return price10;
	}

	public Integer getPrice100() {
		return price100;
	}
}
