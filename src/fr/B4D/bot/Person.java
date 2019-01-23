package fr.B4D.bot;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;
import java.util.List;

import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.dofus.Dofus;
import fr.B4D.transport.B4DWrongPosition;
import fr.B4D.transport.TransportInterface;
import fr.B4D.transport.TransportPath;
import fr.B4D.transport.TransportStep;
import fr.B4D.utils.PointF;

public class Person implements Serializable, TransportInterface{

	private static final long serialVersionUID = -3206212064770380443L;
	
	private String account;
	private String password;
	private Server server;
	private String pseudo;

	private TransportStep boosterPotion = null;
	private TransportStep bontaPotion = null;
	private TransportStep brakmarPotion = null;
	
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
	
	public TransportStep getBoosterPotion() {
		return boosterPotion;
	}

	public void setBoosterPotion(TransportStep boosterPotion) {
		this.boosterPotion = boosterPotion;
	}

	public TransportStep getBontaPotion() {
		return bontaPotion;
	}

	public void setBontaPotion(TransportStep bontaPotion) {
		this.bontaPotion = bontaPotion;
	}

	public TransportStep getBrakmarPotion() {
		return brakmarPotion;
	}

	public void setBrakmarPotion(TransportStep brakmarPotion) {
		this.brakmarPotion = brakmarPotion;
	}

	public PointF getSpellPosition() {
		return spellPosition;
	}

	public void setSpellPosition(PointF spellPosition) {
		this.spellPosition.setLocation(spellPosition);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position.setLocation(position);
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
		transportPath.use(this);
	}		
	public TransportPath getTransportPathTo(Point destination) throws AWTException, B4DCannotFind, B4DWrongPosition {		
		
		//Add potions
		if(boosterPotion != null) 
			Dofus.world.getGraph().addEdge(boosterPotion);
			
		if(bontaPotion != null)
			Dofus.world.getGraph().addEdge(bontaPotion);

		if(brakmarPotion != null)
			Dofus.world.getGraph().addEdge(brakmarPotion);
		
		//Get the shortest path
	    List<TransportStep> shortestPath = Dofus.world.getGraph().getPath(position, destination).getEdgeList();

	    //Remove potions
		if(brakmarPotion != null)
			Dofus.world.getGraph().removeEdge(boosterPotion);
		
		if(bontaPotion != null)
			Dofus.world.getGraph().removeEdge(bontaPotion);
		
		if(brakmarPotion != null)
			Dofus.world.getGraph().removeEdge(brakmarPotion);
		
		return new TransportPath(shortestPath);
	}
	
}
