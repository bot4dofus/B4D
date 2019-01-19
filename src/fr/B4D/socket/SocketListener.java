package fr.B4D.socket;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import fr.B4D.bot.Server;
import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;

public class SocketListener {
	private static final int INFINITE = -1;
	private static final int PACKET_COUNT = INFINITE;
	
	private PacketCapture m_pcap;
	private String m_device;

	public SocketListener(Server server) throws Exception {
		m_pcap = new PacketCapture();
		m_device = NetworkFinder.find();
		System.out.println("Device found : " + m_device);
		m_pcap.open(m_device, 65535, true, 1000);
		m_pcap.setFilter("host " + server.getIp(), true);
		m_pcap.addRawPacketListener(new RawPacketHandler());
		m_pcap.capture(PACKET_COUNT);
	}
}


class RawPacketHandler implements RawPacketListener 
{
	private int lengthHeaderOne = 6;
	private int lengthHeaderTwo = 24;
	//private int lengthTrailer = 6;
	private String encoding = "UTF-8";
	
	public void rawPacketArrived(RawPacket data) {
		if(data.getData().length > 54) {
			byte[] dataArray = Arrays.copyOfRange(data.getData(), 54, data.getData().length -1);
			parseDofus(dataArray);
		}
	}

	public void parseDofus(byte[] data) {	

		//							DOFUS PACKETS STRUCTURE
		
		//		|   X     X   |    X   |    X    |     0     |        X       |   ...   |
		//		| Packet type | Unknow | Channel | Delimiter | Message length | Message |
		//		|                           HeaderOne                         | Message |
		
		//		|   \   A   |  18.X  |  0  0  0  |      X      |  ... |   0  0    |  XXXX  |
		//		| Delimiter | Unknow | Delimiter | Name length | Name | Delimiter | Unknow |
		//		|                  HeaderTwo                   | Name |       Trailer      |
		
		//System.out.println("[" + HexHelper.toString(data) + "]");
		//ip.src eq 34.242.48.97 && frame contains "regex"

		int byte0 = Byte.toUnsignedInt(data[0]);

		if(byte0 == 0x0D) {
			parseChat(data);
		}
		//else
			//System.out.println("[Unknow (" + byte0 + " " + byte1 + ")]");
	}
	
	public void parseChat(byte[] data) {
		try {
			byte channel = data[3];
			switch(channel) {
				case(0x00):
					System.out.print("[Général]");
					break;
				case(0x04):
					System.out.print("[Groupe]");
					break;
				case(0x09):
					System.out.print("[Privé]");
					break;
				case(0x05):
					System.out.print("[Commerce]");
					break;
				case(0x06):
					System.out.print("[Recrutement]");
					break;
				default:
					System.out.print("[Unknow channel = " + data[3] + "]");
					break;
			}

			int lengthMessage = Byte.toUnsignedInt(data[lengthHeaderOne - 1]);
			
			byte[] message = Arrays.copyOfRange(data, lengthHeaderOne, lengthHeaderOne + lengthMessage);
			System.out.print("[" + new String(message, encoding) + "]");

			int lengthName = Byte.toUnsignedInt(data[lengthHeaderOne + lengthMessage + lengthHeaderTwo - 1]);
			byte[] name = Arrays.copyOfRange(data, lengthHeaderOne + lengthMessage + lengthHeaderTwo, lengthHeaderOne + lengthMessage + lengthHeaderTwo + lengthName);
			System.out.println("[" + new String(name, encoding) + "]");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
