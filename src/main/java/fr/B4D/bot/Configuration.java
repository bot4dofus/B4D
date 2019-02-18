package fr.B4D.bot;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

/** La classe {@code Configuration} représente la configuration de l'écran de jeu.
 * Elle est définit par une zone de jeu, une zone de chat, une barre de chat et une minimap.
 */
public class Configuration implements Serializable{

	private static final long serialVersionUID = -1787414977374798817L;
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/

	private Rectangle gameFrame;
	private Rectangle chatFrame;
	private Point chatBar;
	private Point minimap;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructeur de la classe {@code Configuration}. 
	 */
	public Configuration(){
		this.gameFrame = null;
		this.chatFrame = null;
		this.chatBar = null;
		this.minimap = null;
	}
	
	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/

	/** Retourne la zone de jeu.
	 * @return Zone de jeu.
	 */
	public Rectangle getGameFrame() {
		return gameFrame;
	}

	/** Modifi la zone de jeu.
	 * @param gameFrame Nouvelle zone de jeu.
	 */
	public void setGameFrame(Rectangle gameFrame) {
		this.gameFrame = gameFrame;
	}

	/** Retourne la zone de chat;
	 * @return Zone de chat.
	 */
	public Rectangle getChatFrame() {
		return chatFrame;
	}

	/** Modifi la zone de chat.
	 * @param chatFrame Nouvelle zone de chat.
	 */
	public void setChatFrame(Rectangle chatFrame) {
		this.chatFrame = chatFrame;
	}

	/** Retourne la position de la barre de chat.
	 * @return Position de la barre de chat en coordonnées simples.
	 */
	public Point getChatBar() {
		return chatBar;
	}

	/** Modifi la position de la barre de chat.
	 * @param chatBar - Nouvelles position de la barre de chat en coordonnées simples.
	 */
	public void setChatBar(Point chatBar) {
		this.chatBar = chatBar;
	}

	/** Retourne la position de la minimap.
	 * @return Position de la minimap en coordonnées simples.
	 */
	public Point getMinimap() {
		return minimap;
	}

	/** Modifi la position de la minimap.
	 * @param chatBar - Nouvelles position de la minimap en coordonnées simples.
	 */
	public void setMinimap(Point minimap) {
		this.minimap = minimap;
	}
}
