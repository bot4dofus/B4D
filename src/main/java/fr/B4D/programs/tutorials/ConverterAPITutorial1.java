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
 * The {@code ConverterAPI} program is a tutorial to better understand the converter API.
 * <br><br>
 * This tutorial converts a point from relative coordinates to simple and draughtboard coordiantes.
 * <br><br>
 * Step :
 * <ul>
 *     <li>Creates a point in the middle of the game frame in relative coordinates</li>
 *     <li>Converts it to simple coordinates</li>
 *     <li>Converts it to draughtboard coordinates</li>
 *     <li>Display the points</li>
 * </ul>
 *
 * @author Lucas
 *
 */
public final class ConverterAPITutorial1 extends Program{	

	/**
	 * Constructor of the converter API tutorial.
	 */
	public ConverterAPITutorial1() {
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
