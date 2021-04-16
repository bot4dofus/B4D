package fr.B4D.transport;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/**
 * The {@code TransportStep} class represents a step in a {@link TransportPath} between two locations.
 * <br><br>
 * A step is defines by a transport and a destination location.
 * 
 * @author Lucas
 *
 */
public class TransportStep implements Serializable{

	private static final long serialVersionUID = 5240292689676673762L;
	
	/**
	 * Trasnport of the step.
	 */
	private Transport transport;
	
	/**
	 * Destination of the step.
	 */
	private Point destination;
	
	/**
	 * Constructor of the {@code TransportStep} class . 
	 * @param transport - Transport used for the step.
	 * @param destination - Destination location.
	 */
	public TransportStep(Transport transport, Point destination) {
		this.transport = transport;
		this.destination = destination;
	}
	
	/**
	 * Returns the transport of the step.
	 * @return Transport of the step.
	 */
	public Transport getTransport() {
		return transport;
	}
	
	/**
	 * Defines the transport of the step.
	 * @param transport - Transport of the step.
	 */
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	/**
	 * Returns the destination of the step.
	 * @return Destination of the step.
	 */
	public Point getDestination() {
		return destination;
	}
	
	/**
	 * Defines the destination of the step.
	 * @param destination - Destination of the step.
	 */
	public void setDestination(Point destination) {
		this.destination = destination;
	}
	
	/**
	 * Uses the transport of the step.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unknown exception occur.
	 */
	public void use() throws StopProgramException, CancelProgramException, B4DException {
		transport.goTo(destination);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return transport.getPosition() + " vers " + destination + " via " + transport.getName();
	}
}
