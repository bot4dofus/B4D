package fr.B4D.dofus;

import fr.B4D.dofus.world.World;
import fr.B4D.dofus.world.WorldFactory;
import fr.B4D.interaction.chat.Chat;

/** La classe {@code Dofus} permet d'accéder au monde est au chat de dofus.
 */
public class Dofus {
	
	private static Dofus instance;
	
	private static Chat chat;
	private static World world;
	private static DofusDatabase database;
	
	  /*************/
	 /** BUILDER **/
	/*************/

	/** Constructeur de la classe {@code Dofus}.
	 */
	private Dofus() {
		chat = new Chat(100);
		world = WorldFactory.getMainWorld();
		database = new DofusDatabase("/fr/B4D/data/dofus.fr.json", true);
	}
	
	/** Retourne l'instance de la classe {@code Dofus}.
	 * @return Instance du jeu dofus.
	 */
	public static Dofus getInstance() {
		if(instance == null)
			instance = new Dofus();
		return instance;
	}

	/** Retourne le chat du jeu.
	 * @return Chat du jeu.
	 */
	public Chat getChat() {
		return chat;
	}

	/** Retourne le monde du jeu.
	 * @return Monde du jeu.
	 */
	public World getWorld() {
		return world;
	}
	
	/** Retourne à la base de donnée du jeu.
	 * @return Base de donnée du jeu.
	 */
	public DofusDatabase getDatabase() {
		return database;
	}
}
