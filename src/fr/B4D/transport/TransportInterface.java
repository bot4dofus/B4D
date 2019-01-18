package fr.B4D.transport;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;

import fr.B4D.exceptions.B4DCannotFind;
import fr.B4D.exceptions.B4DWrongPosition;

public interface TransportInterface extends Serializable {
	final double zaapCost = 10;
	final double zaapiBontaCost = 1;
	final double zaapiBrakmarCost = 1;
	final double boosterPotionCost = 3;
	final double bontaPotionCost = 3;
	final double brakmarPotionCost = 3;
	final double almanaxPotionCost = 5;
	final double walkCost = 1;

	public void goTo(Point destination) throws AWTException, B4DCannotFind, B4DWrongPosition;
}
