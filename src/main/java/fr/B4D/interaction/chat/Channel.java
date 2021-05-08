package fr.B4D.interaction.chat;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.dofus.server.Server;
import fr.B4D.dofus.server.ServerType;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/**
 * The {@code Channel} class represents a channel in the chat.<br><br>
 * A channel is defined by a name, a prefix and an activation point.
 * 
 * @author Lucas
 *
 */
public class Channel implements Serializable{
	
	private static final long serialVersionUID = 4872542387501307482L;
	private final static double spaceBetweenChannels = 0.0216;
	
	/**
	 * General channel.
	 */
	public final static Channel GENERAL = new Channel("General", "/s", new PointF(0.024, -0.266 + 0*spaceBetweenChannels));
	
	/**
	 * Team channel.
	 */
	public final static Channel TEAM = new Channel("Team", "/t", new PointF(0.024, -0.266 + 1*spaceBetweenChannels));
	
	/**
	 * Guild channel.
	 */
	public final static Channel GUILD = new Channel("Guild", "/g", new PointF(0.024, -0.266 + 2*spaceBetweenChannels));
	
	/**
	 * Allies channel.
	 */
	public final static Channel ALLIES = new Channel("Allies", "/a", new PointF(0.024, -0.266 + 3*spaceBetweenChannels));
	
	/**
	 * Group channel.
	 */
	public final static Channel GROUP = new Channel("Group", "/p", new PointF(0.024, -0.266 + 4*spaceBetweenChannels));
	
	/**
	 * Business channel.
	 */
	public final static Channel BUSINESS = new Channel("Business", "/b", new PointF(0.024, -0.266 + 5*spaceBetweenChannels));
	
	/**
	 * Recruitment channel.
	 */
	public final static Channel RECRUITMENT = new Channel("Recruitment", "/r", new PointF(0.024, -0.266 + 6*spaceBetweenChannels));
	
	/**
	 * Private channel.
	 */
	public final static Channel PRIVATE = new Channel("Private", "/w", new PointF(0.024, -0.266 + 7*spaceBetweenChannels));
	
	/**
	 * Information channel.
	 */
	public final static Channel INFORMATION = new Channel("Information", null, new PointF(0.024, -0.266 + 8*spaceBetweenChannels));
	
	/**
	 * Fight channel.
	 */
	public final static Channel FIGHT = new Channel("Fight", null, new PointF(0.024, -0.266 + 9*spaceBetweenChannels));
	
	/**
	 * Promotion channel.
	 */
	public final static Channel PROMOTION = new Channel("Promotion", null, new PointF(0.024, -0.266 + 10*spaceBetweenChannels));
	
	/**
	 * Kolizeum channel.
	 */
	public final static Channel KOLIZEUM = new Channel("Kolizeum", "/k", new PointF(0.024, -0.266 + 11*spaceBetweenChannels));
	
	/**
	 * Community channel.
	 */
	public final static Channel COMMUNITY = new Channel("Community", "/c", new PointF(0.024, -0.266 + 12*spaceBetweenChannels));
	
	/**
	 * Location of the chat menu in simple coordinates.
	 */
	private static Point chatMenuPosition;
	

	/**
	 * Returns a list of all the existing channels.
	 * @return List of channels.
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
	
	/**
	 * Displays multiple channels.<br><br>
	 * If the channel is enabled, it will be disabled. If the channel is disabled, it will be enabled.
	 * @param server - Current server.
	 * @param channels - List of channels to display.
	 * @return List of toggled channels.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected operation occurred.
	 */
	public static List<Channel> displayChannels(Server server, Channel...channels) throws StopProgramException, CancelProgramException, B4DException {
		return displayChannels(server, Arrays.asList(channels));
	}
	
