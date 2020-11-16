package fr.B4D.programs.tutorials;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.B4D.bot.B4D;
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
import fr.B4D.program.ProgramInterface;
import fr.B4D.program.StopProgramException;

/**
 * The {@code MessageAPI} class contains all the tutorials relative to the message API.
 * 
 * @author Lucas
 *
 */
public final class MessageAPI {	
	
	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des messages entre joueurs.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Attente d'un message quelconque dans le chat.</li>
	 *  	<li>Affiche le message reçu ainsi que l'auteur et le canal.</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL1 = new Program(Place.Tous, Category.Tutorial, "Message API", "Tutorial 1", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws StopProgramException, CancelProgramException {
			Message message = Dofus.getInstance().getChat().waitForMessage(0);
			B4D.logger.popUp("Message de " + message.getPseudo() + "(" + message.getChannel() + ") : " + message.getText());
		}
	});

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des échanges entre joueurs.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Envoi d'un message privé à "Solwy".</li>
	 *  	<li>Envoi du message.</li>
	 *  	<li>Attente de la réponse du joueur.</li>
	 *  	<li>Répond "ça va ?" si le joueur à répondu. Affiche un message si il n'a pas répondu après 1 min = 60000 ms.</li>
	 *  </ul>
	 *  Dans le cas où l'échange est annulé par le joueur, un exception est levée. Un message différent est alors affiché.<br>
	 */
	public final static Program TUTORIAL2 = new Program(Place.Tous, Category.Tutorial, "Message API", "Tutorial 2", new Channel[] {Channel.PRIVATE, Channel.GENERAL}, Status.AVAILABLE, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws StopProgramException, CancelProgramException {
			JTextField nameField = new JTextField();
			Object[] field = {
					"Destinataire :", nameField,
			};
			int option = JOptionPane.showConfirmDialog(null, field, "Envoi d'un message privé :", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.CANCEL_OPTION)
				throw new CancelProgramException("Vous avez annulé le programme.");
			
			String name = nameField.getText();
			Message message = new Message(name, "Salut !");
			message.send();
			message = message.waitForReply(60000);
			if(message != null)
				message.reply("ça va ?");
			else	
				B4D.logger.popUp("Le temps d'attente de 1 min est dépassé.");				
		}
	});

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des échanges entre joueurs.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Ajout d'un filtre canal : Seul les messages du canal commerce seront traités.</li>
	 *  	<li>Ajout d'un filtre texte : Seul les messages contenant "moi" seront traités.</li>
	 *  	<li>Ajout d'une opération à éffectuer pour chaque message correspondant au filtre.</li>
	 *  	<li>Débute la lecture du chat en précisant à 3 le nombre de messages qui seront traités.</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL3 = new Program(Place.Tous, Category.Tutorial, "Message API", "Tutorial 3", new Channel[] {Channel.BUSINESS, Channel.PRIVATE, Channel.GENERAL}, Status.AVAILABLE, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
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
	});
}
