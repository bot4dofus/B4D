package fr.B4D.utils;

import java.io.Serializable;

/**
 * The {@code PointF} class represents a point in relative coordinates.
 * <br><br>
 *	&nbsp;_______<br>
 *	|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1<br>
 *	|<br>
 *	|&nbsp;&nbsp;1<br><br>
 * 
 * @author Lucas
 *
 */
public class PointF extends java.awt.geom.Point2D.Double implements Serializable{

	private static final long serialVersionUID = -8987300386100923141L;
	
	/**
	 * Constructor of the {@code PointF} class in (0, 0).
     */
	public PointF() {
		super();
	}

	/**
	 * Constructor of the {@code PointF} class in (x, y).
	 * @param x - X coordinate.
	 * @param y - Y coordinate.
	 */
	public PointF(double x, double y) {
		super(x,y);
	}
}
