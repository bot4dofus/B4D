package fr.B4D.transport;

import java.awt.Point;
import java.io.Serializable;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import fr.B4D.transport.transports.Walk;

/** La classe {@code Graph} représente la carte du monde de dofus.<br/>
 * Un graph est représenté par des noeuds (vertex) et des arêtes (edges).
 * Les différents moyens de transport sont modélisés par les arêtes.
 */
public class Graph implements Serializable{

	private static final long serialVersionUID = -2532108486622081288L;
	
	private DirectedWeightedPseudograph<Point, TransportStep> graph;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructeur de la classe {@code Graph}. 
	 */
	public Graph() {
		graph = new DirectedWeightedPseudograph<Point, TransportStep>(TransportStep.class);
	}
	
	  /************/
	 /** VERTEX **/
	/************/
	
	/** Permet d'ajouter un noeud au graph.
	 * @param vertex - Coordonnée du noeud.
	 * @param autoConnect - {@code true} pour connecter automatiquement le noeud à ces voisins, {@code false} sinon.  
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
	
	/** Permet de retirer un noeud du graph ainsi que toutes les arêtes le reliant.
	 * Ne fait rien si le noeud n'existe pas.
	 * @param vertex - Coordonnées du noeud.
	 */
	public void removeVertex(Point vertex) {
		graph.removeVertex(vertex);
	}
	
	  /*******************/
	 /** METHODES EDGE **/
	/*******************/
	
	/** Permet d'ajouter une arête au graph.
	 * Les arêtes sont représentées par la classe {@code TransportStep}.
	 * @param transportStep - Arête reliant deux vertex.
	 */
	public void addEdge(TransportStep transportStep) {
		graph.addEdge(transportStep.getTransport().getPosition(), transportStep.getDestination(), transportStep);
		graph.setEdgeWeight(transportStep, transportStep.getTransport().getWeight());
	}
	
	/** Permet de retirer une arête du graph. Cette fonction n'est pas symetrique : A vers B sera supprimé mais pas B vers A.
	 * @param transportStep - Arête reliant deux vertex.
	 */
	public void removeEdge(TransportStep transportStep) {
		removeEdge(transportStep.getTransport().getPosition(), transportStep.getDestination(), false);
	}
	
	/** Permet de retirer une arête du graph.
	 * @param origin - Point de départ de l'arête.
	 * @param destination - Point d'arrivée de l'arête.
	 * @param symetric - {@code true} pour relier symétriquement (A vers B et B vers A), {@code false} sinon.  
	 */
	public void removeEdge(Point origin, Point destination, boolean symetric) {
		graph.removeEdge(origin, destination);
		if(symetric)
			graph.removeEdge(destination, origin);
	}
	
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	/** Permet de trouver le plus court chemin entre deux points de la map en utilisant tous les moyens de transport possible.
	 * @param source - Point de départ.
	 * @param target - Point d'arrivé.
	 * @return Chemin le plus court entre le point de départ et le point d'arrivé.
	 */
	public GraphPath<Point, TransportStep> getPath(Point source, Point target) {
		return new DijkstraShortestPath<Point, TransportStep>(graph).getPath(source, target);
	}
}
