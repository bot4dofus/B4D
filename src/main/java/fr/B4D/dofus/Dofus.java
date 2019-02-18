package fr.B4D.dofus;

import fr.B4D.interaction.chat.Chat;

/** La classe {@code Dofus} permet d'accéder au monde est au chat de dofus.
 */
public class Dofus {
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	public static Chat chat;
	public static World world;
	
	  /*************/
	 /** BUILDER **/
	/*************/

	/** Constructeur de la classe {@code Dofus}.
	 */
	public Dofus() {
		chat = new Chat(100);
		world = new World();
	}
}
