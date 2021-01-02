package fr.B4D.socket;

import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.Packet;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.socket.parser.SocketParser;

/** La classe {@code SocketListener} permet d'écouter de sniffer et traiter les trames dofus.<br><br>
 * Cette classe étend la classe {@code Thread}.
 */
public class SocketListener extends Thread{
	
	private static final int INFINITE = -1;
	
	private PcapHandle handle;
	private PacketListener packetListener;
	
	/** Constructeur de la classe {@code SocketListener}.
	 * @throws B4DException Si il est impossible d'ouvrir le réseau.
	 */
	public SocketListener() throws B4DException{
		
		try {
			PcapNetworkInterface nif = PcapsFinder.findActiveDevice();
			B4D.logger.debug("Device found : " + nif.getName());
			handle = nif.openLive(65536, PromiscuousMode.PROMISCUOUS, -1);
			
			packetListener = new PacketListener() {
				@Override
				public void gotPacket(Packet packet) {
					Packet ethernetPacket = packet.getPayload();
					if(ethernetPacket != null) {
						Packet tcpPacket = ethernetPacket.getPayload();
						if(tcpPacket != null) {
							Packet dofusPacket = tcpPacket.getPayload();
							if(dofusPacket != null) {
								parseDofus(dofusPacket.getRawData());
							}
						}
					}
				}
			};
		} catch (PcapNativeException e) {
			e.printStackTrace();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#interrupt()
	 */
	public void interrupt() {
		try {
			handle.breakLoop();
		} catch (NotOpenException e) {
			B4D.logger.error(e);
		}
		handle.close();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		B4D.logger.debug("Lancement du thread");
		try {
			handle.loop(INFINITE, packetListener);
		} catch (PcapNativeException  | NotOpenException e) {
			B4D.logger.error(e);
		} catch (InterruptedException e) {
			//Do nothing
		}
		B4D.logger.debug("Fin du thread");
	}
	
	/** 
	 * Defines the socket listener filter to only process sockets coming from the following server ip.
	 * @param ip - Ip of the server.
	 * @throws B4DException if the filter is not valid.
	 */
	public void setFilter(String ip) throws B4DException {
		try {
			handle.setFilter("host " + ip, BpfCompileMode.OPTIMIZE);
		} catch (PcapNativeException | NotOpenException e) {
			throw new B4DException(e);
		}
	}
	
	  /*************/
	 /** PARSING **/
	/*************/
	
	/** Parse les trâmes entrantes.
	 * @param data - Trâmes entrante.
	 */
	private void parseDofus(byte[] data) {	
		
		try {
			DofusSocket dofusSocket = new DofusSocket(data);
			SocketParser<?> socketParser = dofusSocket.getParser();
			if(socketParser != null) {
				socketParser.parse(dofusSocket);
			}
		}catch(B4DException | IllegalArgumentException e) {
			//Nothing to do.
		}
	}
}
