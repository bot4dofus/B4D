package fr.B4D.modules;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import fr.B4D.classes.Bot;

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

	public static void focusBot() {
		Bot.window.frame.requestFocus();	//Donne le focus au bot
	}
	
	public static void focusDofus() {
		//a implementer
        //B4DMouse.Clic_Gauche(New Point(Screen.PrimaryScreen.Bounds.Width / 2, 0), False)
	}
}
