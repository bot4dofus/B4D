package fr.B4D.program;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.Server;
import fr.B4D.transport.transports.Zaap;
import fr.B4D.utils.PointF;

public class Person implements Serializable{

	private static final long serialVersionUID = -3206212064770380443L;
	
	public String account;
	public String password;
	public Server server;
	public String pseudo;
	
	public PointF boosterPotionPosition = null;
	public Zaap boosterPotionDestination = Zaap.Astrub;
	
	public PointF bontaPotionPosition = null;
	public Point bontaPotionDestination = new Point(-33, -56);
	
	public PointF brakmarPotionPosition = null;
	public Point brakmarPotionDestination = new Point(-26,36);

	public PointF spellPosition = null;
	
	public Point position = null;
	public boolean inventoryState = false;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public Person() {
		this.account = "Nom de compte";
		this.password = "Mot de passe";
		this.server = Server.values()[0];
		this.pseudo = "Pseudo";
	}
	
	public Person(String account, String password, Server serveur, String pseudo) {
		this.account = account;
		this.password = password;
		this.server = serveur;
		this.pseudo = pseudo;
	}
}
