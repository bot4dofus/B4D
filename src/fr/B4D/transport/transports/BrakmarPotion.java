package fr.B4D.transport.transports;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.utils.PointF;

public class BrakmarPotion extends Potion implements Serializable{
	
	private static final long serialVersionUID = -3297904314801593247L;

	/******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public BrakmarPotion(Point position, PointF positionF) {
		super("Brakmar potion", position, positionF, brakmarPotionCost);
	}
}
