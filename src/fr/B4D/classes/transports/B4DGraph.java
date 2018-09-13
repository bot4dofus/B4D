package fr.B4D.classes.transports;

import java.awt.Point;
import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import fr.B4D.enu.TransportType;

@SuppressWarnings("serial")
public class B4DGraph extends DirectedWeightedPseudograph<Point, B4DEdge>{

	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public B4DGraph() {
		super(B4DEdge.class);
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void addEdge(Point sourceVertex, Point targetVertex, double weight, TransportType TdT) {
	    B4DEdge edge = this.addEdge(sourceVertex, targetVertex);
	    edge.setTypeDeTransport(TdT);
	    this.setEdgeWeight(edge, weight);
	}
	
	public List<B4DEdge> getPath(Point source, Point target) {
		return new DijkstraShortestPath<Point, B4DEdge>(this).getPath(source, target).getEdgeList();
	}
}
