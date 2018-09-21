package fr.B4D.modules;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import fr.B4D.classes.Configuration;

public final class B4DOther {
		
	  /*****************/
	 /** ADRESSE MAC **/
	/*****************/
	
	public static String getMacAdress() throws SocketException, UnknownHostException {

		NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());	
		byte[] mac = network.getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
		}
		return sb.toString();
	}
}
