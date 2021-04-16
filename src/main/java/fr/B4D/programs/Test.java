package fr.B4D.programs;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.FullInventoryException;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.StopProgramException;

/**
 * The {@code Loto} class contains the loto program.
 * 
 * @author Lucas
 *
 */
public final class Test extends Program{
	
	/**
	 * Test program interface.
	 */
	private int iteration = 1;
	
	/**
	 * Constructor of the test program.
	 */
	public Test() {
		super(Place.Tous, Category.Test, "Test", "Test", null, null);
	}

	@Override
	public void intro(Person person) throws CancelProgramException {
		B4D.logger.popUp("Début du programme de test");
	}

	@Override
	public void cycle(Person person) throws FullInventoryException, StopProgramException, CancelProgramException, B4DException {
		B4D.logger.popUp("Iteration " + iteration++);
	}

	@Override
	public void outro(Person person) {
		B4D.logger.popUp("Fin du programme de test");
	}
}
