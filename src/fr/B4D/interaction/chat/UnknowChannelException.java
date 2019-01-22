package fr.B4D.interaction.chat;

public class UnknowChannelException extends Exception {

	private static final long serialVersionUID = 924080672225339433L;
	
	public UnknowChannelException(Integer key) {
		super("Unknow channel " + key);
	}
}
