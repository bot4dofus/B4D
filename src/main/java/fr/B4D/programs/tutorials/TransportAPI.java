package fr.B4D.programs.tutorials;

import java.awt.Point;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.program.StopProgramException;

public final class TransportAPI {
	
	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des transports.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Se rend en (4, -18).</li>
	 *  	<li>Se rend en (3, -19).</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL1 = new Program(Place.Tous, Category.Tutorial, "Transport API", "Tutorial 1", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws StopProgramException, CancelProgramException, B4DException {
			person.goTo(new Point(4, -18));
			person.goTo(new Point(3, -19));
		}
	});

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des transports.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Se rend au zaap enregistré par le joueur.</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL2 = new Program(Place.Tous, Category.Tutorial, "Transport API", "Tutorial 2", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws StopProgramException, CancelProgramException, B4DException {
			person.goTo(person.getBontaPotion().getDestination());
		}
	});
}
