package fr.B4D.modules;

import java.net.InetAddress;
import java.net.NetworkInterface;
import fr.B4D.classes.B4DException;
import fr.B4D.classes.B4DException.Reason;

public final class B4DOther {
		
	  /*****************/
	 /** ADRESSE MAC **/
	/*****************/
	
	public static String getMacAdress() throws B4DException{
		try {
			NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());	
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			return sb.toString();
		} catch (Exception e) {
			throw new B4DException(Reason.MacAdress);
		}

	}
	
}
