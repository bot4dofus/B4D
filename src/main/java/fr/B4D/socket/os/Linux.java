package fr.B4D.socket.os;

import java.util.List;

import fr.B4D.bot.B4DException;

/** La classe {@code Linux} représente un système d'exploitation Linux.<br><br>
 * Cette classe étend la classe {@code OperatingSystem}.
 */
public class Linux extends OperatingSystem{
	
	/** Constructeur de la classe {@code Windows}.
	 */
	public Linux() {
		super("Linux", "libjpcap.so");
	}
	
	/* (non-Javadoc)
	 * @see fr.B4D.socket.os.OperatingSystem#findActiveDevice()
	 */
	public String findActiveDevice() throws B4DException {
		List<String> lines = exec("ip addr");
		for(String line:lines) {
			if(line.contains("state UP"))
				return line.split(": ")[1];
		}
		throw new B4DException("No active device detected. Please try again.");
	}
}
