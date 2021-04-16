package fr.B4D.programs.tutorials;

import java.awt.Color;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.utils.PointF;

/**
 * The {@code ScreenAPI} class contains all the tutorials relative to the screen API.
 * <br><br>
 * This tutorial focus on pixel analysis.
 * If you want to perform an OCR, see {@link ScreenAPITutorial2}.
 * <br><br>
 * Steps :
 * <ul>
 *    <li>Get the color of a pixel and displays it</li>
 *    <li>Waits for this pixel to change color and displays the new color</li>
 * </ul>
 *
 * @author Lucas
 *
 */
public final class ScreenAPITutorial1 extends Program {	

	/**
	 * Constructor of the screen API tutorial 1.
	 */
	public ScreenAPITutorial1() {
		super(Place.Tous, Category.Tutorial, "Screen API", "Tutorial 1", null, null);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}
	
	@Override
	public void cycle(Person person) throws B4DException {
		Color oldColor = B4D.screen.getPixelColor(new PointF(0.5, 0.5));
		B4D.logger.popUp("La couleur actuelle est : " + oldColor.toString());
		Color newColor = B4D.screen.waitForChangingPixel(new PointF(0.5, 0.5), 10000);
		if(newColor != null)
			B4D.logger.popUp("La nouvelle couleur est : " + newColor.toString());
		else
			B4D.logger.popUp("Timeout : Le pixel n'a pas changé.");
	}
}
