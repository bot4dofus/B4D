package test.libs;

import java.awt.AWTException;
import java.awt.Point;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.B4D.bot.Person;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.dofus.Dofus;
import fr.B4D.socket.NoSocketDetectedException;
import fr.B4D.transport.B4DWrongPosition;
import fr.B4D.transport.TransportPath;
import fr.B4D.transport.TransportStep;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.InvalidFilterException;
;
public class JGraphT {

	@Before
	public void before() {
		Dofus dofus = new Dofus();
	}
	
	@Test
	public void testBonta() throws B4DCannotFind, B4DWrongPosition, AWTException, ClassNotFoundException, IOException, CaptureDeviceLookupException, NoSocketDetectedException, CaptureDeviceOpenException, InvalidFilterException {
		List<TransportStep> shortestPath = Dofus.world.getGraph().getPath(new Point(-47,16), new Point(-29,-52)).getEdgeList();
		System.out.println(new TransportPath(shortestPath));
	}
	
	@Test
	public void testBrakmar() throws B4DCannotFind, B4DWrongPosition, AWTException, ClassNotFoundException, IOException, CaptureDeviceLookupException, NoSocketDetectedException, CaptureDeviceOpenException, InvalidFilterException {
		List<TransportStep> shortestPath = Dofus.world.getGraph().getPath(new Point(0,0), new Point(-20,33)).getEdgeList();
		System.out.println(new TransportPath(shortestPath));
	}
	
	@Test
	public void testFrigost() throws B4DCannotFind, B4DWrongPosition, AWTException, ClassNotFoundException, IOException, CaptureDeviceLookupException, NoSocketDetectedException, CaptureDeviceOpenException, InvalidFilterException {
		List<TransportStep> shortestPath = Dofus.world.getGraph().getPath(new Point(14,-64), new Point(-62,-62)).getEdgeList();
		System.out.println(new TransportPath(shortestPath));
	}
}
