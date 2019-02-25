package fr.B4D.socket.os;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import fr.B4D.bot.B4DException;
import fr.B4D.socket.NetworkTester;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.PacketCapture;

/** La classe {@code Windows} représente un système d'exploitation Windows.<br><br>
 * Cette classe étend la classe {@code OperatingSystem}.
 */
public class Windows extends OperatingSystem{

	private final static int MILLIS_TO_WAIT = 3000;
	
	/** Constructeur de la classe {@code Windows}.
	 */
	public Windows() {
		super("Windows", "jpcap.dll");
	}
	
	/* (non-Javadoc)
	 * @see fr.B4D.socket.os.OperatingSystem#findActiveDevice()
	 */
	public String findActiveDevice() throws B4DException {
		try {
			String[] devs = PacketCapture.lookupDevices();
			ArrayList<NetworkTester> testers = new ArrayList<NetworkTester>();
			CountDownLatch socketDetected = new CountDownLatch(1);

			for(int i=0; i<devs.length; i++) {
				devs[i] = devs[i].substring(0, devs[i].indexOf("\n"));
				testers.add(new NetworkTester(devs[i], socketDetected));
			}
			testers.stream().forEach(s -> s.start());
			try {
				socketDetected.await(MILLIS_TO_WAIT, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			testers.stream().forEach(s -> s.interrupt());
			
			for(NetworkTester tester:testers) {
				if(tester.isActif())
					return tester.getNetwork();
			}
		} catch (CaptureDeviceLookupException e1) {
			//Do nothing
		}
		throw new B4DException("No active device detected. Please try again.");
	}
}
