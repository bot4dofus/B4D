package fr.B4D.bot;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;
import java.util.List;

import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.dofus.Dofus;
import fr.B4D.transport.B4DEdge;
import fr.B4D.transport.B4DWrongPosition;
import fr.B4D.transport.TransportPath;
import fr.B4D.transport.TransportType;
import fr.B4D.transport.transports.Zaap;
import fr.B4D.utils.PointF;

public class Person implements Serializable{

	private static final long serialVersionUID = -3206212064770380443L;
	
	private String account;
	private String password;
	private Server server;
	private String pseudo;
	
	private PointF boosterPotionPosition = null;
	private Zaap boosterPotionDestination = Zaap.Astrub;
	
	private PointF bontaPotionPosition = null;
	private Point bontaPotionDestination = new Point(-33, -56);
	
	private PointF brakmarPotionPosition = null;
	private Point brakmarPotionDestination = new Point(-26,36);

	private PointF spellPosition = null;
	
	private Point position = null;
	private boolean inventoryFull = false;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	public Person() {
		this.account = "Nom de compte";
		this.password = "Mot de passe";
		this.server = Server.AGRIDE;
		this.pseudo = "Pseudo";
	}
	
	public Person(String account, String password, Server serveur, String pseudo) {
		this.account = account;
		this.password = password;
		this.server = serveur;
		this.pseudo = pseudo;
	}
	
	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public PointF getBoosterPotionPosition() {
		return boosterPotionPosition;
	}

	public void setBoosterPotionPosition(PointF boosterPotionPosition) {
		this.boosterPotionPosition = boosterPotionPosition;
	}

	public Zaap getBoosterPotionDestination() {
		return boosterPotionDestination;
	}

	public void setBoosterPotionDestination(Zaap boosterPotionDestination) {
		this.boosterPotionDestination = boosterPotionDestination;
	}

	public PointF getBontaPotionPosition() {
		return bontaPotionPosition;
	}

	public void setBontaPotionPosition(PointF bontaPotionPosition) {
		this.bontaPotionPosition = bontaPotionPosition;
	}

	public Point getBontaPotionDestination() {
		return bontaPotionDestination;
	}

	public void setBontaPotionDestination(Point bontaPotionDestination) {
		this.bontaPotionDestination = bontaPotionDestination;
	}

	public PointF getBrakmarPotionPosition() {
		return brakmarPotionPosition;
	}

	public void setBrakmarPotionPosition(PointF brakmarPotionPosition) {
		this.brakmarPotionPosition = brakmarPotionPosition;
	}

	public Point getBrakmarPotionDestination() {
		return brakmarPotionDestination;
	}

	public void setBrakmarPotionDestination(Point brakmarPotionDestination) {
		this.brakmarPotionDestination = brakmarPotionDestination;
	}

	public PointF getSpellPosition() {
		return spellPosition;
	}

	public void setSpellPosition(PointF spellPosition) {
		this.spellPosition = spellPosition;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public boolean isInventoryFull() {
		return inventoryFull;
	}

	public void setInventoryFull(boolean inventoryFull) {
		this.inventoryFull = inventoryFull;
	}
	
	  /***********/
	 /** GO TO **/
	/***********/
	
	public void goTo(Point destination) throws AWTException, B4DCannotFind, B4DWrongPosition {
		TransportPath transportPath = getTransportPathTo(destination);
		transportPath.use();
	}		
	public TransportPath getTransportPathTo(Point destination) throws AWTException, B4DCannotFind, B4DWrongPosition {		
		
		//Add potions
		if(boosterPotionDestination != null) 
			Dofus.world.addB4DEdge(position, boosterPotionDestination.getPosition(), TransportType.BoosterPotion, boosterPotionCost);
			
		if(bontaPotionDestination != null)
			Dofus.world.addB4DEdge(position, bontaPotionDestination, TransportType.BontaPotion, bontaPotionCost);

		if(brakmarPotionDestination != null)
			Dofus.world.addB4DEdge(position, brakmarPotionDestination, TransportType.BrakmarPotion, brakmarPotionCost);
		
		//Get the shortest path
	    List<B4DEdge> shortestPath = Dofus.world.getPath(position, destination).getEdgeList();

	    //Remove potions
		if(boosterPotionDestination != null)
			Dofus.world.removeB4DEdge(position, boosterPotionDestination.getPosition());
		
		if(bontaPotionDestination != null)
			Dofus.world.removeB4DEdge(position, bontaPotionDestination);
		
		if(brakmarPotionDestination != null)
			Dofus.world.removeB4DEdge(position, brakmarPotionDestination);
		
		return new TransportPath(shortestPath);
	}
	
}
