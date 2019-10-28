package fr.B4D.transport;

import java.awt.AWTException;
import java.awt.Point;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.B4D.dofus.Dofus;
import fr.B4D.transport.TransportPath;
import fr.B4D.transport.TransportStep;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.InvalidFilterException;
;
public class GraphTest {

	@Before
	public void before() {
		@SuppressWarnings("unused")
		Dofus dofus = Dofus.getInstance();
	}
	
	@Test
	public void testBonta() throws AWTException, ClassNotFoundException, IOException, CaptureDeviceLookupException, CaptureDeviceOpenException, InvalidFilterException {
		List<TransportStep> shortestPath = Dofus.getInstance().getWorld().getGraph().getPath(new Point(-47,16), new Point(-29,-52)).getEdgeList();
		System.out.println(new TransportPath(shortestPath));
	}
	
	@Test
	public void testBrakmar() throws AWTException, ClassNotFoundException, IOException, CaptureDeviceLookupException, CaptureDeviceOpenException, InvalidFilterException {
		List<TransportStep> shortestPath = Dofus.getInstance().getWorld().getGraph().getPath(new Point(0,0), new Point(-20,33)).getEdgeList();
		System.out.println(new TransportPath(shortestPath));
	}
	
	@Test
	public void testFrigost() throws AWTException, ClassNotFoundException, IOException, CaptureDeviceLookupException, CaptureDeviceOpenException, InvalidFilterException {
		List<TransportStep> shortestPath = Dofus.getInstance().getWorld().getGraph().getPath(new Point(14,-64), new Point(-62,-62)).getEdgeList();
		System.out.println(new TransportPath(shortestPath));
	}
}
