package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.StopProgramException;

/**
 * The {@code TransportAPI} class contains all the tutorials relative to the transport API.
 * Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des transports.<br>
 *  <br>
 *  Fonctionnement :
 *  <ul>
 *  	<li>Se rend au zaap enregistré par le joueur.</li>
 *  </ul>
 *
 * @author Lucas
 *
 */
public final class TransportAPITutorial2 extends Program {

	/**
	 * Constructor of the transport API tutorial 2.
	 */
	public TransportAPITutorial2() {
		super(Place.Tous, Category.Tutorial, "Transport API", "Tutorial 2", null, null);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws StopProgramException, CancelProgramException, B4DException {
		person.goTo(person.getBontaPotion().getDestination());
	}
}
