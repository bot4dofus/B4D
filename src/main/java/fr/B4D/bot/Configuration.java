package fr.B4D.bot;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;

/** La classe {@code Configuration} représente la configuration de l'écran de jeu.<br><br>
 * Elle est définit par une zone de jeu, une zone de chat, une barre de chat et une minimap.
 */
public class Configuration implements Serializable{

	private static final long serialVersionUID = -1787414977374798817L;
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/

	private Rectangle gameFrame;
	private Point chatMenu;
	private Point chatBar;
	private Point status;
	private Point minimap;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructeur de la classe {@code Configuration}. 
	 */
	public Configuration(){
		this.gameFrame = null;
		this.chatMenu = null;
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
	 * @param gameFrame - Nouvelle zone de jeu.
	 */
	public void setGameFrame(Rectangle gameFrame) {
		this.gameFrame = gameFrame;
	}

	/** Retourne la position du menu du chat;
	 * @return Position du menu du chat en coordonnées simples.
	 */
	public Point getChatMenu() {
		return chatMenu;
	}

	/** Modifi la position du menu du chat.
	 * @param chatMenu - Nouvelle position du menu du chat en coordonnées simples.
	 */
	public void setChatMenu(Point chatMenu) {
		this.chatMenu = chatMenu;
		Channel.setChatMenuPosition(chatMenu);
	}

	/** Retourne la position de la barre de chat.
	 * @return Position de la barre de chat en coordonnées simples.
	 */
	public Point getChatBar() {
		return chatBar;
	}

	/** Modifi la position de la barre de chat.
	 * @param chatBar - Nouvelle position de la barre de chat en coordonnées simples.
	 */
	public void setChatBar(Point chatBar) {
		this.chatBar = chatBar;
	}

	/** Retourne la position du status.
	 * @return Position du status en coordonnées simples.
	 */
	public Point getStatus() {
		return status;
	}

	/** Modifi la position du status.
	 * @param chatBar - Nouvelle position du status en coordonnées simples.
	 */
	public void setStatus(Point status) {
		this.status = status;
		Status.setStatusMenuPosition(status);
	}
	
	/** Retourne la position de la minimap.
	 * @return Position de la minimap en coordonnées simples.
	 */
	public Point getMinimap() {
		return minimap;
	}

	/** Modifi la position de la minimap.
	 * @param minimap - Nouvelle position de la minimap en coordonnées simples.
	 */
	public void setMinimap(Point minimap) {
		this.minimap = minimap;
	}
	

	  /*************/
	 /** METHODS **/
	/*************/
	
	/** Retourne un boolean représentant si la totalité des paramètres ont été configurés.
	 * @return {@code true} si la configuration est complète, {@code false} sinon.
	 */
	public boolean isComplet() {
		return gameFrame != null && chatMenu != null && chatBar != null && status != null && minimap != null;
	}
}
