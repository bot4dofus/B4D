package fr.B4D.dofus;

import fr.B4D.dofus.world.World;
import fr.B4D.dofus.world.WorldFactory;
import fr.B4D.interaction.chat.Chat;

/**
 * The {@code Dofus} class is used to access to the Dofus world, chat and database.
 * 
 * @author Lucas
 *
 */
public class Dofus {
	
	/**
	 * Instance of the Dofus object.
	 */
	private static Dofus instance;
	
	/**
	 * Chat of the game.
	 */
	private static Chat chat;
	
	/**
	 * Dofus world.
	 */
	private static World world;
	
	/**
	 * Database of the game.
	 */
	private static DofusDatabase database;

	/**
	 * Constructor of the {@code Dofus} class.
	 */
	private Dofus() {
		chat = new Chat(100);
		world = WorldFactory.getMainWorld();
		database = new DofusDatabase("/data/items.fr.json");
	}
	
	/**
	 * Returns the instance of the {@code Dofus} class
	 * @return Instance of the game.
	 */
	public static Dofus getInstance() {
		if(instance == null)
			instance = new Dofus();
		return instance;
	}

	/**
	 * Returns the chat of the game.
	 * @return Chat of the game.
	 */
	public Chat getChat() {
		return chat;
	}

	/**
	 * Returns the world of the game.
	 * @return World of the game.
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * Returns the database of the game.
	 * @return Database of the game.
	 */
	public DofusDatabase getDatabase() {
		return database;
	}
}
