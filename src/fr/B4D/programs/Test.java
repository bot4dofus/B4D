package fr.B4D.programs;

import java.awt.Point;

import fr.B4D.classes.Bot;
import fr.B4D.classes.ProgramInterface;

public final class Test {
	public static ProgramInterface test = new ProgramInterface() {
		public void run() {
			Bot.configuration.persons.get(0).position = new Point(0, 0);
			try {
				Bot.world.goTo(new Point(-30,-57));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}
