package fr.B4D.bot;

import java.io.Serializable;
import java.util.ArrayList;

/** La classe {@code Team} représente un ensemble de personnage que le joueur peut jouer.<br><br>
 * Une Team est défini par une liste de personnage.
 */
public class Team extends ArrayList<Person> implements Serializable{
	private static final long serialVersionUID = -996256893142354267L;

	/** Constructeur de la classe {@code Team}. 
	 */
	public Team() {
		super();
	}
}
