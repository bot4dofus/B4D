package fr.B4D.utils;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.B4D;

/** La classe {@code Rectangle} représente une zone de l'écran.<br><br>
 * Un rectangle est défini par un point suppérieur gauche et un point inférieur droit.
 */
public class Rectangle extends java.awt.Rectangle implements Serializable{

	private static final long serialVersionUID = -4191850414544381904L;
	
	/** Constructeur de la classe {@code Rectangle}.
	 * @param topLeftHandCorner - Point suppérieur gauche du rectangle en coordonnées simples.
	 * @param bottomRigthHandCorner - Point inférieur droit du rectangle en coordonnées simples.
	 */
	public Rectangle(Point topLeftHandCorner, Point bottomRigthHandCorner) {
		super(topLeftHandCorner.x, topLeftHandCorner.y, bottomRigthHandCorner.x - topLeftHandCorner.x, bottomRigthHandCorner.y - topLeftHandCorner.y);
	}
	
	/** Constructeur de la classe {@code Rectangle}.
	 * @param topLeftHandCorner - Point suppérieur gauche du rectangle en coordonnées relatives.
	 * @param bottomRigthHandCorner - Point inférieur droit du rectangle en coordonnées relatives.
	 */
	public Rectangle(PointF topLeftHandCorner, PointF bottomRigthHandCorner) {
		this(B4D.converter.toPoint(topLeftHandCorner), B4D.converter.toPoint(bottomRigthHandCorner));
	}
}
