package fr.B4D.dofus;

import fr.B4D.interaction.chat.Chat;

/** La classe {@code Dofus} permet d'accéder au monde est au chat de dofus.
 */
public class Dofus {
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private static Dofus instance;
	
	private Chat chat;
	private static World world;
	
	  /*************/
	 /** BUILDER **/
	/*************/

	/** Constructeur de la classe {@code Dofus}.
	 */
	private Dofus() {
		chat = new Chat(100);
		world = new World();
	}
	
	/** Retourne l'instance de la classe {@code Dofus}.
	 * @return Instance du jeu dofus.
	 */
	public static Dofus getInstance() {
		if(instance == null)
			instance = new Dofus();
		return instance;
	}
	
	/***********************/
	/** GETTERS & SETTERS **/
	/***********************/

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
}
