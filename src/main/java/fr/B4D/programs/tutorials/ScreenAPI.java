package fr.B4D.programs.tutorials;

import java.awt.Color;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.utils.PointF;

public final class ScreenAPI {	

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API de l'écran.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Récupère la couleur d'un pixel et l'affiche.</li>
	 *  	<li>Attend que ce pixel change et affiche la nouvelle couleur.</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL1 = new Program(Place.Aucun, Category.Tutorial, "Screen API", "Tutorial 1", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws B4DException {
			Color oldColor = B4D.screen.getPixelColor(new PointF(0.5, 0.5));
			B4D.logger.popUp("La couleur actuelle est : " + oldColor.toString());
			Color newColor = B4D.screen.waitForChangingPixel(new PointF(0.5, 0.5), 10000);
			if(newColor != null)
				B4D.logger.popUp("La nouvelle couleur est : " + newColor.toString());
			else
				B4D.logger.popUp("Timeout : Le pixel n'a pas changé.");
		}
	});
	
	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API de l'écran.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Attend que "hello world" soit affiché à l'écran.</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL2 = new Program(Place.Aucun, Category.Tutorial, "Screen API", "Tutorial 2", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws B4DException {
			B4D.screen.waitForOCR(new PointF(0.4, 0.4), new PointF(0.6, 0.6), "hello world", 10000);
		}
	});
}
