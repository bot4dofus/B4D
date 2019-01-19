package fr.B4D.programs;

import java.awt.AWTException;
import java.awt.Point;

import fr.B4D.bot.B4D;
import fr.B4D.program.ProgramInterface;
import fr.B4D.transport.B4DCannotFind;
import fr.B4D.transport.B4DWrongPosition;

public final class Deplacement {
	public static ProgramInterface deplacement = new ProgramInterface() {

		public void run() throws AWTException, B4DCannotFind, B4DWrongPosition {
			B4D.world.goTo(new Point(4, -18));
			B4D.world.goTo(new Point(3, -19));
		}
	};
}
