package fr.B4D.interaction.chat;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

public class Channel implements Serializable{
	
	private static final long serialVersionUID = 4872542387501307482L;
	
	  /****************/
	 /** COLLECTION **/
	/****************/
	
	public final static Channel GENERAL = new Channel("General", "/s", new PointF(0.024, -0.266 + 0*0.0216));
	public final static Channel TEAM = new Channel("Team", "/t", new PointF(0.024, -0.266 + 1*0.0216));
	public final static Channel GUILD = new Channel("Guild", "/g", new PointF(0.024, -0.266 + 2*0.0216));
	public final static Channel ALLIES = new Channel("Allies", "/a", new PointF(0.024, -0.266 + 3*0.0216));
	public final static Channel GROUP = new Channel("Group", "/p", new PointF(0.024, -0.266 + 4*0.0216));
	public final static Channel BUSINESS = new Channel("Business", "/b", new PointF(0.024, -0.266 + 5*0.0216));
	public final static Channel RECRUITMENT = new Channel("Recruitment", "/r", new PointF(0.024, -0.266 + 6*0.0216));
	public final static Channel PRIVATE = new Channel("Private", "/w", new PointF(0.024, -0.266 + 7*0.0216));
	public final static Channel INFORMATION = new Channel("Information", null, new PointF(0.024, -0.266 + 8*0.0216));
	public final static Channel FIGHT = new Channel("Fight", null, new PointF(0.024, -0.266 + 9*0.0216));
	public final static Channel PROMOTION = new Channel("Promotion", null, new PointF(0.024, -0.266 + 10*0.0216));
	public final static Channel KOLIZEUM = new Channel("Kolizeum", "/k", new PointF(0.024, -0.266 + 11*0.0216));
	public final static Channel EN_COMMUNITY = new Channel("EnCommunity", "/c", new PointF(0.024, -0.266 + 12*0.0216));
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private String name;
	private String prefix;
	private PointF relativCheckPosition;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	public Channel(String name, String prefix, PointF relativCheckPosition) {
		this.name = name;
		this.prefix = prefix;
		this.relativCheckPosition = relativCheckPosition;
	}
	
	  /*************/
	 /** GETTERS **/
	/*************/
	
	public String getName() {
		return this.name;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	  /*************/
	 /** PRIVATE **/
	/*************/
	
	private void toggle(PointF arrowPosition) throws AWTException, StopProgramException, CancelProgramException {
		PointF checkPosition = new PointF(arrowPosition.x + relativCheckPosition.x, arrowPosition.y + relativCheckPosition.y);		
		B4D.mouse.leftClick(checkPosition, false, 500);
	}
	
	private boolean isDisplayed(PointF arrowPosition) throws AWTException {
		PointF checkPosition = new PointF(arrowPosition.x + relativCheckPosition.x, arrowPosition.y + relativCheckPosition.y);
		PointF checkTopLeft = new PointF(checkPosition.x, checkPosition.y - 0.005);
		PointF checkBottomRight = new PointF(checkPosition.x, checkPosition.y + 0.005);		
		return (B4D.screen.searchPixel(checkTopLeft, checkBottomRight, new Color(50,50,50), new Color(255,255,255)) != null);
	}
	
	  /************/
	 /** STATIC **/
	/************/
	
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
		  	channels.add(EN_COMMUNITY);
		    return channels;
		  }
	
	public static void displayChannels(Channel...channel) throws AWTException, StopProgramException, CancelProgramException {
		displayChannels(Arrays.asList(channel));
	}
	public static void displayChannels(List<Channel> channels) throws AWTException, StopProgramException, CancelProgramException {
		B4D.mouse.leftClick(new PointF(-0.26,0.991), false, 200);		//Ouvre le menu du chat
		List<PointF> matchs = B4D.screen.searchPixels(new PointF(-0.0616,0.7285), new PointF(-0.0616,0.985), new Color(100, 100, 100), new Color(255, 255, 255));
		PointF arrowPosition = matchs.get(matchs.size() - 1);
		B4D.mouse.leftClick(arrowPosition, false, 100);	//Affiche les caneaux affichés
		
		List<Channel> toToggle = new ArrayList<Channel>();
		
		for(Channel channel: getAll()) {
			if(channel.isDisplayed(arrowPosition) != channels.contains(channel)) {
				toToggle.add(channel);
			}
		}
		for(Channel channel: toToggle) 
			channel.toggle(arrowPosition);

		B4D.keyboard.sendKey(KeyEvent.VK_ESCAPE, 100);			//Ferme le menu du chat
		B4D.screen.waitForChangingPixel(arrowPosition, 10000);		//Attend la fermeture du menu
	}
}
