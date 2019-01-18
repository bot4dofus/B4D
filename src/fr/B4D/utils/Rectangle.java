package fr.B4D.utils;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.modules.B4DConversion;

public class Rectangle extends java.awt.Rectangle implements Serializable{

	private static final long serialVersionUID = -4191850414544381904L;

	public Rectangle() {
		super();
	}
	public Rectangle(Point topLeft, Point bottomRigth) {
		super(topLeft.x, topLeft.y, bottomRigth.x - topLeft.x, bottomRigth.y - topLeft.y);
	}
	public Rectangle(PointF topLeft, PointF bottomRigth) {
		this(B4DConversion.pointFToPoint(topLeft), B4DConversion.pointFToPoint(bottomRigth));
	}
}
