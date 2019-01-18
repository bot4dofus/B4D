package fr.B4D.socket;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.PacketCapture;

public class NetworkFinder{

	private final static int millisToWait = 10000;

	public static String find() throws CaptureDeviceLookupException, NoSocketDetectedException {
		String[] devs = PacketCapture.lookupDevices();
		ArrayList<NetworkTester> testers = new ArrayList<NetworkTester>();
		CountDownLatch socketDetected = new CountDownLatch(1);

		for(int i=0; i<devs.length; i++) {
			devs[i] = devs[i].substring(0, devs[i].indexOf("\n"));
			testers.add(new NetworkTester(devs[i], socketDetected));
		}
		testers.stream().forEach(s -> s.start());
		try {
			socketDetected.await(millisToWait, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		testers.stream().forEach(s -> s.interrupt());

		return testers.stream().map(s -> s.getNetwork()).filter(n -> n != null).findFirst().orElseThrow(NoSocketDetectedException::new);
	}

	public static void main(String[] args) {
		try {
			System.out.println(NetworkFinder.find());
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
