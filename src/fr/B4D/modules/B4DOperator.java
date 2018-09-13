package fr.B4D.modules;

import java.awt.Color;

public final class B4DOperator {
	
	  /*************/
	 /** COULEUR **/
	/*************/
	
	public static boolean isBetween(Color couleur, Color min, Color max) {
		return (min.getRed() <= couleur.getRed() && couleur.getRed() <= max.getRed() && min.getGreen() <= couleur.getGreen() && couleur.getGreen() <= max.getGreen() && min.getBlue() <= couleur.getBlue() && couleur.getBlue() <= max.getBlue());
	}
}