	/**
	 * Displays multiple channels.<br><br>
	 * If the channel is enabled, it will be disabled. If the channel is disabled, it will be enabled.
	 * @param server - Current server.
	 * @param channels - List of channels to display.
	 * @return List of toggled channels.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected operation occurred.
	 */
	public static List<Channel> displayChannels(Server server, List<Channel> channels) throws StopProgramException, CancelProgramException, B4DException {
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
				if(!(channel == Channel.COMMUNITY && !server.getType().equals(ServerType.INTERNATIONAL))) {
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
	
	/**
	 * Returns the relative distance between the chat menu and the arrows depending on the server.<br><br>
	 * If the server is international, the arrows are not at the same location.
	 * @param server - Current server.
	 * @return Relative distance between the chat menu and the arrows.
	 */
	private static double getRelativXArrowPosition(Server server) {
		if(server.getType().equals(ServerType.INTERNATIONAL))
			return 0.1984;
		else
			return 0.187;
	}
	
	/**
	 * Returns the channel from a byte.
	 * @param data - Byte of the channel.
	 * @return Corresponding channel.
	 * @throws B4DException if the channel is unknown.
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

	/**
	 * Defines the location of the menu button.
	 * @param chatMenuPosition - Location of the menu button in simple coordinates.
	 */
	public static void setChatMenuPosition(Point chatMenuPosition) {
		Channel.chatMenuPosition = chatMenuPosition;
	}
	
	/**
	 * Name of the channel.
	 */
	private String name;
	
	/**
	 * Prefix of the channel. The prefix is used in the chat bar (eg : /s for the General channel).
	 */
	private String prefix;
	
	/**
	 * Location of the channel in the menu to toggle it in relative coordinates.
	 */
	private PointF relativCheckPosition;
	
	/**
	 * Constructor of the {@code Channel} class.
	 * @param name - Name of the channel.
	 * @param prefix - Prefix of the channel.
	 * @param relativCheckPosition - Relative location in the menu in relative coordinates.
	 */
	public Channel(String name, String prefix, PointF relativCheckPosition) {
		this.name = name;
		this.prefix = prefix;
		this.relativCheckPosition = relativCheckPosition;
	}
	
	/**
	 * Returns the name of the channel.
	 * @return Name of the channel.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the prefix of the channel.
	 * @return Prefix of the channel.
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**
	 * Returns the location of the channel button depending on the server.
	 * @param server - Current server.
	 * @param arrowPosition - Location of the arrow in relative coordinates.
	 * @return Location of the channel button in relative coordinates.
	 */
	private PointF getChekPosition(Server server, PointF arrowPosition) {
		if(server.getType().equals(ServerType.INTERNATIONAL))
			return new PointF(arrowPosition.x + relativCheckPosition.x, arrowPosition.y + relativCheckPosition.y);
		else
			return new PointF(arrowPosition.x + relativCheckPosition.x, arrowPosition.y + relativCheckPosition.y + spaceBetweenChannels);
	}
	
	/**
	 * Returns the state of the channel.
	 * @param server - Current server.
	 * @param arrowPosition - Location of the arrow in relative coordinates.
	 * @return {@code true} if the channel is enabled, {@code false} otherwise.
	 * @throws B4DException if an unexpected operation occurred.
	 */
	private boolean isDisplayed(Server server, PointF arrowPosition) throws B4DException {
		PointF checkPosition = getChekPosition(server, arrowPosition);
		PointF checkTopLeft = new PointF(checkPosition.x, checkPosition.y - 0.005);
		PointF checkBottomRight = new PointF(checkPosition.x, checkPosition.y + 0.005);	
		return (B4D.screen.searchPixel(checkTopLeft, checkBottomRight, new Color(50,50,50), new Color(255,255,255)) != null);
	}
	
	/**
	 * Change the state of the channel.
	 * @param server - Current server.
	 * @param arrowPosition - Location of the arrow in relative coordinates.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	private void toggle(Server server, PointF arrowPosition) throws StopProgramException, CancelProgramException, B4DException {
		PointF checkPosition = getChekPosition(server, arrowPosition);		
		B4D.mouse.leftClick(checkPosition, false, 500);
	}
	
	/**
	 * Enables the channel.
	 * @param server - Current server.
	 * @param arrowPosition - Location of the arrow in relative coordinates.
	 * @return {@code true} if the state has changed, {@code false} otherwise.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected operation occurred.
	 */
	private boolean enable(Server server, PointF arrowPosition) throws StopProgramException, CancelProgramException, B4DException {
		if(!isDisplayed(server, arrowPosition)) {
			toggle(server, arrowPosition);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Disables the channel.
	 * @param server - Current server.
	 * @param arrowPosition - Location of the arrow in relative coordinates.
	 * @return {@code true} if the state has changed, {@code false} otherwise.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected operation occurred.
	 */
	private boolean disable(Server server, PointF arrowPosition) throws StopProgramException, CancelProgramException, B4DException {
		if(isDisplayed(server, arrowPosition)) {
			toggle(server, arrowPosition);
			return true;
		}
		else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
}
