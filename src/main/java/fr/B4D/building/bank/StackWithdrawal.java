package fr.B4D.building.bank;

import java.awt.event.KeyEvent;

import fr.B4D.bot.B4D;
import fr.B4D.dofus.items.Stack;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

public class StackWithdrawal extends BankAction{

	private Stack stack;
	
	public StackWithdrawal(Stack stack) {
		super();
		
		if(stack == null)
			throw new IllegalArgumentException("The withdrawal stack cannot be null.");
		
		this.stack = stack;
	}
	
	/** Returns the stacks to withdraw.
	 * @return List of stacks to withdraw.
	 */
	public Stack getStack() {
		return stack;
	}

	@Override
	public void doAction() throws StopProgramException, CancelProgramException {

		if(stack.getAmount() > 0 || stack.getAmount() == -1) {							//If something to transfer
			B4D.mouse.leftClick(new PointF(0.2664, 0.7894), false);							//Clear research field
			B4D.mouse.leftClick(new PointF(0.1544, 0.7904), false);							//Click research field
			B4D.keyboard.writeKeyboard(stack.getItem().getName());							//Type item's name
			B4D.mouse.dragDrop(new PointF(0.0512, 0.2016), new PointF(0.7624, 0.2006));		//Drag and drop
			if(stack.getAmount() > 0)														//If not all the stack
				B4D.keyboard.writeKeyboard(String.valueOf(stack.getAmount()));					//Type the amount
			B4D.keyboard.sendKey(KeyEvent.VK_ENTER);										//Enter
		}
	}
}
