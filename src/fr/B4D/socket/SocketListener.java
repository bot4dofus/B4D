package fr.B4D.socket;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import fr.B4D.bot.B4D;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.log.Logger;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.CapturePacketException;
import net.sourceforge.jpcap.capture.InvalidFilterException;
import net.sourceforge.jpcap.capture.PacketCapture;
import net.sourceforge.jpcap.capture.RawPacketListener;
import net.sourceforge.jpcap.net.RawPacket;

public class SocketListener extends Thread{
	private static final int INFINITE = -1;
	
	private static final int lengthHeaderOne = 6;
	private static final int lengthHeaderTwo = 24;
	//private static final int lengthTrailer = 6;
	private static final String encoding = "UTF-8";
	
	private PacketCapture m_pcap;
	private String m_device;

	public SocketListener() throws CaptureDeviceLookupException, NoSocketDetectedException, CaptureDeviceOpenException, InvalidFilterException {
		m_pcap = new PacketCapture();
		m_device = NetworkFinder.find();
		System.out.println("Network found : " + m_device);
		m_pcap.open(m_device, 65535, true, 1000);
		m_pcap.setFilter("host " + B4D.getConfiguration().getPersons().get(0).getServer().getIp(), true);
		m_pcap.addRawPacketListener(new RawPacketListener() {
			public void rawPacketArrived(RawPacket data) {
				if(data.getData().length > 54) {
					byte[] dataArray = Arrays.copyOfRange(data.getData(), 54, data.getData().length -1);
					parseDofus(dataArray);
				}
			}
			
		});
	}
	
	public void run () {
		try {
			m_pcap.capture(INFINITE);
		} catch (CapturePacketException e) {
			Logger.error("La capture des packet s'est arretée.", e);
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

		if(data[0] == 0x0D) {
			parseChat(data);
		}
		//else
			//System.out.println("[Unknow (" + byte0 + " " + byte1 + ")]");
	}
	
	public void parseChat(byte[] data) {
		try {
			Channel channel = Message.getChannel(data[3]);

			int lengthText = Byte.toUnsignedInt(data[lengthHeaderOne - 1]);
			byte[] text = Arrays.copyOfRange(data, lengthHeaderOne, lengthHeaderOne + lengthText);

			int lengthName = Byte.toUnsignedInt(data[lengthHeaderOne + lengthText + lengthHeaderTwo - 1]);
			byte[] pseudo = Arrays.copyOfRange(data, lengthHeaderOne + lengthText + lengthHeaderTwo, lengthHeaderOne + lengthText + lengthHeaderTwo + lengthName);
			
			Message message = new Message(new String(pseudo, encoding), channel, new String(text, encoding));
			Dofus.chat.addMessage(message);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
