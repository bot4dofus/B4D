package fr.B4D.socket;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

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
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.InvalidFilterException;
import net.sourceforge.jpcap.util.HexHelper;

/** La classe {@code SocketListener} permet d'écouter de sniffer et traiter les trames dofus.<br><br>
 * Cette classe étend la classe {@code Thread}.
 */
public class SocketListener extends Thread{
	
	  /***************/
	 /** CONSTANTS **/
	/***************/
	
	private static final int INFINITE = -1;
	
	private static final int lengthHeaderTwo = 24;
	//private static final int lengthTrailer = 6;
	private static final String encoding = "UTF-8";	//UTF-8 //Cp857

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
	 * @throws CaptureDeviceOpenException Si il est impossible d'ouvrir le réseau.
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
	 * @throws InvalidFilterException Si le filtre n'est pas valide.
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

		//							DOFUS PACKETS STRUCTURE
		
		//		|   X     X   |    X   |    X    |     X    X     |   ...   |
		//		| Packet type | Unknow | Channel | Message length | Message |
		//		|                      HeaderOne                  | Message |
		
		//		|   \   A   |  18.X  |   0  0    |    X   X    |  ... |   0  0    |  XXXX  |
		//		| Delimiter | Unknow | Delimiter | Name length | Name | Delimiter | Unknow |
		//		|                  HeaderTwo                   | Name |       Trailer      |

		if(data[0] == 0x0D)
			parseChat(data);
//		else if(data[0] == 0x0e) {// puis 0xdd
//			System.out.println("Echange !");
//		}
//		else
//			System.out.println("[Unknow soket (" + data[0] + ")]");
	}
	
	/** Retourne la taille de l'entête de la trâme.
	 * @param data - Trâme entrante.
	 * @return Taile de l'entête.
	 * @throws B4DException Si le type de trâme et donc la taille de l'entête est inconnue.
	 */
	private int getHeaderLength(byte[] data) throws B4DException {
		int length;
		switch(Byte.toUnsignedInt(data[1])) {
			case 0xC5:
			case 0xCD:
			case 0xC9:
				length = 6;
				break;
			case 0xCE:
			case 0xC6:
				length = 7;
				break;
			case 0x75:
				length = 9;
				break;
			default:
				length = -1;
				break;
		}
		if(length == -1)
			throw new B4DException("Unknow socket type [" + HexHelper.toString(data) + "]");
		
		return length;
	}
	
	  /******************/
	 /** PARSING CHAT **/
	/******************/
	
	/** Parse une trâme lorsque celle-ci est de type chat.
	 * Les informations du message sont extraites et ajouté à la queu du chat.
	 * @param data - Trâme entrante.
	 */
	private void parseChat(byte[] data) {
		try {
			int lengthHeaderOne = getHeaderLength(data);		
			Channel channel = Channel.fromByte(data[lengthHeaderOne-3]);
			
			int lengthText = Byte.toUnsignedInt(data[lengthHeaderOne-2])*256 + Byte.toUnsignedInt(data[lengthHeaderOne-1]);
			byte[] text = Arrays.copyOfRange(data, lengthHeaderOne, lengthHeaderOne + lengthText);

			int lengthName = Byte.toUnsignedInt(data[lengthHeaderOne + lengthText + lengthHeaderTwo - 1]);
			byte[] pseudo = Arrays.copyOfRange(data, lengthHeaderOne + lengthText + lengthHeaderTwo, lengthHeaderOne + lengthText + lengthHeaderTwo + lengthName);
			
			Message message = new Message(new String(pseudo, encoding), channel, new String(text, encoding));
			Dofus.getInstance().getChat().addMessage(message);
		} catch (B4DException | UnsupportedEncodingException e) {
				B4D.logger.warning(e.getMessage());
		}
	}
}
