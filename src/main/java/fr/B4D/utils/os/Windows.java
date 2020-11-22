package fr.B4D.utils.os;

import java.io.IOException;
import java.util.List;

import fr.B4D.bot.B4DException;

/**
 * The {@code Windows} class gives access to useful methods relative to the Windows Operating System.
 * @author Lucas
 *
 */
public class Windows extends Os{

	public String findServerIp() throws B4DException {
		try {
			List<String> lines = Os.exec("cmd.exe", "/c", "netstat", "-a", "-p", "TCP", "-n", "|", "findstr", ":5555");				
			if(lines.size() == 0)
				throw new B4DException("Coudn't find the server ip: The netstat command returned an empty result.");
			if(lines.size() > 1)
				throw new B4DException("Coudn't find the server ip: The netstat command returned more than one result.");
			
			String[] worlds = lines.get(0).split(" ");
			String ipAndPort = null;
			
			for(String word:worlds) {
				if(!word.isEmpty() && word.contains(":5555"))
					ipAndPort = word;
			}
			
			if(ipAndPort == null)
				throw new B4DException("Couldn't find th server ip: ip is null.");
			
			String[] address = ipAndPort.split(":");
			
			if(address.length != 2)
				throw new B4DException("Couldn't find th server ip: server address is " + address.length + " long.");
			
			return address[0];
		} catch (IOException e) {
			throw new B4DException("Coudn't find the server ip:", e);
		}
	}
}
