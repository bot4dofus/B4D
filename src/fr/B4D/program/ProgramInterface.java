package fr.B4D.program;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import fr.B4D.bot.Person;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.transport.B4DWrongPosition;
import net.sourceforge.tess4j.TesseractException;

public interface ProgramInterface{
	public void run(Person person) throws B4DFullInventory, AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException ;
}
