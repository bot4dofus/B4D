package fr.B4D.transport;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.utils.PointF;

/**
 * The {@code Transport} class represents a transport method.<br><br>
 * A transport is defined by a name, a starting location, a relative location and a weight.
 * 
 * @author Lucas
 *
 */
public abstract class Transport implements TransportInterface, Serializable{
	
	private static final long serialVersionUID = 6764531654498330943L;
	
	/**
	 * Name of the transport.
	 */
	private String name;
	
	/**
	 * Location of the transport on the map.
	 */
	private Point position;
	
	/**
	 * Location of the transport on the screen in relative coordinates.
	 */
	private PointF positionF;
	
	/**
	 * Weight of the transport.
	 */
	private double weight;
	
	/**
	 * Constructor of the {@code Transport} class.
	 * @param name - Name of the transport.
	 * @param position - Location of the transport on the map.
	 * @param positionF - Location of the transport on the screen in relative coordinates.
	 * @param weigth - Weight of the transport.
	 */
	public Transport(String name, Point position, PointF positionF, double weigth) {
		this.name = name;
		this.position = position;
		this.positionF = positionF;
		this.setWeight(weigth);
	}
	
	/**
	 * Returns the name of the transport.
	 * @return Name of the transport.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Defines the name of the transport.
	 * @param name - Name of the transport.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the location of the transport on the map.
	 * @return Location of the transport on the map.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Defines the location of the transport on the map.
	 * @param position - Location of the transport on the map.
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Returns the location of the transport on the screen in relative coordinates.
	 * @return Location of the transport on the screen in relative coordinates.
	 */
	public PointF getPositionF() {
		return positionF;
	}

	/**
	 * Defines the location of the transport on the screen in relative coordinates.
	 * @param positionF - Location of the transport on the screen in relative coordinates.
	 */
	public void setPositionF(PointF positionF) {
		this.positionF = positionF;
	}

	/**
	 * Returns the weight of the transport.
	 * @return Weight of the transport
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Defines the weight of the transport.
	 * @param weight - Weight of the transport.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
