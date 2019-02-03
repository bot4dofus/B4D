package fr.B4D.programs.tutorials;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.interaction.B4DExchangeCanceled;
import fr.B4D.interaction.Exchange;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.program.Status;
import fr.B4D.program.StopProgramException;
import fr.B4D.transport.B4DWrongPosition;
import net.sourceforge.tess4j.TesseractException;

public final class ExchangeAPI {	

	public final static Program TUTORIAL1 = new Program(Place.Aucun, Category.Tutorial, "Exchange API", "Tutorial 1", null, Status.AVAILABLE, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException, StopProgramException, NumberFormatException, CancelProgramException {
			Exchange exchange = new Exchange(100000,0);
			String name = exchange.waitForExchange();
			try {
				Image proof = exchange.exchange("T'es sur de ce don ?");
				B4D.logger.popUp("Echange avec " + name + " effectué. Voici la preuve " + proof);
			} catch (B4DExchangeCanceled e) {
				B4D.logger.popUp("L'échange à été annulé");
			}
		}
	});
}
