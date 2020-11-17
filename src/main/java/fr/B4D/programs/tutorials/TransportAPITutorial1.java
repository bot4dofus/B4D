package fr.B4D.programs.tutorials;

import java.awt.Point;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.StopProgramException;

/**
 * The {@code TransportAPI} class contains all the tutorials relative to the transport API.
 * 
 * Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des transports.<br>
 *  <br>
 *  Fonctionnement :
 *  <ul>
 *  	<li>Se rend en (4, -18).</li>
 *  	<li>Se rend en (3, -19).</li>
 *  </ul>
 *
 * @author Lucas
 *
 */
public final class TransportAPITutorial1 extends Program {

	/**
	 * Constructor of the transport API tutorial 1.
	 */
	public TransportAPITutorial1() {
		super(Place.Tous, Category.Tutorial, "Transport API", "Tutorial 1", null, null);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws StopProgramException, CancelProgramException, B4DException {
		person.goTo(new Point(4, -18));
		person.goTo(new Point(3, -19));
	}
}
