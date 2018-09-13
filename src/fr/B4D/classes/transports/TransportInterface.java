package fr.B4D.classes.transports;

import java.awt.Point;

import fr.B4D.classes.B4DException;

public interface TransportInterface {
	public void goTo(Point destination) throws B4DException;
}
