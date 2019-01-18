package fr.B4D.socket;

public class NoSocketDetectedException extends Exception {

	private static final long serialVersionUID = -5977537879743745288L;

	public NoSocketDetectedException() {
		super("Couldn't find any packet on any device. Please try again.");
	}
}
