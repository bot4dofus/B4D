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
		graph.addVertex(vertex);
		
		Point up = new Point(vertex.x,vertex.y - 1);
		Point down = new Point(vertex.x,vertex.y + 1);
		Point right = new Point(vertex.x + 1,vertex.y);
		Point left = new Point(vertex.x - 1,vertex.y);
		
		if(autoConnect) {
			if(!graph.containsEdge(vertex, up))
				this.addEdge(new TransportStep(new Walk(vertex), up));
			if(!graph.containsEdge(vertex, down))
				this.addEdge(new TransportStep(new Walk(vertex), down));
			if(!graph.containsEdge(vertex, right))
				this.addEdge(new TransportStep(new Walk(vertex), right));
			if(!graph.containsEdge(vertex, left))
				this.addEdge(new TransportStep(new Walk(vertex), left));
		}
	}
	
	public void removeVertex(Point vertex) {
		graph.removeVertex(vertex);
	}
	
	  /*******************/
	 /** METHODES EDGE **/
	/*******************/
	
	public void addEdge(TransportStep transportStep) {
		if(!graph.containsVertex(transportStep.getTransport().getPosition()))
			graph.addVertex(transportStep.getTransport().getPosition());
		
		if(!graph.containsVertex(transportStep.getDestination()))
			graph.addVertex(transportStep.getDestination());
			
	    graph.setEdgeWeight(transportStep, transportStep.getTransport().getWeight());
	}
	
	public void removeEdge(TransportStep transportStep) {
		graph.removeEdge(transportStep.getTransport().getPosition(), transportStep.getDestination());
	}
	  /**************/
	 /** METHODES **/
	/**************/
	
	public GraphPath<Point, TransportStep> getPath(Point source, Point target) {
		return new DijkstraShortestPath<Point, TransportStep>(graph).getPath(source, target);
	}
}
