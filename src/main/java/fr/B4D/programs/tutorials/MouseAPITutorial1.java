package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.utils.PointF;

/**
 * The {@code MouseAPI} class contains all the tutorials relative to the mouse API.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Left clique on the chat bar and waits 500 ms</li>
 *     <li>Maj + Left click on the game frame and waits 1000 ms</li>
 *     <li>Right click on the game frame and waits 700 ms</li>
 *     <li>Move the mouse in the middle of the game frame and waits 1500 ms</li>
 * </ul>
 *
 * @author Lucas
 *
 */
public final class MouseAPITutorial1 extends Program {

	/**
	 * Constructor of the mouse API tutorial.
	 */
	public MouseAPITutorial1() {
		super(Place.Tous, Category.Tutorial, "Souris API", "Tutorial 1", null, null);
	}
	
	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws B4DException {
		B4D.mouse.chatClick();
		B4D.mouse.leftClick(new PointF(0.5, 0.4), true);
		B4D.mouse.rightClick(new PointF(0.5, 0.6), false, 700);
		B4D.mouse.place(new PointF(0.5, 0.5), 1500);
	}
}
