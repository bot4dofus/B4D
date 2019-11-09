package fr.B4D.programs.tutorials;

import java.awt.Image;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.interaction.Exchange;
import fr.B4D.interaction.ExchangeCanceledException;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.program.StopProgramException;

public final class ExchangeAPI {	

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API des échanges entre joueur.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Création de l'objet échange avec 100 000 kamas entrants, 0 kamas sortants.</li>
	 *  	<li>Attente d'une demande d'échange.</li>
	 *  	<li>Procède à l'échange avec un message de confirmation auquel la personne peut répondre uniquement par "oui" ou "non".</li>
	 *  	<li>Affiche le nom du joueur qui vient de réaliser l'échange ainsi que la preuve de son consentement.</li>
	 *  </ul>
	 *  Dans le cas où l'échange est annulé par le joueur, un exception est levée. Un message différent est alors affiché.<br>
	 */
	public final static Program TUTORIAL1 = new Program(Place.Tous, Category.Tutorial, "Exchange API", "Tutorial 1", new Channel[] {Channel.GENERAL, Channel.PRIVATE}, Status.AVAILABLE, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws StopProgramException, CancelProgramException, B4DException {
			Exchange exchange = new Exchange(100000,0);
			String name = exchange.waitForExchange();
			try {
				Image proof = exchange.exchange("T'es sur de ce don ?");
				B4D.logger.popUp("Echange avec " + name + " effectué. Voici la preuve " + proof);
			} catch (ExchangeCanceledException e) {
				B4D.logger.popUp("L'échange à été annulé");
			}
		}
	});
}
