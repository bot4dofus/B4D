package fr.B4D.bot;

/**
 * The {@code Server} enumeration represents a Dofus server.
 * <br><br>
 * A server is defined by a name and an IP address.
 * 
 * @author Lucas
 *
 */
public enum Server {

	//"netstat -oan" to list all the connections under Windows
	
	/**
	 * The Furye server (French).
	 */
	FURYE("Furye", "34.255.129.242 || 63.34.101.111 || 172.65.232.71", false),
	
	/**
	 * The Merania server (French).
	 */
	MERIANA("Mériana", "34.243.42.81 || 172.65.198.58", false),
	
	/**
	 * The Merkator server (French).
	 */
	MERKATOR("Merkator", "34.253.54.78 || 172.65.194.249", false),
	
	/**
	 * The Pandore server (French).
	 */
	PANDORE("Pandore", "34.255.15.190", false),
	
	/**
	 * The Brumen server (French).
	 */
	BRUMEN("Brumen", "34.243.167.137", false),
	
	/**
	 * The Agride server (French).
	 */
	AGRIDE("Agride", "34.255.26.216", false),
	
	/**
	 * The Nidas server (French).
	 */
	NIDAS("Nidas", "52.50.239.1", false),
	
	/**
	 * The Ush server (French).
	 */
	USH("Ush", "52.214.35.62", false),
	
	/**
	 * The Julith server (French).
	 */
	JULITH("Julith", "52.213.230.140", false),
	
	/**
	 * The Echo server (International).
	 */
	ECHO("Echo", "54.194.216.90", true),
	
	/**
	 * The Ilyzaelle server (International).
	 */
	ILYZAELLE("Ilyzaelle", "34.242.48.97", true),
	
	/**
	 * The Otomustam server (Special).
	 */
	//OTOMUSTAM("OtoMustam", "0.0.0.0", true),
	
	/**
	 * The Tournois server (Special).
	 */
	//TOURNOIS("Tournois", "0.0.0.0", true),
	
	/**
	 * The Ombre server (Special).
	 */
	OMBRE("Ombre", "54.77.55.234", true);

	/**
	 * Name of the server.
	 */
	private String name;
	
	/**
	 * IP address of the server.
	 */
	private String ip;
	
	/**
	 * Specifies whether the server is international.
	 */
	private boolean international;

	/**
	 * Constructor of the {@code Server} enumeration.
	 * @param name - Name of the server.
	 * @param ip - IP address of the server (format x.x.x.x).
	 * @param international - {@code true} if the server is international, {@code false} otherwise.
	 */
	Server(String name, String ip, boolean international) {
		this.name = name;
		this.ip = ip;
		this.international = international;
	}

	/**
	 * Returns the name of the server.
	 * @return Name of the server.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the IP address of the server.
	 * @return IP address of the server.
	 */
	public String getIp() {
		return ip;
	}

	/** 
	 * Checks whether the server is international or not.
	 * @return {@code true} if the server is international, {@code false} otherwise.
	 */
	public boolean isInternationnal() {
		return international;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
}
