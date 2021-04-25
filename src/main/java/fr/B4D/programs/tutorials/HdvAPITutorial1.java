package fr.B4D.programs.tutorials;

import javax.swing.JOptionPane;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.socket.event.HDVItemViewEvent;
import fr.B4D.socket.store.EventStore;

/**
 * The {@code HdvAPI} program is a tutorial to better understand the HDV API.
 * <br><br>
 * This tutorial focus on retrieving item informations.
 * If you want to buy items, see {@link HdvAPITutorial2}.
 * If you want to sell items, see {@link HdvAPITutorial3}.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Waits for the player to select an item in the HDV list</li>
 *     <li>Displays the item informations</li>
 * </ul>
 * 
 * @author Lucas
 *
 */
public final class HdvAPITutorial1 extends Program {	

	/**
	 * Constructor of the Hdv API tutorial.
	 */
	public HdvAPITutorial1() {
		super(Place.Tous, Category.Tutorial, "Hdv API", "Tutorial 1", null, null);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws B4DException {
		HDVItemViewEvent result = EventStore.getInstance().waitForEvent(HDVItemViewEvent.class, 10000);
		if(result != null)
			JOptionPane.showMessageDialog(null, "Voici le prix de l'item affiché :\n- Lot de 1 : " + result.getPrice1() + " k\n- Lot de 10 : " + result.getPrice10() + " k\n- Lot de 100 : " + result.getPrice100() + " k", "Prix", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "Vous n'avez pas affiché d'item dans le temps imparti.", "Timeout", JOptionPane.WARNING_MESSAGE);
	}
}
