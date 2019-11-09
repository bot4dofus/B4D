package fr.B4D.programs.tutorials;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;

public final class KeyboardAPI {	

	/** Ce tutoriel à pour objectif de mieux comprendre le fonctionnement et l'utilisation de l'API du clavier.<br>
	 *  <br>
	 *  Fonctionnement :
	 *  <ul>
	 *  	<li>Attend l'appui sur une touche.</li>
	 *  	<li>Ecrit le numéro de la touche dans le chat.</li>
	 *  	<li>Copie un texte dans le presse-papier.</li>
	 *  	<li>Récupère le texte dans le presse-papier.</li>
	 *  	<li>Affiche le texte.</li>
	 *  </ul>
	 */
	public final static Program TUTORIAL1 = new Program(Place.Tous, Category.Tutorial, "Clavier API", "Tutorial 1", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws B4DException {
			int id = B4D.keyboard.waitForKeyboard(5000);
			B4D.keyboard.writeKeyboard("Touche " + id + " enfoncée.");
			B4D.keyboard.setClipboard("Ce texte à été copié.");
			String texte = B4D.keyboard.getClipboard();
			B4D.logger.popUp("Texte lu depuis le press papier : " + texte);
		}
	});
}
