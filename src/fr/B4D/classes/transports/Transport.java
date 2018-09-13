package fr.B4D.classes.transports;

import java.awt.Point;

import fr.B4D.classes.PointF;

public abstract class Transport implements TransportInterface{
	
	private String name;
	private Point position;
	private PointF positionF;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public Transport(String name, Point position, PointF positionF) {
		this.name = name;
		this.position = position;
		this.positionF = positionF;
	}
	
	  /***************/
	 /** GET & SET **/
	/***************/
	
	public String getNom() {
		return name;
	}

	public void setNom(String nom) {
		this.name = nom;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public PointF getPositionF() {
		return positionF;
	}

	public void setPositionF(PointF positionF) {
		this.positionF = positionF;
	}	
}
