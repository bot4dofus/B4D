package fr.B4D.classes.transports;

import java.awt.AWTException;
import java.awt.Point;

import fr.B4D.exceptions.B4DCannotFind;

public interface TransportInterface {
	public void goTo(Point destination) throws AWTException, B4DCannotFind;
}
