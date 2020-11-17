package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;

/**
 * The {@code LoggerAPI} class contains all the tutorials relative to the logger API.
 * 
 * Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des logs.<br>
 *  <br>
 *  Fonctionnement :
 *  <ul>
 *  	<li>Envoi d'un message dans la console.</li>
 *  	<li>Affichage d'un message à l'écran.</li>
 *  	<li>Envoi d'un avertissement dans la console.</li>
 *  	<li>Affichage d'une erreur à l'écran.</li>
 *  </ul>
 *
 * @author Lucas
 *
 */
public final class LoggerAPITutorial extends Program {	

	/**
	 * Constructor of the logger API tutorial.
	 */
	public LoggerAPITutorial() {
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
