package fr.B4D.socket.os;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import fr.B4D.bot.B4DException;
import fr.B4D.socket.NetworkDeviceInfo;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.PacketCapture;

/** La classe {@code Windows} représente un système d'exploitation Windows.<br><br>
 * Cette classe étend la classe {@code OperatingSystem}.
 */
public class Windows extends OperatingSystem{

	/** Constructeur de la classe {@code Windows}.
	 */
	public Windows() {
		super("Windows", "jpcap.dll");
	}

	/* (non-Javadoc)
	 * @see fr.B4D.socket.os.OperatingSystem#findActiveDevice()
	 */
	public String findActiveDevice() throws B4DException {

		try {
			String currentAddress = null;
			Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
			while(nis.hasMoreElements() && currentAddress == null)
			{
				NetworkInterface ni = (NetworkInterface) nis.nextElement();
				if(ni.isUp()) {
					Enumeration<InetAddress> ias = ni.getInetAddresses();
					while (ias.hasMoreElements() && currentAddress == null)
					{
						InetAddress ia = (InetAddress) ias.nextElement();
						if(ia.isSiteLocalAddress() && !ia.isLoopbackAddress())
							currentAddress = ia.getHostAddress();
					}
				}
			}

			if(currentAddress == null)
				throw new B4DException("Merci de vérifier votre connection internet.", false);

			for (String jpcapDevice : PacketCapture.lookupDevices()) {
				NetworkDeviceInfo info = new NetworkDeviceInfo(jpcapDevice);
				if(info.getInterfaceAddress().equals(currentAddress))
					return info.getJpcapDeviceName();
			}
		} catch (CaptureDeviceLookupException | SocketException e1) {
			//Do nothing
		}
		throw new B4DException("No active device detected. Please try again.");
	}
}
