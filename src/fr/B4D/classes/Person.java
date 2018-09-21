package fr.B4D.classes;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.classes.transports.Zaap;

public class Person implements Serializable{

	public String account = "";
	public String password = "";
	public String serveur = "";
	public String pseudo = "";
	
	public PointF rappelPotionPosition = null;
	public Zaap rappelPotionDestination = Zaap.Astrub;
	
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
		this.serveur = "Serveur";
		this.pseudo = "Pseudo";
	}
	
	public Person(String account, String password, String serveur, String pseudo) {
		this.account = account;
		this.password = password;
		this.serveur = serveur;
		this.pseudo = pseudo;
	}
}
