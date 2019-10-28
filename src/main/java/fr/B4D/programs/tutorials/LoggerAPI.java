package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;

public final class LoggerAPI {	

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des logs.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Envoi d'un message dans la console.</li>
	 *  	<li>Affichage d'un message à l'écran.</li>
	 *  	<li>Envoi d'un avertissement dans la console.</li>
	 *  	<li>Affichage d'une erreur à l'écran.</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL1 = new Program(Place.Aucun, Category.Tutorial, "Logger API", "Tutorial 1", new Channel[] {Channel.PRIVATE}, Status.AVAILABLE, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws B4DException {
			B4D.logger.debug("Ceci affcihe un message.");
			B4D.logger.popUp("Ceci affcihe un pop-up.");
			B4D.logger.warning("Ceci affcihe un avertissement.");
			B4D.logger.error(new B4DException("Ceci affcihe une erreur."));
		}
	});
}
