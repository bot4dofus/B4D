package fr.B4D.classes;

import java.awt.geom.Point2D.Double;
import java.io.Serializable;

public class PointF extends Double implements Serializable{

	private static final long serialVersionUID = -8987300386100923141L;
	
	public PointF() {
		super();
	}
	public PointF(double x, double y) {
		super(x,y);
	}
}
