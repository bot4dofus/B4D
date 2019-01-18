package fr.B4D.chat;

import java.io.Serializable;

import fr.B4D.enu.Channel;
import fr.B4D.modules.B4DChat;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 5508700695491701427L;
	
	private String name, text;
	private Channel messageType;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	public Message(String name, Channel messageType, String text) {
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
	public Channel getMessageType() {
		return messageType;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void send() {
		if (messageType == Channel.Private)
			B4DChat.sendChat(B4DChat.getChannelPrefix(messageType) + " " + name + " " + text);
		else
			B4DChat.sendChat(B4DChat.getChannelPrefix(messageType) + " " + text);
	}
	
	public void answer(String text) {
		if(messageType != Channel.Information) {
			Message message = new Message(name, Channel.Private, text);
			message.send();
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
