package fr.B4D.programs.tutorials;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import fr.B4D.bot.Person;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.interaction.B4DExchangeCanceled;
import fr.B4D.interaction.Exchange;
import fr.B4D.program.ProgramInterface;
import fr.B4D.transport.B4DWrongPosition;
import net.sourceforge.tess4j.TesseractException;

public final class ExchangeAPI {	

	public static ProgramInterface tutorial1 = new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException {
			Exchange exchange = new Exchange(2,1);
			String name = exchange.waitForExchange();
			try {
				Image proof = exchange.exchange("T'es sur ?");
			} catch (B4DExchangeCanceled e) {
				//Do nothing
			}
		}
	};
	
	public static ProgramInterface tutorial2 = new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException {
			
		}
	};
	
	public static ProgramInterface tutorial3 = new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException {
			
		}
	};
}
