package fr.B4D.programs;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.StopProgramException;
import fr.B4D.socket.event.PlayerEnterMapEvent;
import fr.B4D.socket.store.EventStore;

/**
 * The {@code Loto} class contains the loto program.
 * 
 * @author Lucas
 *
 */
public final class Spam extends Program{
	
	private String message;
	
	/**
	 * Constructor of the loto program.
	 */
	public Spam() {
		super(Place.Tous, Category.Autre, "Spam", "Spam", null, null);
	}

	@Override
	public void intro(Person person) throws CancelProgramException {	

		JTextField messageField = new JTextField();
		Object[] objects = {
				"Message :", messageField
		};
		int option = JOptionPane.showConfirmDialog(null, objects, "Merci de renseigner les champs suivants :", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.CANCEL_OPTION)
			throw new CancelProgramException("Vous avez annulé le programme.");

		message = messageField.getText();

		if(message == null)
			throw new CancelProgramException("Tous les champs doivent être remplis.");
	}

	@Override
	public void cycle(Person person) throws StopProgramException, NumberFormatException, CancelProgramException, B4DException {
		PlayerEnterMapEvent event = EventStore.getInstance().waitForEvent(PlayerEnterMapEvent.class, 60000);
		if(event != null) {
			Message m = new Message(event.getPseudo(), message);
			m.send();
		}
	}

	@Override
	public void outro(Person person) {
		//Nothing to do
	}
}
