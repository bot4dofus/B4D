package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.ChatListener;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.StopProgramException;

/**
 * The {@code MessageAPI} class contains all the tutorials relative to the message API.
 * <br><br>
 * This tutorial focus on message filters and chat listener.
 * If you want to read messages, see {@link MessageAPITutorial1}.
 * If you want to send private messages, see {@link MessageAPITutorial2}.
 * <br><br>
 * Steps :
 * <ul>
 *     <li>Add a channel filter : Only the commercial messages will be processed</li>
 *     <li>Add regex filter : Only the messages containing "moi" will be processed</li>
 *     <li>Add a chat listener : Specify what to do when a message matches the filter criteria</li>
 *     <li>Wait for 3 messages to be processed</li>
 * </ul>
 *
 * @author Lucas
 *
 */
public final class MessageAPITutorial3 extends Program {	

	/**
	 * Constructor of the message API tutorial 3.
	 */
	public MessageAPITutorial3() {
		super(Place.Tous, Category.Tutorial, "Message API", "Tutorial 3", new Channel[] {Channel.BUSINESS, Channel.PRIVATE, Channel.GENERAL}, Status.AVAILABLE);
	}

	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
	public void cycle(Person person) throws StopProgramException, CancelProgramException, B4DException {
		Dofus.getInstance().getChat().addChannelFilter(Channel.BUSINESS);
		Dofus.getInstance().getChat().addTextFilter("moi");
		Dofus.getInstance().getChat().setChatListener(new ChatListener() {
			public void messageReceived(Message message) throws StopProgramException, CancelProgramException, B4DException {
				message.reply("Salut !");
			}
		});
		Dofus.getInstance().getChat().read(3);
	}
}
