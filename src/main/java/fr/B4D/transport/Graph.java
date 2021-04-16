package fr.B4D.transport;

import java.awt.Point;
import java.io.Serializable;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import fr.B4D.transport.transports.Walk;

/** 
 * The {@code Graph} class represents a directed weighted graph<br><br>
 * A graph is defined with vertexes and weighted edges.
 * 
 * @author Lucas
 *
 */
public class Graph implements Serializable{

	private static final long serialVersionUID = -2532108486622081288L;
	
	private DirectedWeightedPseudograph<Point, TransportStep> graph;
	
	/**
	 * Constructor of the {@code Graph} class.
	 */
	public Graph() {
		graph = new DirectedWeightedPseudograph<Point, TransportStep>(TransportStep.class);
	}
	
	/**
	 * Adds a vertex to the graph.
	 * @param vertex - Coordinate of the vertex.
	 * @param autoConnect - {@code true} to automatically connect the vertex with his neighbours, {@code false} otherwise.
	 * <br><br>
	 * Exemple : If the added vertex is (0,0) and {@code autoConnect} is set to true, and will be connected with (-1,0), (1,0), (0,-1) and (0,1) with a weight of 1 (walk).
	 */
	public void addVertex(Point vertex, Boolean autoConnect) {
		this.graph.addVertex(vertex);
		
		if(autoConnect) {
			Point up = new Point(vertex.x,vertex.y - 1);
			Point down = new Point(vertex.x,vertex.y + 1);
			Point right = new Point(vertex.x + 1,vertex.y);
			Point left = new Point(vertex.x - 1,vertex.y);
			
			if(graph.containsVertex(up)) {
				addEdge(new TransportStep(new Walk(vertex), up));
				addEdge(new TransportStep(new Walk(up), vertex));
			}
			if(graph.containsVertex(down)) {
				addEdge(new TransportStep(new Walk(vertex), down));
				addEdge(new TransportStep(new Walk(down), vertex));
			}
			if(graph.containsVertex(right)) {
				addEdge(new TransportStep(new Walk(vertex), right));
				addEdge(new TransportStep(new Walk(right), vertex));
			}
			if(graph.containsVertex(left)) {
				addEdge(new TransportStep(new Walk(vertex), left));
				addEdge(new TransportStep(new Walk(left), vertex));
			}
		}
	}
	
	/**
	 * Removes the specified vertex from this graph including all its touching edges if present. 
	 * Don't do anything if the node does not exists.
	 * @param vertex - Coordinates of the node to remove.
	 */
	public void removeVertex(Point vertex) {
		graph.removeVertex(vertex);
	}
	
	/**
	 * Adds an edge to the graph.
	 * An edge is represented by a {@code TransportStep}.
	 * @param transportStep - Edge linking two vertex.
	 */
	public void addEdge(TransportStep transportStep) {
		graph.addEdge(transportStep.getTransport().getPosition(), transportStep.getDestination(), transportStep);
		graph.setEdgeWeight(transportStep, transportStep.getTransport().getWeight());
	}
	
	/**
	 * Removes an edge from the graph.
	 * This method is not asymmetric. A to B will be removed but not B to A.
	 * @param transportStep - Edge to remove.
	 */
	public void removeEdge(TransportStep transportStep) {
		removeEdge(transportStep.getTransport().getPosition(), transportStep.getDestination(), false);
	}
	
	/**
	 * Removes an edge from the graph. 
	 * @param origin - Origin vertex.
	 * @param destination - Destination vertex.
	 * @param symetric - {@code true} to also remove the edge from {@code destination} to {@code origin}, {@code false} otherwise.  
	 */
	public void removeEdge(Point origin, Point destination, boolean symetric) {
		graph.removeEdge(origin, destination);
		if(symetric)
			graph.removeEdge(destination, origin);
	}
	
	/**
	 * Finds the fastest path between two vertexes using all the transport methods
	 * @param origin - Origin vertex.
	 * @param destination - Destination vertex.
	 * @return Fastest path between two vertexes.
	 */
	public GraphPath<Point, TransportStep> getPath(Point origin, Point destination) {
		return new DijkstraShortestPath<Point, TransportStep>(graph).getPath(origin, destination);
	}
}
