package fr.B4D.building.bank;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/**
 * The class {@code MoneyDeposit} represents a money deposit action on a bank.<br><br>
 * This class extends {@code BankAction}.
 * A money deposit has an amount.
 * 
 * @author Lucas
 *
 */
public class MoneyDeposit extends BankAction{

	/**
	 * Put all the money the player has in the bank.
	 */
	public static final  MoneyDeposit ALL = new MoneyDeposit(-1);
	
	private Integer amount;
	
	/**
	 * Constructs a {@code MoneyDeposit} with a specific amount.
	 * @param amount - Number of kamas to add to the account, -1 to add all.
	 */
	public MoneyDeposit(Integer amount) {
		super();
		
		if(amount == null)
			throw new IllegalArgumentException("The deposit amount cannot be null.");
		
		this.amount = amount;
	}

	/**
	 * Returns the amount of the deposit.
	 * @return Integer representing the amount.
	 */
	public Integer getAmount() {
		return amount;
	}

	@Override
	public void doAction() throws StopProgramException, CancelProgramException, B4DException {
		if(amount > 0 || amount == -1) {
			B4D.mouse.leftClick(new PointF(0.9456, 0.8234), false);
			if(amount > 0)
				B4D.keyboard.writeKeyboard(String.valueOf(amount));
			B4D.mouse.leftClick(new PointF(1.0504, 0.7745), false);
		}
	}
}
