package fr.B4D.utils;

import java.awt.Point;
import java.io.Serializable;

/**
 * The {@code PointD} class represents a point in draughtboard coordinates.
 * <br><br>
 *	&nbsp;&nbsp;/&nbsp;X<br>
 *	/<br>
 *	\<br>
 *  &nbsp;&nbsp;\&nbsp;Y<br><br>
 *
 * @author Lucas
 *
 */
public class PointD extends Point implements Serializable{

	private static final long serialVersionUID = -6659200190789389246L;
	
	/**
	 * Constructor of the {@code PointD} class in (0, 0).
     */
	public PointD() {
		super();
	}
	
	/**
	 * Constructor of the {@code PointD} class in (x, y).
	 * @param x - X coordinate.
	 * @param y - Y coordinate.
	 */
	public PointD(int x, int y) {
		super(x,y);
	}
}
