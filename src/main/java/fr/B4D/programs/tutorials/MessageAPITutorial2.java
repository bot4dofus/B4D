package fr.B4D.programs.tutorials;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.B4D.bot.B4D;
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
 * Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des échanges entre joueurs.<br>
 *  <br>
 *  Fonctionnement :
 *  <ul>
 *  	<li>Envoi d'un message privé à "Solwy".</li>
 *  	<li>Envoi du message.</li>
 *  	<li>Attente de la réponse du joueur.</li>
 *  	<li>Répond "ça va ?" si le joueur à répondu. Affiche un message si il n'a pas répondu après 1 min = 60000 ms.</li>
 *  </ul>
 *  Dans le cas où l'échange est annulé par le joueur, un exception est levée. Un message différent est alors affiché.<br>
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
	public void cycle(Person person) throws StopProgramException, CancelProgramException {
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
