package fr.B4D.interaction.chat;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import fr.B4D.bot.B4D;
import fr.B4D.bot.statics.Keyboard;
import fr.B4D.bot.statics.Mouse;
import fr.B4D.dofus.Dofus;
import fr.B4D.modules.B4DWait;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 5508700695491701427L;

	private final static String generalPrefix = "/s";
	private final static String teamPrefix = "/t";
	private final static String guildPrefix = "/g";
	private final static String alliesPrefix = "/a";
	private final static String groupPrefix = "/p";
	private final static String privatePrefix = "/w";
	private final static String kolizeumPrefix = "/k";
	private final static String recruitmentPrefix = "/r";
	private final static String businessPrefix = "/b";

	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private String pseudo, text;
	private Channel channel;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	public Message(String pseudo, Channel channel, String text) {
		this.pseudo = pseudo;
		this.channel = channel;
		this.text = text;
	}

	  /*************/
	 /** GETTERS **/
	/*************/
	
	public String getPseudo() {
		return pseudo;
	}
	public String getText() {
		return text;
	}
	public Channel getChannel() {
		return channel;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public static void sendChat(String text, double time) {
		try {
			Mouse.leftClick(B4D.getConfiguration().getChatBar(), false, 0.5);
			Keyboard.writeKeyboard(text,time);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			B4DWait.wait(0.1);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	public static void sendChat(String text){
		sendChat(text, 0.5);
	}
	
	public void send() {
		if (channel == Channel.Private)
			sendChat(getChannelPrefix(channel) + " " + pseudo + " " + text);
		else
			sendChat(getChannelPrefix(channel) + " " + text);
	}
	
	public void reply(String text) {
		if(channel == Channel.Business || channel == Channel.Recruitment)
			channel = Channel.Private;
		this.text = text;
		send();
	}
	
	public Message waitForReply(long timeout) {
		B4D.logger.debug(this, "Attente d'une réponse");
		Dofus.chat.addPseudoFilter(pseudo);
		Message message = Dofus.chat.waitForMessage(timeout);
		Dofus.chat.addPseudoFilter(null);
		return message;
	}
	
	  /***************/
	 /** TO STRING **/
	/***************/
	
	public String toString() {
		if(pseudo != null)
			return "[" + channel.toString() +"][" + pseudo + "] " + text;
		else
			return "[" + channel.toString() +"] " + text;
	}
	
	  /************/
	 /** STATIC **/
	/************/
	
	public static String getChannelPrefix(Channel channel) {
		switch(channel) {
			case General:
				return generalPrefix;
			case Team:
				return teamPrefix;
			case Guild:
				return guildPrefix;
			case Allies:
				return alliesPrefix;
			case Group:
				return groupPrefix;
			case Private:
				return privatePrefix;
			case Kolizeum:
				return kolizeumPrefix;
			case Recruitment:
				return recruitmentPrefix;
			case Business:
				return businessPrefix;
			default:
				return null;
		}
	}
	
	public static Channel getChannel(byte data) throws UnknowChannelException {
		switch(Byte.toUnsignedInt(data)) {
			case(0):
				return Channel.General;
			case(4):
				return Channel.Team;
			case(0x09):
				return Channel.Private;
			case(5):
				return Channel.Business;
			case(6):
				return Channel.Recruitment;
			default:
				throw new UnknowChannelException(Byte.toUnsignedInt(data));
		}
	}
}
