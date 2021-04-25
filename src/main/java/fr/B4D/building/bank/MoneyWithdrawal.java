package fr.B4D.building.bank;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/**
 * The class {@code MoneyWithdrawal} represents a money withdrawal action on a bank.<br><br>
 * This class extends {@code BankAction}.
 * A money withdrawal has an amount.
 * 
 * @author Lucas
 *
 */
public class MoneyWithdrawal extends BankAction{

	/**
	 * Withdraw all the money the player has in the bank.
	 */
	public static final MoneyWithdrawal ALL = new MoneyWithdrawal(-1);
	
	private Integer amount;

	/**
	 * Constructs a {@code MoneyWithdrawal} with a specific amount.
	 * @param amount - Number of kamas to retrieve from the account, -1 to retrieve all.
	 */
	public MoneyWithdrawal(Integer amount) {
		super();
		
		if(amount == null)
			throw new IllegalArgumentException("The withdrawal amount cannot be null.");
		
		this.amount = amount;
	}

	/**
	 * Returns the amount of the withdrawal.
	 * @return Integer representing the amount.
	 */
	public Integer getAmount() {
		return amount;
	}
	
	@Override
	public void doAction() throws StopProgramException, CancelProgramException, B4DException {
		if(amount > 0 || amount == -1) {
			B4D.mouse.leftClick(new PointF(0.2456, 0.8214), false);
			if(amount > 0)
				B4D.keyboard.writeKeyboard(String.valueOf(amount));
			B4D.mouse.leftClick(new PointF(0.352, 0.7705), false);
		}
	}
}
