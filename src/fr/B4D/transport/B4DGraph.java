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
		this.graph = new DirectedWeightedPseudograph<Point, TransportStep>(TransportStep.class);
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
				this.addEdge(new TransportStep(new Walk(vertex), up));
				this.addEdge(new TransportStep(new Walk(up), vertex));
			}
			if(graph.containsVertex(down)) {
				this.addEdge(new TransportStep(new Walk(vertex), down));
				this.addEdge(new TransportStep(new Walk(down), vertex));
			}
			if(graph.containsVertex(right)) {
				this.addEdge(new TransportStep(new Walk(vertex), right));
				this.addEdge(new TransportStep(new Walk(right), vertex));
			}
			if(graph.containsVertex(left)) {
				this.addEdge(new TransportStep(new Walk(vertex), left));
				this.addEdge(new TransportStep(new Walk(left), vertex));
			}
		}
	}
	
	public void removeVertex(Point vertex) {
		this.graph.removeVertex(vertex);
	}
	
	  /*******************/
	 /** METHODES EDGE **/
	/*******************/
	
	public void addEdge(TransportStep transportStep) {
		this.graph.addEdge(transportStep.getTransport().getPosition(), transportStep.getDestination(), transportStep);
		this.graph.setEdgeWeight(transportStep, transportStep.getTransport().getWeight());
	}
	
	public void removeEdge(TransportStep transportStep) {
		this.graph.removeEdge(transportStep.getTransport().getPosition(), transportStep.getDestination());
	}
	  /**************/
	 /** METHODES **/
	/**************/
	
	public GraphPath<Point, TransportStep> getPath(Point source, Point target) {
		return new DijkstraShortestPath<Point, TransportStep>(this.graph).getPath(source, target);
	}
}
