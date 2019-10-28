package fr.B4D.programs.tutorials;

import java.awt.Point;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;

public final class ConverterAPI {	

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des converssions.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Créer un point dans le système de cordonnée de la fenêtre de jeu.</li>
	 *  	<li>Converti ce point dans le système de coordonnnée de l'écran.</li>
	 *  	<li>Converti ce point dans le système de coordonnnée du damier de dofus.</li>
	 *  	<li>Affiche les points.</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL1 = new Program(Place.Tous, Category.Tutorial, "Converter API", "Tutorial 1", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) {			
			PointF middleDofusFrame = new PointF(0.5, 0.5);
			Point middleScreen = B4D.converter.toPoint(middleDofusFrame);
			PointD middleDofusDraughtboard = B4D.converter.toPointD(middleDofusFrame);
			B4D.logger.popUp(
						"Ces points sont tous identiques mais ont des coordonnées différentes en fonction du plan dans le quel ils sont :" +
						"- Plan de la fenêtre de jeu : " + middleDofusFrame.toString() +
						"- Plan de l'écran : " + middleScreen.toString() +
						"- Plan du damier du jeu : " + middleDofusDraughtboard.toString()
					);
		}
	});
}
