package fr.B4D.transport;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;

import fr.B4D.dofus.B4DCannotFind;

public interface TransportInterface extends Serializable {
	final double zaapCost = 10;
	final double zaapiCost = 1;
	final double boosterPotionCost = 3;
	final double bontaPotionCost = 3;
	final double brakmarPotionCost = 3;
	final double almanaxPotionCost = 5;
	final double walkCost = 1;
	
	final Point bontaPotionDestination = new Point(-33, -56);
	final Point brakmarPotionDestination = new Point(-26,36);

	public void goTo(Point destination) throws AWTException, B4DCannotFind, B4DWrongPosition;
}
