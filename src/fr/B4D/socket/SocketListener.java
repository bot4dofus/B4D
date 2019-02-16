package fr.B4D.socket;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Server;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.interaction.chat.UnknowChannelException;
import fr.B4D.interaction.chat.UnknowSocketTypeException;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.CapturePacketException;
import net.sourceforge.jpcap.capture.InvalidFilterException;
import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;

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
	
	private PacketCapture m_pcap;
	private String m_device;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	public SocketListener() throws CaptureDeviceLookupException, NoSocketDetectedException, CaptureDeviceOpenException, InvalidFilterException {
		m_pcap = new PacketCapture();
		m_device = NetworkFinder.find();
		B4D.logger.debug(this, "Network found : " + m_device);
		m_pcap.open(m_device, 65535, true, 1000);
		m_pcap.addRawPacketListener(new RawPacketListener() {
			public void rawPacketArrived(RawPacket data) {
				if(data.getData().length > 54) {
					byte[] dataArray = Arrays.copyOfRange(data.getData(), 54, data.getData().length -1);
					parseDofus(dataArray);
				}
			}
			
		});
	}
	
	  /*********/
	 /** RUN **/
	/*********/
	
	public void interrupt() {
		m_pcap.endCapture();
	}
	
	public void run() {
		try {
			B4D.logger.debug(this, "Lancement du thread");
			m_pcap.capture(INFINITE);
			B4D.logger.debug(this, "Fin du thread");
		} catch (CapturePacketException e) {
			B4D.logger.error(e);
		}
	}

	  /*************/
	 /** METHODS **/
	/*************/
	
	public void setFilter(Server serveur) throws InvalidFilterException {
		m_pcap.setFilter("host " + serveur.getIp(), true);
	}
	
	  /*************/
	 /** PARSING **/
	/*************/
	
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
	
	private int getHeaderLength(byte[] data) throws UnknowSocketTypeException {
		int length;
		switch(Byte.toUnsignedInt(data[1])) {
			case 197:
			case 205:
			case 0xC9:
				length = 6;
				break;
			case 0xCE:
			case 198:
				length = 7;
				break;
			default:
				length = -1;
				break;
		}
		if(length == -1)
			throw new UnknowSocketTypeException(data);
		
		return length;
	}
	
	  /******************/
	 /** PARSING CHAT **/
	/******************/
	
	private void parseChat(byte[] data) {

		try {
			int lengthHeaderOne = getHeaderLength(data);			
			Channel channel = Channel.fromByte(data[lengthHeaderOne-3]);
			
			int lengthText = Byte.toUnsignedInt(data[lengthHeaderOne-2])*256 + Byte.toUnsignedInt(data[lengthHeaderOne-1]);
			byte[] text = Arrays.copyOfRange(data, lengthHeaderOne, lengthHeaderOne + lengthText);

			int lengthName = Byte.toUnsignedInt(data[lengthHeaderOne + lengthText + lengthHeaderTwo - 1]);
			byte[] pseudo = Arrays.copyOfRange(data, lengthHeaderOne + lengthText + lengthHeaderTwo, lengthHeaderOne + lengthText + lengthHeaderTwo + lengthName);
			
			Message message = new Message(new String(pseudo, encoding), channel, new String(text, encoding));
			Dofus.chat.addMessage(message);
		} catch (UnknowChannelException | UnsupportedEncodingException | UnknowSocketTypeException e) {
				B4D.logger.debug(this, e.getMessage());
		}
	}
}
