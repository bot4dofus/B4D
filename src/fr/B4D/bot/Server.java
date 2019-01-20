package fr.B4D.bot;

import java.io.Serializable;
import java.util.ArrayList;

public class Server implements Serializable{

	private static final long serialVersionUID = 4209292777849996330L;

	  /****************/
	 /** COLLECTION **/
	/****************/
	
	//French
	public final static Server FURYE = new Server("Furye", "0.0.0.0");
	public final static Server MERIANA = new Server("Mériana", "0.0.0.0");
	public final static Server MERKATOR = new Server("Merkator", "0.0.0.0");
	public final static Server PANDORE = new Server("Pandore", "0.0.0.0");
	public final static Server BRUMEN = new Server("Brumen", "0.0.0.0");
	public final static Server AGRIDE = new Server("Agride", "0.0.0.0");
	public final static Server NIDAS = new Server("Nidas", "0.0.0.0");
	public final static Server USH = new Server("Ush", "0.0.0.0");
	public final static Server ILYZAELLE = new Server("Ilyzaelle", "34.242.48.97");
	public final static Server JULITH = new Server("Julith", "0.0.0.0");

	//Internationnals
	public final static Server OTOMUSTAM = new Server("OtoMustam", "0.0.0.0");
	public final static Server OMBRE = new Server("Ombre", "0.0.0.0");
	public final static Server ECHO = new Server("Echo", "54.194.216.90");
	
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
    servers.add(ILYZAELLE);
    servers.add(JULITH);
    servers.add(OTOMUSTAM);
    servers.add(OMBRE);
    servers.add(ECHO);
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
