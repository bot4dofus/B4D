package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.StopProgramException;

/**
 * The {@code MessageAPI} class contains all the tutorials relative to the message API.
 * <br><br>
 * This tutorial focus on message reception.
 * If you want to send private messages, see {@link MessageAPITutorial2}.
 * If you want to use chat filters and a chat listener, see {@link MessageAPITutorial3}.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Waits for a message in the chat</li>
 *     <li>Displays the message in a popup window</li>
 * </ul>
 *
 * @author Lucas
 *
 */
public final class MessageAPITutorial1 extends Program {

	/**
	 * Constructor of the message API tutorial 1.
	 */
	public MessageAPITutorial1() {
		super(Place.Tous, Category.Tutorial, "Message API", "Tutorial 1", null, null);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws StopProgramException, CancelProgramException {
		Message message = Dofus.getInstance().getChat().waitForMessage(0);
		B4D.logger.popUp("Message de " + message.getPseudo() + "(" + message.getChannel() + ") : " + message.getText());
	}
}
