package fr.B4D.interaction.chat;

import net.sourceforge.jpcap.util.HexHelper;

public class UnknowSocketTypeException extends Exception {

	private static final long serialVersionUID = 924080672225339433L;
	
	public UnknowSocketTypeException(byte[] data) {
		super("Unknow socket type [" + HexHelper.toString(data) + "]");
	}
}
