package fr.B4D.socket;

import java.util.concurrent.CountDownLatch;

import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.CapturePacketException;
import net.sourceforge.jpcap.capture.InvalidFilterException;
import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;

public class NetworkTester extends Thread{
	private final String FILTER = "";

	private PacketCapture m_pcap;
	private String m_device, network;
	private CountDownLatch socketDetected;

	public NetworkTester(String m_device, CountDownLatch socketDetected) {
		m_pcap = new PacketCapture();
		this.m_device = m_device;
		this.socketDetected = socketDetected;
	}

	public String getNetwork() {
		return network;
	}

	public void run() {
		try {
			m_pcap.open(m_device, true);
			m_pcap.setFilter(FILTER, true);
			m_pcap.addRawPacketListener(new RawPacketHandler());
			m_pcap.capture(1);
		} catch (CaptureDeviceOpenException | InvalidFilterException | CapturePacketException e) {}
	}

	class RawPacketHandler implements RawPacketListener 
	{
		public void rawPacketArrived(RawPacket data) {
			network = m_device;
			socketDetected.countDown();
		}
	}
}
