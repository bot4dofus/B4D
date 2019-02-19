package fr.B4D.socket;

import fr.B4D.bot.B4DException;

public class NoSocketDetectedException extends B4DException {

	private static final long serialVersionUID = -5977537879743745288L;

	public NoSocketDetectedException() {
		super("Couldn't find any packet on any device. Please try again.");
	}
}
