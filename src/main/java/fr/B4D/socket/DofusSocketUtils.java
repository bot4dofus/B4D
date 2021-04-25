package fr.B4D.socket;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;

import fr.B4D.bot.B4DException;

/**
 * The {@code SocketUtils} class is used to define useful methods relative to sockets.
 * 
 * @author Lucas
 *
 */
public class DofusSocketUtils {

	/**
	 * Find the current active pcap network interface.
	 * @return The active pcap network interface
	 * @throws B4DException if no device has been found.
	 */
	public static PcapNetworkInterface findActiveDevice() throws B4DException{
		try {
			InetAddress currentAddress = null;
			Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
			while(nis.hasMoreElements() && currentAddress == null)
			{
				NetworkInterface ni = nis.nextElement();
				if(ni.isUp() && !ni.isLoopback()) {
					Enumeration<InetAddress> ias = ni.getInetAddresses();
					while (ias.hasMoreElements() && currentAddress == null)
					{
						InetAddress ia = ias.nextElement();
						if(ia.isSiteLocalAddress() && !ia.isLoopbackAddress())
							currentAddress = ia;
					}
				}
			}
			
			if(currentAddress == null)
				throw new B4DException("No active address found. Make sure you have an internet connection.");
			
			PcapNetworkInterface nif = Pcaps.getDevByAddress(currentAddress);
			if(nif == null)
				throw new B4DException("No active device found. Make sure WinPcap or libpcap is installed.");
			
			return nif;
		}catch(SocketException | PcapNativeException e) {
			throw new B4DException(e);
		}
	}
	
	/**
	 * Finds a subarray in an array.
	 * @param subArray Subarray to look for.
	 * @param array Array in which search for the subarray.
	 * @return THe index of subarray beginning, {@code null} if not found.
	 */
	public static Integer findArray(byte[] subArray, byte[] array) {
		if(subArray == null)
			throw new IllegalArgumentException("Cannot be null.");
		if(array == null)
			throw new IllegalArgumentException("Cannot be null.");
		if(subArray.length == 0)
			throw new IllegalArgumentException("Cannot be empty.");
		if(array.length == 0)
			throw new IllegalArgumentException("Cannot be empty.");
		
		for(int i=0; i<=array.length-subArray.length; i++) {
			int correct = 0;
			
			for(int j=0; j<subArray.length; j++) {				
				if(array[i+j] == subArray[j]) {
					correct++;

					if(correct == subArray.length)
						return i;
				}
				else
					break;
			}
			
		}
		return null;
	}
}
