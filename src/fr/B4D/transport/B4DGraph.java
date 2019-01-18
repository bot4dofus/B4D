package fr.B4D.transport;

import java.awt.Point;
import java.io.Serializable;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class B4DGraph implements Serializable{

	private static final long serialVersionUID = -2532108486622081288L;
	
	private DirectedWeightedPseudograph<Point, B4DEdge> graph;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public B4DGraph() {
		this.graph = new DirectedWeightedPseudograph<Point, B4DEdge>(B4DEdge.class);
	}
	
	  /*********************/
	 /** METHODES VERTEX **/
	/*********************/
	
	public void addB4DVertex(Point vertex, Boolean autoConnect) {
		graph.addVertex(vertex);
		
		Point up = new Point(vertex.x,vertex.y - 1);
		Point down = new Point(vertex.x,vertex.y + 1);
		Point right = new Point(vertex.x + 1,vertex.y);
		Point left = new Point(vertex.x - 1,vertex.y);
		
		if(autoConnect) {
			if(!graph.containsEdge(vertex, up))
				this.addB4DEdge(vertex, up, TransportType.Walk, 1);
			if(!graph.containsEdge(vertex, down))
				this.addB4DEdge(vertex, down, TransportType.Walk, 1);
			if(!graph.containsEdge(vertex, right))
				this.addB4DEdge(vertex, right, TransportType.Walk, 1);
			if(!graph.containsEdge(vertex, left))
				this.addB4DEdge(vertex, left, TransportType.Walk, 1);
		}
	}
	
	public void removeB4DVertex(Point vertex) {
		graph.removeVertex(vertex);
	}
	
	  /*******************/
	 /** METHODES EDGE **/
	/*******************/
	
	public void addB4DEdge(Point sourceVertex, Point targetVertex, TransportType transportType, double weight) {
		if(!graph.containsVertex(sourceVertex))
			graph.addVertex(sourceVertex);
		
		if(!graph.containsVertex(targetVertex))
			graph.addVertex(targetVertex);
			
	    B4DEdge edge = graph.addEdge(sourceVertex, targetVertex);
	    edge.setTypeDeTransport(transportType);
	    graph.setEdgeWeight(edge, weight);
	}
	
	public void removeB4DEdge(Point sourceVertex, Point targetVertex) {
		graph.removeEdge(sourceVertex, targetVertex);
	}
	  /**************/
	 /** METHODES **/
	/**************/
	
	public GraphPath<Point, B4DEdge> getPath(Point source, Point target) {
		return new DijkstraShortestPath<Point, B4DEdge>(graph).getPath(source, target);
	}
}
