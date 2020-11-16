package fr.B4D.dofus.world;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import fr.B4D.transport.Graph;
import fr.B4D.transport.Transport;
import fr.B4D.transport.TransportStep;
import fr.B4D.transport.transports.Zaap;
import fr.B4D.transport.transports.Zaapi;

/** La classe {@code World} représente le monde de dofus.<br><br>
 * Un monde est défini par un graph.
 */
public class World implements Serializable{

	private static final long serialVersionUID = 3069416510525087204L;
	
	private Graph graph;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/

	/** Constructeur de la classe {@code World}.
	 */
	protected World() {
		graph = new Graph();
	}
	
	  /*************/
	 /** GETTERS **/
	/*************/
	
	/** Retourne le graph du monde.
	 * @return Graph du monde.
	 */
	public Graph getGraph() {
		return graph;
	}
	
	  /*********************/
	 /** PATCH TRANSPORT **/
	/*********************/
	
	/** Permet d'ajouter au graph une liste de transport.
	 * @param transports - Liste de transports.
	 */
	protected void patchTransport(ArrayList<Transport> transports) {
		for(Transport t1:transports) {
			for(Transport t2:transports) {
				if(t1 != t2)
					graph.addEdge(new TransportStep(t1, t2.getPosition()));
			}
		}
	}	
}
