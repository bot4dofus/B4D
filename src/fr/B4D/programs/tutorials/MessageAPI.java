package fr.B4D.programs.tutorials;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.ChatListener;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.program.Status;
import fr.B4D.program.StopProgramException;
import fr.B4D.transport.B4DWrongPosition;
import net.sourceforge.tess4j.TesseractException;

public final class MessageAPI {	
	
	public final static Program TUTORIAL1 = new Program(Place.Aucun, Category.Tutorial, "Message API", "Tutorial 1", null, Status.AVAILABLE, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException {
			Message message = Dofus.chat.waitForMessage(0);
			B4D.logger.popUp("Message de " + message.getPseudo() + "(" + message.getChannel() + ") : " + message.getText());
		}
	});
	

	public final static Program TUTORIAL2 = new Program(Place.Aucun, Category.Tutorial, "Message API", "Tutorial 2", null, Status.AVAILABLE, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException, StopProgramException, CancelProgramException {
			Message message = new Message("Solwy", Channel.PRIVATE, "Salut !");
			message.send();
			Dofus.chat.addPseudoFilter("Solwy");
			message = Dofus.chat.waitForMessage(60000);
			if(message != null)
				message.reply("ça va ?");
			else	
				B4D.logger.popUp("Le temps d'attente de 1 min est dépassé.");				
		}
	});

	public final static Program TUTORIAL3 = new Program(Place.Aucun, Category.Tutorial, "Message API", "Tutorial 3", null, Status.AVAILABLE, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException, StopProgramException, CancelProgramException {
			Dofus.chat.addChannelFilter(Channel.BUSINESS);
			Dofus.chat.addTextFilter("moi");
			Dofus.chat.addChatListener(new ChatListener() {
				public void treatMessage(Message message) throws StopProgramException, CancelProgramException {
					message.reply("C'est qui moi ?");
				}
			});
			Dofus.chat.read(3);
		}
	});
}
