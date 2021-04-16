package fr.B4D.bot;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;

/**
 * The {@code Configuration} class represents the configuration of the game.
 * <br><br>
 * It is defined by areas and locations of components on the screen.
 * 
 * @author Lucas
 *
 */
public class Configuration implements Serializable{

	private static final long serialVersionUID = -1787414977374798817L;

	/**
	 * Area where the user can click to interact.
	 */
	private Rectangle gameFrame;
	
	/**
	 * Location of the chat menu button.
	 */
	private Point chatMenu;
	
	/**
	 * Location of the chat bar.
	 */
	private Point chatBar;
	
	/**
	 * Location of the status button.
	 */
	private Point status;
	
	/**
	 * Location of the minimap.
	 */
	private Point minimap;
	
	/**
	 * Constructor of the {@code Configuration} class.
	 */
	public Configuration(){
		this.gameFrame = null;
		this.chatMenu = null;
		this.chatBar = null;
		this.minimap = null;
	}

	/**
	 * Returns the game frame.
	 * @return Rectangle representing the area where the user can interact.
	 */
	public Rectangle getGameFrame() {
		return gameFrame;
	}

	/**
	 * Defines the new game frame.
	 * @param gameFrame - Rectangle representing the area where the user can interact.
	 */
	public void setGameFrame(Rectangle gameFrame) {
		this.gameFrame = gameFrame;
	}

	/**
	 * Returns the location of the chat menu button on the screen.
	 * @return Location of the chat menu button on the screen.
	 */
	public Point getChatMenu() {
		return chatMenu;
	}

	/**
	 * Defines the new location of the chat menu button on the screen.
	 * @param chatMenu - Location of the chat menu button on the screen
	 */
	public void setChatMenu(Point chatMenu) {
		this.chatMenu = chatMenu;
		Channel.setChatMenuPosition(chatMenu);
	}

	/**
	 * Returns the location of the chat bar on the screen.
	 * @return Location of the chat bar on the screen.
	 */
	public Point getChatBar() {
		return chatBar;
	}

	/**
	 * Defines the new location of the chat bar on the screen.
	 * @param chatBar - Location of the chat bar on the screen
	 */
	public void setChatBar(Point chatBar) {
		this.chatBar = chatBar;
	}

	/**
	 * Returns the location of the status button on the screen.
	 * @return Location of the status button on the screen.
	 */
	public Point getStatus() {
		return status;
	}

	/**
	 * Defines the new location of the status button on the screen.
	 * @param status - Location of the status button on the screen
	 */
	public void setStatus(Point status) {
		this.status = status;
		Status.setStatusMenuPosition(status);
	}

	/**
	 * Returns the location of the minimap on the screen.
	 * @return Location of the minimap on the screen.
	 */
	public Point getMinimap() {
		return minimap;
	}

	/**
	 * Defines the new location of the minimap on the screen.
	 * @param minimap - Location of the minimap on the screen
	 */
	public void setMinimap(Point minimap) {
		this.minimap = minimap;
	}
	
	/**
	 * Checks whether the configuration is completed. All the attributes of the class needs to be defined and non null.
	 * @return {@code true} if the configuration is completed, {@code false} otherwise.
	 */
	public boolean isComplet() {
		return gameFrame != null && chatMenu != null && chatBar != null && status != null && minimap != null;
	}
}
