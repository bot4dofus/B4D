package fr.B4D.classes;

import java.awt.Point;

public class Personnage {

	public String compte = "";
	public String motDePasse = "";
	public String serveur = "";
	public String pseudo = "";
	
	public Point positionPotionRappel_F;
	//public Zaap destinationPotionRappel = Dofus.Zaaps.Astrub
	
	public Point positionPotionBonta_F;
	public Point destinationPotionBonta = new Point(-33, -56);
	
	public Point positionPotionBrakmar_F;
	public Point destinationPotionBrakmar = new Point(-26,36);

	public Point positionSort;
	
	public Point position;
	public boolean inventairePlein = false;
	
	public Personnage(String compte, String motDePasse, String serveur, String pseudo) {
		this.compte = compte;
		this.motDePasse = motDePasse;
		this.serveur = serveur;
		this.pseudo = pseudo;
	}
}
