package fr.B4D.program;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.security.GeneralSecurityException;

import fr.B4D.bot.Person;
import fr.B4D.dofus.CannotFindException;
import fr.B4D.transport.WrongPositionException;
import net.sourceforge.tess4j.TesseractException;

public interface ProgramInterface{
	public void intro(Person person) throws StopProgramException, CancelProgramException, IOException, GeneralSecurityException;
	public void cycle(Person person) throws StopProgramException, CancelProgramException, FullInventoryException, AWTException, CannotFindException, WrongPositionException, UnsupportedFlavorException, IOException, TesseractException;
	public void outro(Person person) throws CancelProgramException;
}
