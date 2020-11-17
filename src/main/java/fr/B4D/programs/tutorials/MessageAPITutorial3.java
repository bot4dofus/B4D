package fr.B4D.programs.tutorials;

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
 * Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des échanges entre joueurs.<br>
 *  <br>
 *  Fonctionnement :
 *  <ul>
 *  	<li>Ajout d'un filtre canal : Seul les messages du canal commerce seront traités.</li>
 *  	<li>Ajout d'un filtre texte : Seul les messages contenant "moi" seront traités.</li>
 *  	<li>Ajout d'une opération à éffectuer pour chaque message correspondant au filtre.</li>
 *  	<li>Débute la lecture du chat en précisant à 3 le nombre de messages qui seront traités.</li>
 *  </ul>
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
	public void cycle(Person person) throws StopProgramException, CancelProgramException {
		Dofus.getInstance().getChat().addChannelFilter(Channel.BUSINESS);
		Dofus.getInstance().getChat().addTextFilter("moi");
		Dofus.getInstance().getChat().setChatListener(new ChatListener() {
			public void treatMessage(Message message) throws StopProgramException, CancelProgramException {
				message.reply("C'est qui moi ?");
			}
		});
		Dofus.getInstance().getChat().read(3);
	}
}
