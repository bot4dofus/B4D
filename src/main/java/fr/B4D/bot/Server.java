package fr.B4D.bot;

/** La classe {@code Server} représente un serveur de jeu dofus.<br><br>
 * Un serveur est défini par un nom et une ip.
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

	/**************/
	/** ATRIBUTS **/
	/**************/

	private String name;
	private String ip;
	private boolean international;

	/*****************/
	/** CONSTRUCTOR **/
	/*****************/

	/** Constructeur de la classe {@code Server}.
	 * @param name - Nom du serveur.
	 * @param ip - Ip du serveur au format x.x.x.x.
	 * @param international - {@code true} si le serveur est internationnal, {@code false} sinon.
	 */
	Server(String name, String ip, boolean international) {
		this.name = name;
		this.ip = ip;
		this.international = international;
	}

	/***********************/
	/** GETTERS & SETTERS **/
	/***********************/

	/** Retourne le nom du serveur.
	 * @return Nom du serveur.
	 */
	public String getName() {
		return name;
	}

	/** Retourne l'ip du serveur.
	 * @return Ip du serveur.
	 */
	public String getIp() {
		return ip;
	}

	/** Retourne un booleen représentant si le server est internationnal ou non.
	 * @return {@code true} si le serveur est internationnal, {@code false} sinon.
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
