package fr.B4D.programs.tutorials;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
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
 * If you want to read messages, see {@link MessageAPITutorial1}.
 * If you want to use chat filters and a chat listener, see {@link MessageAPITutorial3}.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Ask for a player to which send a private message</li>
 *     <li>Send a private message</li>
 *     <li>Wait for the answer</li>
 *     <li>Answer "ça va ?" if the player has answer. Displays a popup message if he hasn't answer after 60 secondes</li>
 *  </ul>
 *  
 * @author Lucas
 *
 */
public final class MessageAPITutorial2 extends Program {

	/**
	 * Constructor of the message API tutorial 2.
	 */
	public MessageAPITutorial2() {
		super(Place.Tous, Category.Tutorial, "Message API", "Tutorial 2", new Channel[] {Channel.PRIVATE, Channel.GENERAL}, Status.AVAILABLE);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws StopProgramException, CancelProgramException, B4DException {
		JTextField nameField = new JTextField();
		Object[] field = {
				"Destinataire :", nameField,
		};
		int option = JOptionPane.showConfirmDialog(null, field, "Envoi d'un message privé :", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.CANCEL_OPTION)
			throw new CancelProgramException("Vous avez annulé le programme.");

		String name = nameField.getText();
		Message message = new Message(name, "Salut !");
		message.send();
		message = message.waitForReply(60000);
		if(message != null)
			message.reply("ça va ?");
		else	
			B4D.logger.popUp("Le temps d'attente de 1 min est dépassé.");				
	}
}
