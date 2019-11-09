package fr.B4D.transport;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/** La classe {@code TransportStep} représente une étape dans un chemin entre deux point de la carte.<br><br>
 * Une étape est défini par un transport et une destination.
 */
public class TransportStep implements Serializable{

	private static final long serialVersionUID = 5240292689676673762L;
	
	private Transport transport;
	private Point destination;

	  /*************/
	 /** BUILDER **/
	/*************/

	/** Constructeur de la classe {@code TransportStep}. 
	 * @param transport - Transport utilisé pour l'étape.
	 * @param destination - Destination du transport.
	 */
	public TransportStep(Transport transport, Point destination) {
		this.transport = transport;
		this.destination = destination;
	}

	  /*************/
	 /** GETTERS **/
	/*************/
	
	/** Retourne le transport de l'étape.
	 * @return Transport de l'étape.
	 */
	public Transport getTransport() {
		return transport;
	}
	
	/** Modifi le transport de l'étape.
	 * @param transport - Nouveau transport de l'étape.
	 */
	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	/** Retourne la destination de l'étape.
	 * @return Destination de l'étape.
	 */
	public Point getDestination() {
		return destination;
	}
	
	/** Modifi le destination de l'étape.
	 * @param destination - Nouvelle destination de l'étape.
	 */
	public void setDestination(Point destination) {
		this.destination = destination;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	/** Permet d'utiliser le transport de l'étape.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 */
	public void use() throws StopProgramException, CancelProgramException, B4DException {
		transport.goTo(destination);
	}

	  /**************/
	 /** TOSTRING **/
	/**************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return transport.getPosition() + " vers " + destination + " via " + transport.getName();
	}
}
