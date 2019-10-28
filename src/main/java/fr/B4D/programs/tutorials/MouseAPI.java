package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.utils.PointF;

public final class MouseAPI {	

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API de la souris.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Clic sur la barre de chat et attend 500 millisecondes.</li>
	 *  	<li>Clic gauche avec appui sur Maj et attend 1 seconde.</li>
	 *  	<li>Clic droit sans appui sur Maj et attend 700 millisecondes.</li>
	 *  	<li>Place la souris au milieu et attend 1.5 secondes.</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL1 = new Program(Place.Aucun, Category.Tutorial, "Souris API", "Tutorial 1", new Channel[] {Channel.PRIVATE}, Status.AVAILABLE, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws B4DException {
			B4D.mouse.chatClick();
			B4D.mouse.leftClick(new PointF(0.5, 0.4), true);
			B4D.mouse.rightClick(new PointF(0.5, 0.6), false, 700);
			B4D.mouse.place(new PointF(0.5, 0.5), 1500);
		}
	});
}
