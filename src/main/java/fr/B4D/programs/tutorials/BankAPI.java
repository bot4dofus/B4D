package fr.B4D.programs.tutorials;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.bank.Bank;
import fr.B4D.building.bank.BankAction;
import fr.B4D.building.bank.MoneyDeposit;
import fr.B4D.building.bank.MoneyWithdrawal;
import fr.B4D.building.bank.StackDeposit;
import fr.B4D.dofus.Dofus;
import fr.B4D.dofus.items.Item;
import fr.B4D.dofus.items.Stack;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;

/**
 * The {@code BankAPI} class contains all the tutorials relative to the bank API.
 * 
 * @author Lucas
 *
 */
public final class BankAPI {	

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des banques.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Dépose 100 kamas</li>
	 *  	<li>Dépose tous les kamas</li>
	 *  	<li>Prend 100 kamas</li>
	 *  	<li>Prend tous les kamas</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL1 = new Program(Place.Tous, Category.Tutorial, "Banque API", "Tutorial 1", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws B4DException {
			
			List<BankAction> bankAction = new ArrayList<BankAction>();
			
			bankAction.add(new MoneyDeposit(100));
			bankAction.add(MoneyDeposit.ALL);
			bankAction.add(new MoneyWithdrawal(100));
			bankAction.add(MoneyWithdrawal.ALL);
			
			Bank.ASTRUB.doActions(person, bankAction);
		}
	});
	
	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des banques.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Demande un item à déposer</li>
	 *  	<li>Dépose 1 item</li>
	 *  	<li>Dépose tous le stack d'item</li>
	 *  	<li>Dépose tous les items</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL2 = new Program(Place.Tous, Category.Tutorial, "Banque API", "Tutorial 2", null, null, new ProgramInterface() {
		private Item item;
		
		public void intro(Person person) throws CancelProgramException, B4DException {

			JTextField itemField = new JTextField();
			Object[] message = {
					"Saisir le nom d'un item à déposer en banque.",
					"Nom :", itemField,
			};
			int option = JOptionPane.showConfirmDialog(null, message, "Tutorial 2", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.CANCEL_OPTION)
				throw new CancelProgramException("Vous avez annulé le programme.");
			
			List<Item> items = Dofus.getInstance().getDatabase().findItemsByName(itemField.getText());
			if(items.isEmpty())
				throw new CancelProgramException("Aucun item à ce nom n'a été trouvé.");
			
			item = items.get(0);
		}
		public void cycle(Person person) throws B4DException {
			
			List<BankAction> bankAction = new ArrayList<BankAction>();
			
			bankAction.add(new StackDeposit(new Stack(item, 1)));
			bankAction.add(new StackDeposit(new Stack(item, -1)));
			bankAction.add(StackDeposit.ALL);
			
			Bank.ASTRUB.doActions(person, bankAction);
		}
		public void outro(Person person) {}
	});
}
