package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;

/**
 * The {@code KeyboardAPI} program is a tutorial to better understand the keyboard API.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Wait for a key press</li>
 *     <li>Write the id of the key in the chat</li>
 *     <li>Copy a text in the clipboard</li>
 *     <li>Retrieve the text from the clipboard</li>
 *     <li>Displays the text</li>
 * </ul>
 *
 * @author Lucas
 *
 */
public final class KeyboardAPITutorial1 extends Program {	

	/**
	 * Constructor of the keyboard API tutorial.
	 */
	public KeyboardAPITutorial1() {
		super(Place.Tous, Category.Tutorial, "Clavier API", "Tutorial 1", null, null);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws B4DException {
		int id = B4D.keyboard.waitForKeyboard(10000);
		if(id != -1) {
			B4D.keyboard.writeKeyboard("Touche " + id + " enfoncée.");
			B4D.keyboard.setClipboard("Ce texte à été copié.");
			String texte = B4D.keyboard.getClipboard();
			B4D.logger.popUp("Texte lu depuis le press papier : " + texte);
		}
		else {
			B4D.logger.popUp("Temps d'attente dépassé.");
		}
	}
}
