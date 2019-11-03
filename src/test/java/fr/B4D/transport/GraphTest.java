package fr.B4D.transport;

import java.awt.Point;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.B4D.dofus.Dofus;
;
public class GraphTest {

	@Before
	public void before() {
		@SuppressWarnings("unused")
		Dofus dofus = Dofus.getInstance();
	}
	
	@Test
	public void testBonta() {
		List<TransportStep> shortestPath = Dofus.getInstance().getWorld().getGraph().getPath(new Point(-47,16), new Point(-29,-52)).getEdgeList();
		System.out.println(new TransportPath(shortestPath));
	}
	
	@Test
	public void testBrakmar() {
		List<TransportStep> shortestPath = Dofus.getInstance().getWorld().getGraph().getPath(new Point(0,0), new Point(-20,33)).getEdgeList();
		System.out.println(new TransportPath(shortestPath));
	}
	
	@Test
	public void testFrigost() {
		List<TransportStep> shortestPath = Dofus.getInstance().getWorld().getGraph().getPath(new Point(14,-64), new Point(-62,-62)).getEdgeList();
		System.out.println(new TransportPath(shortestPath));
	}
}
