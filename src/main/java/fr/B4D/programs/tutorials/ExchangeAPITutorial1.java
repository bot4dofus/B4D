package fr.B4D.programs.tutorials;

import java.awt.Image;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.interaction.Exchange;
import fr.B4D.interaction.ExchangeCanceledException;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.StopProgramException;

/**
 * The {@code ExchangeAPI} program is a tutorial to better understand exchange API.<br>
 * <br><br>
 * This tutorial waits for player to exchange 100 000 kamas.
 * If you want to make an item exchange, see {@link ExchangeAPITutorial2}
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Creates an exchange of 100 000 kamas input and 0 kamas output</li>
 *     <li>Waits for player to request an exchange</li>
 *     <li>Proceed to the exchange with a display message to confirm</li>
 *     <li>Displays the name of the player we performed the exchange with and the proof</li>
 * </ul>
 * In the case where the exchange is canceled a message is displayed.<br>
 *
 * @author Lucas
 *
 */
public final class ExchangeAPITutorial1 extends Program {	

	/**
	 * Constructor of the exchange API tutorial.
	 */
	public ExchangeAPITutorial1() {
		super(Place.Tous, Category.Tutorial, "Exchange API", "Tutorial 1", new Channel[] {Channel.GENERAL, Channel.PRIVATE}, Status.AVAILABLE);
	}
	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws StopProgramException, CancelProgramException, B4DException {
		Exchange exchange = new Exchange(100000,0);
		String name = exchange.waitForExchange();
		try {
			Image proof = exchange.exchange("T'es sur de ce don ?");
			B4D.logger.popUp("Echange avec " + name + " effectué. Voici la preuve " + proof);
		} catch (ExchangeCanceledException e) {
			B4D.logger.popUp("L'échange à été annulé");
		}
	}
}
