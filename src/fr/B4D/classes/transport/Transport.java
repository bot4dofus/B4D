package fr.B4D.classes.transport;

import java.awt.Point;

import fr.B4D.classes.PointF;

public abstract class Transport implements TransportInterface{
	
	private String name;
	private Point position;
	private PointF positionF;
	private double weight;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public Transport(String name, Point position, PointF positionF, double weigth) {
		this.name = name;
		this.position = position;
		this.positionF = positionF;
		this.setWeight(weigth);
	}
	
	  /***************/
	 /** GET & SET **/
	/***************/
	
	public String getName() {
		return name;
	}

	public void setName(String nom) {
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}	
}
