package fr.B4D.interaction.chat;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Server;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/** La classe {@code Channel} représente un canal de diffusion du chat.<br><br>
 * Un canal est défini par un nom, un préfix et un point d'activation.
 */
public class Channel implements Serializable{
	
	private static final long serialVersionUID = 4872542387501307482L;
	private final static double spaceBetweenChannels = 0.0216;
	
	  /****************/
	 /** COLLECTION **/
	/****************/
	
	public final static Channel GENERAL = new Channel("General", "/s", new PointF(0.024, -0.266 + 0*spaceBetweenChannels));
	public final static Channel TEAM = new Channel("Team", "/t", new PointF(0.024, -0.266 + 1*spaceBetweenChannels));
	public final static Channel GUILD = new Channel("Guild", "/g", new PointF(0.024, -0.266 + 2*spaceBetweenChannels));
	public final static Channel ALLIES = new Channel("Allies", "/a", new PointF(0.024, -0.266 + 3*spaceBetweenChannels));
	public final static Channel GROUP = new Channel("Group", "/p", new PointF(0.024, -0.266 + 4*spaceBetweenChannels));
	public final static Channel BUSINESS = new Channel("Business", "/b", new PointF(0.024, -0.266 + 5*spaceBetweenChannels));
	public final static Channel RECRUITMENT = new Channel("Recruitment", "/r", new PointF(0.024, -0.266 + 6*spaceBetweenChannels));
	public final static Channel PRIVATE = new Channel("Private", "/w", new PointF(0.024, -0.266 + 7*spaceBetweenChannels));
	public final static Channel INFORMATION = new Channel("Information", null, new PointF(0.024, -0.266 + 8*spaceBetweenChannels));
	public final static Channel FIGHT = new Channel("Fight", null, new PointF(0.024, -0.266 + 9*spaceBetweenChannels));
	public final static Channel PROMOTION = new Channel("Promotion", null, new PointF(0.024, -0.266 + 10*spaceBetweenChannels));
	public final static Channel KOLIZEUM = new Channel("Kolizeum", "/k", new PointF(0.024, -0.266 + 11*spaceBetweenChannels));
	public final static Channel COMMUNITY = new Channel("Community", "/c", new PointF(0.024, -0.266 + 12*spaceBetweenChannels));
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private static Point chatMenuPosition;
	
	private String name;
	private String prefix;
	private PointF relativCheckPosition;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructeur de la classe {@code Channel}.
	 * @param name - Nom de la classe
	 * @param prefix - Préfix de la classe
	 * @param relativCheckPosition - Point relatif au menu
	 */
	public Channel(String name, String prefix, PointF relativCheckPosition) {
		this.name = name;
		this.prefix = prefix;
		this.relativCheckPosition = relativCheckPosition;
	}
	
	  /*************/
	 /** GETTERS **/
	/*************/
	
	/** Modifi la position du menu du chat.
	 * @param chatMenuPosition - Nouvelle position du menu du chat.
	 */
	public static void setChatMenuPosition(Point chatMenuPosition) {
		Channel.chatMenuPosition = chatMenuPosition;
	}
	
	/** Retourne le nom du canal.
	 * @return Nom du canal.
	 */
	public String getName() {
		return this.name;
	}
	
	/** Retourne le préfix du canal.
	 * @return Préfixe du canal.
	 */
	public String getPrefix() {
		return this.prefix;
	}
	
	  /*************/
	 /** PRIVATE **/
	/*************/

	/** Retourne la position de la coche du canal.
	 * @param server - Server courant.
	 * @param arrowPosition - Position du menu.
	 * @return position relative de la coche.
	 */
	private PointF getChekPosition(Server server, PointF arrowPosition) {
		if(server.isInternationnal())
			return new PointF(arrowPosition.x + relativCheckPosition.x, arrowPosition.y + relativCheckPosition.y);
		else
			return new PointF(arrowPosition.x + relativCheckPosition.x, arrowPosition.y + relativCheckPosition.y + spaceBetweenChannels);
	}
	
	/** Retourne l'état du canal.
	 * @param server - Server courant.
	 * @param arrowPosition - Position du menu.
	 * @return {@code true} si le canal est affiché, {@code false} sinon.
	 */
	private boolean isDisplayed(Server server, PointF arrowPosition) {
		PointF checkPosition = getChekPosition(server, arrowPosition);
		PointF checkTopLeft = new PointF(checkPosition.x, checkPosition.y - 0.005);
		PointF checkBottomRight = new PointF(checkPosition.x, checkPosition.y + 0.005);	
		return (B4D.screen.searchPixel(checkTopLeft, checkBottomRight, new Color(50,50,50), new Color(255,255,255)) != null);
	}
	
