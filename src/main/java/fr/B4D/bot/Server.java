package fr.B4D.bot;

import java.io.Serializable;
import java.util.ArrayList;

/** La classe {@code Server} représente un serveur de jeu dofus.<br><br>
 * Un serveur est défini par un nom et une ip.
 */
public class Server implements Serializable{

	private static final long serialVersionUID = 4209292777849996330L;

	/****************/
	/** COLLECTION **/
	/****************/

	//netstat -oan pour lister les connections

	//French
	public final static Server FURYE = new Server("Furye", "34.255.129.242 || 63.34.101.111", false); //precedement 34.255.26.216
	public final static Server MERIANA = new Server("Mériana", "34.243.42.81 || 172.65.198.58", false);
	public final static Server MERKATOR = new Server("Merkator", "34.253.54.78 || 172.65.194.249", false);
	public final static Server PANDORE = new Server("Pandore", "34.255.15.190", false);
	public final static Server BRUMEN = new Server("Brumen", "34.243.167.137", false);
	public final static Server AGRIDE = new Server("Agride", "34.255.26.216", false);
	public final static Server NIDAS = new Server("Nidas", "52.50.239.1", false);
	public final static Server USH = new Server("Ush", "52.214.35.62", false);
	public final static Server JULITH = new Server("Julith", "52.213.230.140", false);

	//Internationnals
	public final static Server ECHO = new Server("Echo", "54.194.216.90", true);
	public final static Server ILYZAELLE = new Server("Ilyzaelle", "34.242.48.97", true);

	//Specials
	//public final static Server OTOMUSTAM = new Server("OtoMustam", "0.0.0.0", true);
	//public final static Server TOURNOIS = new Server("Tournois", "0.0.0.0", true);
	public final static Server OMBRE = new Server("Ombre", "54.77.55.234", true);

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
	public Server(String name, String ip, boolean international) {
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

	/************************/
	/** METHODES STATIQUES **/
	/************************/

	/** Retourne la liste de tous les serveurs.
	 * @return Liste de tous les serveur.
	 */
	public final static ArrayList<Server> getAll(){
		ArrayList<Server> servers = new ArrayList<Server>();
		servers.add(FURYE);
		servers.add(MERIANA);
		servers.add(MERKATOR);
		servers.add(PANDORE);
		servers.add(BRUMEN);
		servers.add(AGRIDE);
		servers.add(NIDAS);
		servers.add(USH);
		servers.add(JULITH);

		servers.add(ECHO);
		servers.add(ILYZAELLE);

		//servers.add(OTOMUSTAM);
		//servers.add(TOURNOIS);
		servers.add(OMBRE);
		return servers;
	}

	/** Permet de retrouver un serveur à partir de son nom.
	 * @param name - Nom du serveur.
	 * @return Serveur correspondant, {@code null} si aucun résultat.
	 */
	public final static Server getServer(String name) {
		return getAll().stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
	}


	/***************/
	/** TO STRING **/
	/***************/

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name + " (" + ip + ")";
	}
}
