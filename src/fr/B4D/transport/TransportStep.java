package fr.B4D.transport;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;

import fr.B4D.dofus.B4DCannotFind;

public class TransportStep implements Serializable{

	private static final long serialVersionUID = 5240292689676673762L;
	
	private Transport transport;
	private Point destination;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	public TransportStep(Transport transport, Point destination) {
		this.transport = transport;
		this.destination = destination;
	}

	  /*************/
	 /** GETTERS **/
	/*************/
	
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	public Point getDestination() {
		return destination;
	}
	public void setDestination(Point destination) {
		this.destination = destination;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void use() throws AWTException, B4DCannotFind, B4DWrongPosition {
		transport.goTo(destination);
	}

	  /**************/
	 /** TOSTRING **/
	/**************/
	
	public String toString() {
		return transport.getPosition() + " vers " + destination + " via " + transport.getName();
	}
}
