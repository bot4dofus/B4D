package fr.B4D.bot;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

public class Configuration implements Serializable{

	private static final long serialVersionUID = -1787414977374798817L;
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private ArrayList<Person> persons;
	private Rectangle gameFrame;
	private Rectangle chatFrame;
	private Point chatBar;
	private Point minimap;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	public Configuration(){
		this.persons = new ArrayList<Person>();
		persons.add(new Person("Nom de compte", "Mot de passe", Server.FURYE, "Pseudo"));
		persons.add(new Person("Nom de compte", "Mot de passe", Server.OTOMUSTAM, "Pseudo"));
		this.gameFrame = null;
		this.chatFrame = null;
		this.chatBar = null;
		this.minimap = null;
	}
	
	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/

	public ArrayList<Person> getPersons() {
		return persons;
	}

	public Rectangle getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(Rectangle gameFrame) {
		this.gameFrame = gameFrame;
	}

	public Rectangle getChatFrame() {
		return chatFrame;
	}

	public void setChatFrame(Rectangle chatFrame) {
		this.chatFrame = chatFrame;
	}

	public Point getChatBar() {
		return chatBar;
	}

	public void setChatBar(Point chatBar) {
		this.chatBar = chatBar;
	}

	public Point getMinimap() {
		return minimap;
	}

	public void setMinimap(Point minimap) {
		this.minimap = minimap;
	}
}
