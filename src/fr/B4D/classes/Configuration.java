package fr.B4D.classes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Configuration {

	public ArrayList<Person> persons;
	public Rectangle gameFrame;
	public Rectangle chatFrame;
	public Point chatBar;
	public Point minimap;
	
	public Configuration(){
		this.persons = new ArrayList<Person>();
		persons.add(new Person("Nom1", "MDP1", "Serveur1", "Pseudo1"));
		persons.add(new Person("Nom2", "MDP2", "Serveur2", "Pseudo2"));
		this.gameFrame = null;
		this.chatFrame = null;
		this.chatBar = null;
		this.minimap = null;
	}
}
