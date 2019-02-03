package fr.B4D.interaction.chat;

import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

public interface ChatListener {
	public void treatMessage(Message message) throws StopProgramException, CancelProgramException;
}
