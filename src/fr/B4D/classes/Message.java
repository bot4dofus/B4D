package fr.B4D.classes;

import fr.B4D.enu.MessageType;
import fr.B4D.modules.B4DChat;

public class Message {

	private final String generalMesageTypeShortcut = "/s";
	private final String teamMesageTypeShortcut = "/t";
	private final String guildMesageTypeShortcut = "/g";
	private final String alliesMesageTypeShortcut = "/a";
	private final String groupMesageTypeShortcut = "/p";
	private final String privateMesageTypeShortcut = "/w";
	private final String kolizeumMesageTypeShortcut = "/k";
	private final String recruitmentMesageTypeShortcut = "/r";
	private final String businessMesageTypeShortcut = "/b";
	
	private String name, text;
	private MessageType messageType;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	public Message(String name, MessageType messageType, String text) {
		this.name = name;
		this.messageType = messageType;
		this.text = text;
	}

	  /*************/
	 /** GET SET **/
	/*************/
	
	public String getName() {
		return name;
	}
	public String getText() {
		return text;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void send() {
		if (messageType == MessageType.Private)
			B4DChat.sendChat(getMessageTypeShortcut(messageType) + " " + name + " " + text);
		else
			B4DChat.sendChat(getMessageTypeShortcut(messageType) + " " + text);
	}
	
	public void answer(String text) {
		if(messageType != MessageType.Information) {
			Message message = new Message(name, MessageType.Private, text);
			message.send();
		}
	}
	
	private String getMessageTypeShortcut(MessageType messageType) {
		switch(messageType) {
		case General:
			return generalMesageTypeShortcut;
		case Team:
			return teamMesageTypeShortcut;
		case Guild:
			return guildMesageTypeShortcut;
		case Allies:
			return alliesMesageTypeShortcut;
		case Group:
			return groupMesageTypeShortcut;
		case Private:
			return privateMesageTypeShortcut;
		case Kolizeum:
			return kolizeumMesageTypeShortcut;
		case Recruitment:
			return recruitmentMesageTypeShortcut;
		case Business:
			return businessMesageTypeShortcut;
		default:
			return null;
		}
	}
	
	  /***************/
	 /** TO STRING **/
	/***************/
	
	public String toString() {
		if(name != null)
			return "[" + messageType.toString() +"][" + name + "] " + text;
		else
			return "[" + messageType.toString() +"] " + text;
	}
}
