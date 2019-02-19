package fr.B4D.utils;

import java.awt.Point;
import java.io.Serializable;

/** La classe {@code PointD} représente un point en coordonnée du damier de dofus.<br><br>
 * 
 *	&nbsp;&nbsp;/&nbsp;X<br>
 *	/<br>
 *	\<br>
 *  &nbsp;&nbsp;\&nbsp;Y<br><br>
 *
 */
public class PointD extends Point implements Serializable{

	private static final long serialVersionUID = -6659200190789389246L;
	
	/** Constructeur de la classe {@code PointD} en (0, 0).
     */
	public PointD() {
		super();
	}
	
	/** Constructeur de la classe {@code PointD} en (x, y)
	 * @param x - Coordonnée x du point.
	 * @param y - Coordonnée y du point.
	 */
	public PointD(int x, int y) {
		super(x,y);
	}
}
