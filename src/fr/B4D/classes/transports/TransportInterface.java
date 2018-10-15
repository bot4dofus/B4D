package fr.B4D.classes.transports;

import java.awt.AWTException;
import java.awt.Point;

import fr.B4D.exceptions.B4DCannotFind;

public interface TransportInterface {
	final double zaapCost = 10;
	final double zaapiBontaCost = 1;
	final double zaapiBrakmarCost = 1;
	final double boosterPotionCost = 3;
	final double bontaPotionCost = 3;
	final double brakmarPotionCost = 3;
	final double almanaxPotionCost = 5;

	public void goTo(Point destination) throws AWTException, B4DCannotFind;
}
