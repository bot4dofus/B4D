package fr.B4D.transport;

import java.awt.Point;
import java.io.Serializable;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import fr.B4D.transport.transports.Walk;

public class B4DGraph implements Serializable{

	private static final long serialVersionUID = -2532108486622081288L;
	
	private DirectedWeightedPseudograph<Point, TransportStep> graph;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public B4DGraph() {
		graph = new DirectedWeightedPseudograph<Point, TransportStep>(TransportStep.class);
	}
	
	  /*********************/
	 /** METHODES VERTEX **/
	/*********************/
	
	public void addVertex(Point vertex, Boolean autoConnect) {
		this.graph.addVertex(vertex);
		
		Point up = new Point(vertex.x,vertex.y - 1);
		Point down = new Point(vertex.x,vertex.y + 1);
		Point right = new Point(vertex.x + 1,vertex.y);
		Point left = new Point(vertex.x - 1,vertex.y);
		
		if(autoConnect) {
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
	
	public void removeVertex(Point vertex) {
		graph.removeVertex(vertex);
	}
	
	  /*******************/
	 /** METHODES EDGE **/
	/*******************/
	
	public void addEdge(TransportStep transportStep) {
		graph.addEdge(transportStep.getTransport().getPosition(), transportStep.getDestination(), transportStep);
		graph.setEdgeWeight(transportStep, transportStep.getTransport().getWeight());
	}
	
	public void removeEdge(TransportStep transportStep) {
		removeEdge(transportStep.getTransport().getPosition(), transportStep.getDestination(), false);
	}
	public void removeEdge(Point origin, Point destination, boolean symetric) {
		graph.removeEdge(origin, destination);
		if(symetric)
			graph.removeEdge(destination, origin);
	}
	
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public GraphPath<Point, TransportStep> getPath(Point source, Point target) {
		return new DijkstraShortestPath<Point, TransportStep>(graph).getPath(source, target);
	}
}
