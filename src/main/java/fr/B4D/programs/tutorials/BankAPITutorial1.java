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
 * The {@code BankAPI} class contains all the tutorials relative to the bank API.
 * 
 * Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des banques.<br>
 *  <br>
 *  Fonctionnement :
 *  <ul>
 *  	<li>Dépose 100 kamas</li>
 *  	<li>Dépose tous les kamas</li>
 *  	<li>Prend 100 kamas</li>
 *  	<li>Prend tous les kamas</li>
 *  </ul>
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
