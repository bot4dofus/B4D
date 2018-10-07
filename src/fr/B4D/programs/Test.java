package fr.B4D.programs;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import fr.B4D.classes.Bot;
import fr.B4D.classes.ProgramInterface;
import fr.B4D.classes.transports.B4DEdge;
import fr.B4D.classes.transports.B4DGraph;
import fr.B4D.enu.TransportType;

public final class Test {
	public static ProgramInterface test = new ProgramInterface() {
		public void run() {
			B4DGraph graph = new B4DGraph();
			graph.addVertex(new Point(1,1));
			graph.addVertex(new Point(2,2));
			graph.addVertex(new Point(3,3));
	
		    graph.addB4DEdge(new Point(1,1), new Point(3,3), 6, TransportType.Marche);
		    graph.addB4DEdge(new Point(1,1), new Point(2,2), 3, TransportType.Zaap);
		    graph.addB4DEdge(new Point(2,2), new Point(3,3), 2, TransportType.Zaap);
		    
		    List<B4DEdge> shortestPath = graph.getPath(new Point(1,1), new Point(3,3));
		    System.out.println(shortestPath);
		    
		    try {
				System.out.println(Bot.world.getPosition().toString());
			} catch (AWTException | UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
		}
	};
}
