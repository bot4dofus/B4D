package fr.B4D.socket;

import java.util.concurrent.CountDownLatch;

import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.CapturePacketException;
import net.sourceforge.jpcap.capture.InvalidFilterException;
import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;

/** La classe {@code NetworkTester} étend la classe  {@code Thread}.
 * Elle permet de tester si un réseau est actif.
 * Un testeur de réseau est identifié par un nom de réseau et un nombre de packet minimum.
 */
public class NetworkTester extends Thread{

	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private PacketCapture m_pcap;
	private CountDownLatch socketDetected;
	
	private boolean actif;
	private String network;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructeur de la classe {@code NetworkTester}. 
	 * @param m_device - Nom du réseau à tester.
	 * @param socketDetected - Nombre de trames à détecter pour considérer le réseau comme actif.
	 */
	public NetworkTester(String network, CountDownLatch socketDetected) {
		this.m_pcap = new PacketCapture();
		this.actif = false;
		this.network = network;
		this.socketDetected = socketDetected;
	}

	  /*************/
	 /** GETTERS **/
	/*************/
	
	/** Retourne le nom du réseau.
	 * @return Nom du réseau.
	 */
	public String getNetwork() {
		return network;
	}
	
	/** Spécifi si le réseau est actif.
	 * @return {@code true} si le réseau est actif, {@code false} sinon.
	 */
	public boolean isActif() {
		return actif;
	}

	  /*********/
	 /** RUN **/
	/*********/
	
	public void run() {
		try {
			m_pcap.open(network, true);
			m_pcap.setFilter("", true);
			m_pcap.addRawPacketListener(new RawPacketHandler());
			m_pcap.capture(1);
		} catch (CaptureDeviceOpenException | InvalidFilterException | CapturePacketException e) {}
	}

	class RawPacketHandler implements RawPacketListener 
	{
		public void rawPacketArrived(RawPacket data) {
			actif = true;
			socketDetected.countDown();
		}
	}
}
