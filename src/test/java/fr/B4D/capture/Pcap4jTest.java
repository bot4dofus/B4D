package fr.B4D.capture;

import org.junit.Test;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.Packet;

import fr.B4D.socket.PcapsFinder;

// From https://github.com/kaitoy/pcap4j/blob/v1/pcap4j-sample/src/main/java/org/pcap4j/sample/Loop.java

@SuppressWarnings("javadoc")
public class Pcap4jTest {

	private static final int COUNT = -1;	// [number of packets to capture]
	private static final int READ_TIMEOUT = -1; // [ms]
	private static final int SNAPLEN = 65536; // [bytes]
	private static final String FILTER = "host 34.255.129.242 || 63.34.101.111";

	@Test
	public void receptionTest() throws Exception {

//		if (Platform.isWindows())
//			System.out.println("This is windows");

		PcapNetworkInterface nif = PcapsFinder.findActiveDevice();

		System.out.println("Device found : " + nif.getName());
		PcapHandle handle = nif.openLive(SNAPLEN, PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);

		if (FILTER != null) {
			handle.setFilter(FILTER, BpfCompileMode.OPTIMIZE);
		}

		PacketListener listener = new PacketListener() {
			@Override
			public void gotPacket(Packet packet) {
				System.out.println(packet.getPayload().getPayload().getPayload());
			}
		};
		handle.loop(COUNT, listener);
		handle.close();
	}
}