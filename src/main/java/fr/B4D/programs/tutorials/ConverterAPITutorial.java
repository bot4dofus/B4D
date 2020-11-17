package fr.B4D.programs.tutorials;

import java.awt.Point;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;

/**
 * The {@code ConverterAPI} class contains all the tutorials relative to the converter API.
 * 
 * Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des converssions.<br>
 *  <br>
 *  Fonctionnement :
 *  <ul>
 *  	<li>Créer un point dans le système de cordonnée de la fenêtre de jeu.</li>
 *  	<li>Converti ce point dans le système de coordonnnée de l'écran.</li>
 *  	<li>Converti ce point dans le système de coordonnnée du damier de dofus.</li>
 *  	<li>Affiche les points.</li>
 *  </ul>
 *
 * @author Lucas
 *
 */
public final class ConverterAPITutorial extends Program{	

	/**
	 * Constructor of the converter API tutorial.
	 */
	public ConverterAPITutorial() {
		super(Place.Tous, Category.Tutorial, "Converter API", "Tutorial 1", null, null);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
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
}