	/** Change l'état du canal.
	 * @param server - Server courant.
	 * @param arrowPosition - Position du menu.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	private void toggle(Server server, PointF arrowPosition) throws StopProgramException, CancelProgramException {
		PointF checkPosition = getChekPosition(server, arrowPosition);		
		B4D.mouse.leftClick(checkPosition, false, 500);
	}
	
	/** Active/Affiche le canal.
	 * @param server - Server courant.
	 * @param arrowPosition - Position du menu.
	 * @return {@code true} si l'état du canal a changé, {@code false} sinon.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws AWTException Si un problème de souris survient.
	 */
	private boolean enable(Server server, PointF arrowPosition) throws StopProgramException, CancelProgramException {
		if(!isDisplayed(server, arrowPosition)) {
			toggle(server, arrowPosition);
			return true;
		}
		else
			return false;
	}
	
	/** Désactive/Cache le canal.
	 * @param server - Server courant.
	 * @param arrowPosition - Position du menu.
	 * @return {@code true} si l'état du canal a changé, {@code false} sinon.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	private boolean disable(Server server, PointF arrowPosition) throws StopProgramException, CancelProgramException {
		if(isDisplayed(server, arrowPosition)) {
			toggle(server, arrowPosition);
			return true;
		}
		else
			return false;
	}
	
	  /************/
	 /** STATIC **/
	/************/
	
	 /** Retourne une liste de tous les canaux existants.
	 * @return List des canaux.
	 */
	public final static List<Channel> getAll(){
		  	List<Channel> channels = new ArrayList<Channel>();
		  	channels.add(GENERAL);
		  	channels.add(TEAM);
		  	channels.add(GUILD);
		  	channels.add(ALLIES);
		  	channels.add(GROUP);
		  	channels.add(BUSINESS);
		  	channels.add(RECRUITMENT);
		  	channels.add(PRIVATE);
		  	channels.add(INFORMATION);
		  	channels.add(FIGHT);
		  	channels.add(PROMOTION);
		  	channels.add(KOLIZEUM);
		  	channels.add(COMMUNITY);
		    return channels;
		  }
	
	/** Permet d'afficher un ou plusieurs canaux. Les autres canaux seront désactivés.
	 * @param server - Server courant.
	 * @param channel - Liste des canaux à afficher.
	 * @return Liste des canaux ayant changé d'état.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws AWTException Si un problème de souris survient.
	 */
	public static List<Channel> displayChannels(Server server, Channel...channel) throws AWTException, StopProgramException, CancelProgramException {
		return displayChannels(server, Arrays.asList(channel));
	}
	
	/** Permet d'afficher un ou plusieurs canaux. Les autres canaux seront désactivés.
	 * @param server - Server courant.
	 * @param channels - Liste des canaux à afficher.
	 * @return Liste des canaux ayant changé d'état, {@code null} si impossible d'ouvrir le menu.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public static List<Channel> displayChannels(Server server, List<Channel> channels) throws StopProgramException, CancelProgramException {
		List<Channel> toggles = null;
		
		if(chatMenuPosition != null) {
			PointF menu = B4D.converter.toPointF(chatMenuPosition);
			B4D.mouse.leftClick(menu, false, 200);		//Ouvre le menu du chat
			double XArrowPosition = getRelativXArrowPosition(server);
			List<PointF> matchs = B4D.screen.searchPixels(new PointF(menu.x + XArrowPosition, menu.y - 0.2625), new PointF(menu.x + XArrowPosition, menu.y - 0.006), new Color(100, 100, 100), new Color(255, 255, 255));
			
			if(matchs == null)
				throw new CancelProgramException("No pixel found.");
			
			PointF arrowPosition = matchs.get(matchs.size() - 1);//1984
			B4D.mouse.place(arrowPosition, 500);		//Affiche les caneaux affichés
			
			toggles = new ArrayList<Channel>();
			
			for(Channel channel: getAll()) {
				if(!(channel == Channel.COMMUNITY && !server.isInternationnal())) {
					boolean toggled;
					if(channels.contains(channel))
						toggled = channel.enable(server, arrowPosition);
					else
						toggled = channel.disable(server, arrowPosition);
					if(toggled)
						toggles.add(channel);
				}
			}

			B4D.keyboard.sendKey(KeyEvent.VK_ESCAPE, 0);				//Ferme le menu du chat
			B4D.screen.waitForChangingPixel(arrowPosition, 10000);		//Attend la fermeture du menu
		}
		return toggles;
	}
	
	/** Permet de retourner la distance relative entre le menu du chat et les flêches en fonction du server.
	 * @param server - Server courant.
	 * @return Distance relative entre le menu du chat et les flêches.
	 */
	private static double getRelativXArrowPosition(Server server) {
		if(server.isInternationnal())
			return 0.1984;
		else
			return 0.187;
	}
	
	/** Permet de retourner le canal correspondant à un octet.
	 * @param data - Octet du canal.
	 * @return Canal correspondant.
	 * @throws B4DException Si une exception de type B4D est levée.
	 */
	public static Channel fromByte(byte data) throws B4DException {
		switch(data) {
			case(0):
				return GENERAL;
			case(0x04):
				return TEAM;
			case(0x09):
				return PRIVATE;
			case(0x05):
				return BUSINESS;
			case(0x06):
				return RECRUITMENT;
			default:
				throw new B4DException("Unknow channel " + Byte.toUnsignedInt(data));
		}
	}
	
	  /***************/
	 /** TO STRING **/
	/***************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
}
