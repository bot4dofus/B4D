package fr.B4D.bot;

import java.io.Serializable;
import java.util.ArrayList;

public class Server implements Serializable{

	private static final long serialVersionUID = 4209292777849996330L;

	  /****************/
	 /** COLLECTION **/
	/****************/
	
	//French
	public final static Server FURYE = new Server("Furye", "34.255.26.216");
	public final static Server MERIANA = new Server("Mériana", "34.243.42.81");
	public final static Server MERKATOR = new Server("Merkator", "34.253.54.78");
	public final static Server PANDORE = new Server("Pandore", "34.255.15.190");
	public final static Server BRUMEN = new Server("Brumen", "34.243.167.137");
	public final static Server AGRIDE = new Server("Agride", "34.255.26.216");
	public final static Server NIDAS = new Server("Nidas", "52.50.239.1");
	public final static Server USH = new Server("Ush", "52.214.35.62");
	public final static Server JULITH = new Server("Julith", "52.213.230.140");

	//Internationnals
	public final static Server ECHO = new Server("Echo", "54.194.216.90");
	public final static Server ILYZAELLE = new Server("Ilyzaelle", "34.242.48.97");

	//Specials
	//public final static Server OTOMUSTAM = new Server("OtoMustam", "0.0.0.0");
	//public final static Server TOURNOIS = new Server("Tournois", "0.0.0.0");
	public final static Server OMBRE = new Server("Ombre", "54.77.55.234");
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private String name;
	private String ip;
	
	  /*****************/
	 /** CONSTRUCTOR **/
	/*****************/
	
	public Server(String name, String ip) {
		this.name = name;
		this.ip = ip;
	}

	  /************************/
	 /** METHODES STATIQUES **/
	/************************/
	
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

  public final static Server getServer(String name) {
	  return getAll().stream().filter(s -> s.getName().equals(name)).findFirst().orElse(null);
  }
  
	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/
  
	public String getName() {
		return name;
	}

	public String getIp() {
		return ip;
	}
}
