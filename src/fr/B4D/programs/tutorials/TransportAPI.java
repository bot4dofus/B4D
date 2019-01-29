package fr.B4D.programs.tutorials;

import java.awt.AWTException;
import java.awt.Point;

import javax.swing.JOptionPane;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.program.ProgramInterface;
import fr.B4D.transport.B4DWrongPosition;

public final class TransportAPI {
	public static ProgramInterface tutorial1 = new ProgramInterface() {
		public void run(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition {
			person.goTo(new Point(4, -18));
			person.goTo(new Point(3, -19));
		}
	};

	public static ProgramInterface tutorial2 = new ProgramInterface() {
		public void run(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition {
			B4D.logger.popUp("Le programme de test marche correctement.");
		}
	};

	public static ProgramInterface tutorial3 = new ProgramInterface() {
		public void run(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition {
			
		}
	};
}
