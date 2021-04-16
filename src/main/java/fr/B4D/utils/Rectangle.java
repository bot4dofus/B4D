package fr.B4D.utils;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.B4D;

/**
 * The {@code Rectangle} class represents an area on the screen.
 * <br><br>
 * A rectangle is defined by a top-left-hand corner a bottom-righ-corner.
 * 
 * @author Lucas
 *
 */
public class Rectangle extends java.awt.Rectangle implements Serializable{

	private static final long serialVersionUID = -4191850414544381904L;
	
	/**
	 * Constructor of the {@code Rectangle} class.
	 * @param topLeftHandCorner - Top-left-hand corner in simple coordinates.
	 * @param bottomRigthHandCorner - Bottom-right-hand corner in simple coordinates.
	 */
	public Rectangle(Point topLeftHandCorner, Point bottomRigthHandCorner) {
		super(topLeftHandCorner.x, topLeftHandCorner.y, bottomRigthHandCorner.x - topLeftHandCorner.x, bottomRigthHandCorner.y - topLeftHandCorner.y);
	}

	/**
	 * Constructor of the {@code Rectangle} class.
	 * @param topLeftHandCorner - Top-left-hand corner in relative coordinates.
	 * @param bottomRigthHandCorner - Bottom-right-hand corner in relative coordinates.
	 */
	public Rectangle(PointF topLeftHandCorner, PointF bottomRigthHandCorner) {
		this(B4D.converter.toPoint(topLeftHandCorner), B4D.converter.toPoint(bottomRigthHandCorner));
	}
}
