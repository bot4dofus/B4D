package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;

/**
 * The {@code LoggerAPI} class contains all the tutorials relative to the logger API.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Displays a message in the console</li>
 *     <li>Displays a popup message</li>
 *     <li>Displays a warning in the console</li>
 *     <li>Displays an error in the consol and in a popup</li>
 * </ul>
 *
 * @author Lucas
 *
 */
public final class LoggerAPITutorial1 extends Program {	

	/**
	 * Constructor of the logger API tutorial.
	 */
	public LoggerAPITutorial1() {
		super(Place.Tous, Category.Tutorial, "Logger API", "Tutorial 1", null, null);
	}
	
	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws B4DException {
		B4D.logger.debug("Ceci affcihe un message.");
		B4D.logger.popUp("Ceci affcihe un pop-up.");
		B4D.logger.warning("Ceci affcihe un avertissement.");
		B4D.logger.error(new B4DException("Ceci affcihe une erreur."));
	}
}
