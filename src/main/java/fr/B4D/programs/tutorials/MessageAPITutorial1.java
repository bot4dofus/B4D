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
 * Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des messages entre joueurs.<br>
 *  <br>
 *  Fonctionnement :
 *  <ul>
 *  	<li>Attente d'un message quelconque dans le chat.</li>
 *  	<li>Affiche le message reçu ainsi que l'auteur et le canal.</li>
 *  </ul>
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
