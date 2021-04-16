package fr.B4D.dofus.world;

import java.util.ArrayList;

import fr.B4D.transport.Graph;
import fr.B4D.transport.Transport;
import fr.B4D.transport.TransportStep;

/**
 * The {@code World} class represents a world in the game.<br><br>
 * A world is represented by a graph.
 * 
 * @author Lucas
 *
 */
public class World {
	
	private Graph graph;

	/**
	 * Constructor of the {@code World} class.
	 */
	protected World() {
		graph = new Graph();
	}
	
	/**
	 * Returns the graph of the world.
	 * @return Graph of the world.
	 */
	public Graph getGraph() {
		return graph;
	}
	
	/**
	 * Adds a list of transports to the graph.
	 * @param transports - List of transports.
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
