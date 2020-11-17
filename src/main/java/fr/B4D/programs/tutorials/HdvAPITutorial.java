package fr.B4D.programs.tutorials;

import javax.swing.JOptionPane;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.socket.result.HDVItemViewSocketResult;
import fr.B4D.socket.store.HDVItemViewSocketStore;

/**
 * The {@code HdvAPI} class contains all the tutorials relative to the HDV API.
 * 
 * MISSING EXPLAINATIONS !!!!!!!!
 * 
 * @author Lucas
 *
 */
public final class HdvAPITutorial extends Program {	

	/**
	 * Constructor of the Hdv API tutorial.
	 */
	public HdvAPITutorial() {
		super(Place.Tous, Category.Tutorial, "Hdv API", "Tutorial 1", null, null);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws B4DException {
		HDVItemViewSocketResult result = HDVItemViewSocketStore.getInstance().waitForResult(10000);
		if(result != null)
			JOptionPane.showMessageDialog(null, "Voici le prix de l'item affiché :\n- Lot de 1 : " + result.getPrice1() + " k\n- Lot de 10 : " + result.getPrice10() + " k\n- Lot de 100 : " + result.getPrice100() + " k", "Prix", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "Vous n'avez pas affiché d'item dans le temps imparti.", "Timeout", JOptionPane.WARNING_MESSAGE);
	}
}
