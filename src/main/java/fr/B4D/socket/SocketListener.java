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
import fr.B4D.bot.Server;
import fr.B4D.socket.parser.SocketParser;

/** La classe {@code SocketListener} permet d'écouter de sniffer et traiter les trames dofus.<br><br>
 * Cette classe étend la classe {@code Thread}.
 */
public class SocketListener extends Thread{
	
	  /***************/
	 /** CONSTANTS **/
	/***************/
	
	private static final int INFINITE = -1;

	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private PcapHandle handle;
	private PacketListener packetListener;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructeur de la classe {@code SocketListener}.
	 * @throws B4DException Si utilisation d'une jvm 64bit, si aucune librairie jpcap trouvée ou si aucun des réseaux n'est actif.
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
					Packet payload = packet.getPayload().getPayload().getPayload();
					if(payload != null) {
						parseDofus(payload.getRawData());
					}
				}
			};
		} catch (PcapNativeException e) {
			e.printStackTrace();
		}
		
	}
	
	  /*********/
	 /** RUN **/
	/*********/
	
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
		try {
			B4D.logger.debug("Lancement du thread");
			handle.loop(INFINITE, packetListener);
			B4D.logger.debug("Fin du thread");
		} catch (PcapNativeException  | NotOpenException e) {
			B4D.logger.error(e);
		} catch (InterruptedException e) {
			//Do nothing
		}
	}

	  /*************/
	 /** METHODS **/
	/*************/
	
	/** Modifi le filtre du sniffeur pour traiter uniquement les trâmes vennant du serveur.
	 * @param serveur - Serveur du joueur.
	 * @throws B4DException Si le filtre n'est pas valide.
	 */
	public void setFilter(Server serveur) throws B4DException {
		try {
			handle.setFilter("host " + serveur.getIp(), BpfCompileMode.OPTIMIZE);
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
		}
		catch(B4DException e) {
			B4D.logger.error(e);
		}catch(IllegalArgumentException e) {
			//Nothing to do.
		}
	}
}
