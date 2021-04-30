package fr.B4D.programs.tutorials;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.bank.Bank;
import fr.B4D.building.bank.BankAction;
import fr.B4D.building.bank.StackDeposit;
import fr.B4D.building.bank.StackWithdrawal;
import fr.B4D.dofus.Dofus;
import fr.B4D.dofus.items.Item;
import fr.B4D.dofus.items.Stack;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;

/**
 * The {@code BankAPITutorial2} program is a tutorial to better understand the Bank API.
 * <br><br>
 * This tutorial focus on item deposits and withdrawals.
 * If you want to perform money deposits and withdrawals, see {@link BankAPITutorial1}.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Requests an object to deposit</li>
 *     <li>Deposit one item</li>
 *     <li>Deposit the item stack</li>
 *     <li>Deposit all the items</li>
 *     <li>Withdraw one item</li>
 *     <li>Withdraw the item stack</li>
 *     <li>Withdraw all the items</li>
 * </ul>
 *
 * @author Lucas
 *
 */
public final class BankAPITutorial2 extends Program{	

	private Item item;

	/**
	 * Constructor of the bank API tutorial 2.
	 */
	public BankAPITutorial2() {
		super(Place.Tous, Category.Tutorial, "Banque API", "Tutorial 2", null, null);
	}

	@Override
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

	@Override
	public void cycle(Person person) throws B4DException {

		List<BankAction> bankAction = new ArrayList<BankAction>();

		bankAction.add(new StackDeposit(new Stack(item, 1)));
		bankAction.add(new StackDeposit(new Stack(item, -1)));
		bankAction.add(StackDeposit.ALL);
		bankAction.add(new StackWithdrawal(new Stack(item, 1)));
		bankAction.add(new StackWithdrawal(new Stack(item, -1)));
		bankAction.add(StackWithdrawal.ALL);

		Bank.ASTRUB.doActions(person, bankAction);
	}
	
	@Override
	public void outro(Person person) {}
}
