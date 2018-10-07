package fr.B4D.classes.transports;

import java.awt.Point;
import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import fr.B4D.enu.TransportType;

public class B4DGraph extends DirectedWeightedPseudograph<Point, B4DEdge>{

	private static final long serialVersionUID = 843741136461285887L;

	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public B4DGraph() {
		super(B4DEdge.class);
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void addB4DEdge(Point sourceVertex, Point targetVertex, double weight, TransportType TdT) {
	    B4DEdge edge = super.addEdge(sourceVertex, targetVertex);
	    edge.setTypeDeTransport(TdT);
	    super.setEdgeWeight(edge, weight);
	}
	
	public void removeB4DEdge(Point sourceVertex, Point targetVertex) {
		super.removeEdge(sourceVertex, targetVertex);
	}
	
	public List<B4DEdge> getPath(Point source, Point target) {
		return new DijkstraShortestPath<Point, B4DEdge>(this).getPath(source, target).getEdgeList();
	}
}
