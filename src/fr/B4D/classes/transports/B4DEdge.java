package fr.B4D.classes.transports;

import java.awt.Point;

import org.jgrapht.graph.DefaultWeightedEdge;
import fr.B4D.enu.TransportType;

@SuppressWarnings("serial")
public class B4DEdge extends DefaultWeightedEdge {
	
	private TransportType transportType;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public B4DEdge() {
		super();
	}

	  /***************/
	 /** GET & SET **/
	/***************/
	
	public Point getSource() {
		return (Point)super.getSource();
	}
	public Point getTarget() {
		return (Point)super.getTarget();
	}
	public double getWeight() {
		return super.getWeight();
	}
	public TransportType getTypeDeTransport() {
		return transportType;
	}
	public void setTypeDeTransport(TransportType transportType) {
		this.transportType = transportType;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public String toString() {
		return "(" + this.getSource() + ":" + transportType + ":" + this.getTarget() + ")";
	}
}
