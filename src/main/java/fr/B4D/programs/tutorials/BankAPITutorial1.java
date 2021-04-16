package fr.B4D.programs.tutorials;

import java.util.ArrayList;
import java.util.List;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.bank.Bank;
import fr.B4D.building.bank.BankAction;
import fr.B4D.building.bank.MoneyDeposit;
import fr.B4D.building.bank.MoneyWithdrawal;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;

/**
 * The {@code BankAPITutorial1} program is a tutorial to better understand the Bank API.
 * <br><br>
 * This tutorial focus on money deposits and withdrawals.
 * If you want to perform item deposits and withdrawals, see {@link BankAPITutorial2}.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>100 kamas deposit</li>
 *     <li>Deposit of all the kamas</li>
 *     <li>100 kamas withdrawal</li>
 *     <li>Withdrawal of all the kamas</li>
 * </ul>
 *
 * @author Lucas
 *
 */
public final class BankAPITutorial1 extends Program{	

	/**
	 * Constructor of the bank API tutorial 1.
	 */
	public BankAPITutorial1() {
		super(Place.Tous, Category.Tutorial, "Banque API", "Tutorial 1", null, null);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}
	
	@Override
	public void cycle(Person person) throws B4DException {

		List<BankAction> bankAction = new ArrayList<BankAction>();

		bankAction.add(new MoneyDeposit(100));
		bankAction.add(MoneyDeposit.ALL);
		bankAction.add(new MoneyWithdrawal(100));
		bankAction.add(MoneyWithdrawal.ALL);

		Bank.ASTRUB.doActions(person, bankAction);
	}
}
