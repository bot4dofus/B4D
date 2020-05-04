package fr.B4D.socket;

import fr.B4D.bot.B4DException;
import fr.B4D.utils.HexHelper;

public class PatternNotFoundException extends B4DException{

	public PatternNotFoundException() {
		super("Could find the pattern.");
	}

	public PatternNotFoundException(byte[] socket, byte[] pattern) {
		super("Could find the pattern [" + HexHelper.toString(pattern) + "] in  [" + HexHelper.toString(socket) + "]");
	}
}
