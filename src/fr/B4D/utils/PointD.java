package fr.B4D.utils;

import java.awt.Point;
import java.io.Serializable;

public class PointD extends Point implements Serializable{

	private static final long serialVersionUID = -6659200190789389246L;
	
	public PointD() {
		super();
	}
	public PointD(int x, int y) {
		super(x,y);
	}
}
