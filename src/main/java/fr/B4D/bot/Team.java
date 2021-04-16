package fr.B4D.bot;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The {@code Team} class represents a list of persons that the player can use.
 * <br><br>
 * A team is a list of persons.
 * 
 * @author Lucas
 *
 */
public class Team extends ArrayList<Person> implements Serializable{
	private static final long serialVersionUID = -996256893142354267L;

	/**
	 * Constructor of the {@code Team} class. 
	 */
	public Team() {
		super();
	}
}
