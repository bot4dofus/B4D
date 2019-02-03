package fr.B4D.programs.tutorials;

import java.awt.AWTException;
import java.awt.Point;

import fr.B4D.bot.Person;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.program.Status;
import fr.B4D.program.StopProgramException;
import fr.B4D.transport.B4DWrongPosition;

public final class TransportAPI {
	public final static Program TUTORIAL1 = new Program(Place.Aucun, Category.Tutorial, "Transport API", "Tutorial 1", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition, StopProgramException, CancelProgramException {
			person.goTo(new Point(4, -18));
			person.goTo(new Point(3, -19));
		}
	});

	public final static Program TUTORIAL2 = new Program(Place.Aucun, Category.Tutorial, "Transport API", "Tutorial 2", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition, StopProgramException, CancelProgramException {
			person.goTo(person.getBontaPotion().getDestination());
		}
	});
}
