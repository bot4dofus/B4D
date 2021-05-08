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
 * <br><br>
 * This tutorial focus on simple moves.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Goes to (3, -19).</li>
 *     <li>Goes to (3, -20).</li>
 *     <li>Goes to (3, -19).</li>
 *     <li>Goes to the player's registered zaap</li>
 * </ul>
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
		person.goTo(new Point(3, -19));
		person.goTo(new Point(3, -20));
		person.goTo(new Point(3, -19));
		person.goTo(person.getBoosterPotion().getDestination());
	}
}
