package fr.B4D.bot;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;

import fr.B4D.program.Person;

public class Configuration implements Serializable{

	private static final long serialVersionUID = -1787414977374798817L;

	public ArrayList<Person> persons;
	public Rectangle gameFrame;
	public Rectangle chatFrame;
	public Point chatBar;
	public Point minimap;

	public boolean hdvWhenFull;
	public boolean bankWhenFull;
	public boolean stopWhenFull;
	
	private static Configuration instance;
	
	private Configuration(){
		this.persons = new ArrayList<Person>();
		persons.add(new Person("Nom de compte", "Mot de passe", Server.FURYE, "Pseudo"));
		persons.add(new Person("Nom de compte", "Mot de passe", Server.OTOMUSTAM, "Pseudo"));
		this.gameFrame = null;
		this.chatFrame = null;
		this.chatBar = null;
		this.minimap = null;
		this.hdvWhenFull = false;
		this.bankWhenFull = false;
		this.stopWhenFull = true;
	}
	
	public static Configuration getInstance() {
		if(instance == null)
			instance = new Configuration();
		return instance;
	}
	
	public static Configuration setInstance(Configuration configuration) {
		instance = configuration;
		return instance;
	}
}
