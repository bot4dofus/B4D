package fr.B4D.dofus.world;

import java.util.ArrayList;

import fr.B4D.transport.Graph;
import fr.B4D.transport.Transport;
import fr.B4D.transport.TransportStep;

/** La classe {@code World} représente le monde de dofus.<br><br>
 * Un monde est défini par un graph.
 */
public class World {
	
	private Graph graph;

	/** Constructeur de la classe {@code World}.
	 */
	protected World() {
		graph = new Graph();
	}
	
	/** Retourne le graph du monde.
	 * @return Graph du monde.
	 */
	public Graph getGraph() {
		return graph;
	}
	
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
