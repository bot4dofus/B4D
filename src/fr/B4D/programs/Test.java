package fr.B4D.programs;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import fr.B4D.log.Logger;
import fr.B4D.program.ProgramInterface;
import fr.B4D.transport.B4DCannotFind;
import fr.B4D.transport.B4DWrongPosition;
import net.sourceforge.tess4j.TesseractException;

public final class Test {
	public static ProgramInterface test = new ProgramInterface() {

		public void run() throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException {
			Logger.popUp("Le programme de test marche correctement.");
		}
	};
}
