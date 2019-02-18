package fr.B4D.transport;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.utils.PointF;

/** La classe {@code Transport} représente un moyen de transport.<br/>
 * Un transport est défini par un nom, une position, une position relative et un poid.
 */
public abstract class Transport implements TransportInterface, Serializable{
	
	private static final long serialVersionUID = 6764531654498330943L;
	
	private String name;
	private Point position;
	private PointF positionF;
	private double weight;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	/** Constructeur de la classe {@code Transport}.
	 * @param name - Nom du transport.
	 * @param position - Position du transport sur la carte.
	 * @param positionF - Position relative du transport.
	 * @param weigth - Poid du transport.
	 */
	public Transport(String name, Point position, PointF positionF, double weigth) {
		this.name = name;
		this.position = position;
		this.positionF = positionF;
		this.setWeight(weigth);
	}
	
	  /***************/
	 /** GET & SET **/
	/***************/
	
	/** Retourne le nom du transport.
	 * @return Nom du transport.
	 */
	public String getName() {
		return name;
	}

	/** Modifi le nom du transport.
	 * @param nom - Nouveau nom du transport.
	 */
	public void setName(String nom) {
		this.name = nom;
	}

	/** Retourne la position du transport sur la carte.
	 * @return Position du transport sur la carte.
	 */
	public Point getPosition() {
		return position;
	}

	/** Modifi la position du transport sur la carte.
	 * @param position - Nouvelle position du transport.
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/** Retourne la position relative du transport.
	 * @return Position relative.
	 */
	public PointF getPositionF() {
		return positionF;
	}

	/** Modifi la position relative du transport.
	 * @param positionF - Nouvelle position relative du transport.
	 */
	public void setPositionF(PointF positionF) {
		this.positionF = positionF;
	}

	/** Retourne le poid du transport.
	 * @return Poid du transport.
	 */
	public double getWeight() {
		return weight;
	}

	/** Modifi le poid du transport.
	 * @param weight - Nouveau poid du transport.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}	
}
