package fr.B4D.modules;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.B4D.bot.Configuration;
import fr.B4D.chat.Message;
import fr.B4D.enu.Channel;

public final class B4DChat {
	
	private final static String generalPrefix = "/s";
	private final static String teamPrefix = "/t";
	private final static String guildPrefix = "/g";
	private final static String alliesPrefix = "/a";
	private final static String groupPrefix = "/p";
	private final static String privatePrefix = "/w";
	private final static String kolizeumPrefix = "/k";
	private final static String recruitmentPrefix = "/r";
	private final static String businessPrefix = "/b";
	
	  /***************/
	 /** SEND CHAT **/
	/***************/
	
	public static void sendChat(String text, double time) {
		try {
			B4DMouse.leftClick(Configuration.getInstance().chatBar, false, 0.5);
			B4DKeyboard.writeKeyboard(text,time);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			B4DWait.wait(0.1);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	public static void sendChat(String text){
		B4DChat.sendChat(text, 0.5);
	}
	
	  /******************/
	 /** SEND MESSAGE **/
	/******************/
	
	public static void sendMessage(Channel channel, String text){
		B4DChat.sendChat(text, 0.5);
	}
	public static void sendPrivateMessage(String name, String text){
		B4DChat.sendChat(getChannelPrefix(Channel.Private) + " " + name + " " + text, 0.5);
	}
	
	  /**************/
	 /** GET CHAT **/
	/**************/
	
	public static String getChat() throws AWTException, UnsupportedFlavorException, IOException  {
		return B4DScreen.getSelection(new Point((int)(Configuration.getInstance().chatFrame.x + Configuration.getInstance().chatFrame.width*0.95), (int)(Configuration.getInstance().chatFrame.y + Configuration.getInstance().chatFrame.height*0.95)));
	}
	
	public static ArrayList<Message> parseChat() throws AWTException, UnsupportedFlavorException, IOException{
		ArrayList<Message> messages = new ArrayList<Message>();
		List<String> lignes = Arrays.asList(getChat().split("\n"));
		lignes.stream().forEach(l -> {
			try {
				messages.add(parseLine(l));
			} catch (AWTException | UnsupportedFlavorException | IOException e) {
				e.printStackTrace();
			}
		});
		return messages;
	}
	
	private static Message parseLine(String line) throws AWTException, UnsupportedFlavorException, IOException{
		String sender = null, text = "";
		Channel messageType = Channel.Information;
		List<String> words = Arrays.asList(line.split(" "));
		
		if(words.size() > 3) {
			if(words.get(2).equals(":")) {
				sender = words.get(1);
				text = words.subList(3, words.size()).stream().collect(Collectors.joining(" "));
				if(words.get(0).equals("(Équipe)"))
					messageType = Channel.Team;
				else if(words.get(0).equals("(Guilde)"))
					messageType = Channel.Guild;
				else if(words.get(0).equals("(Alliance)"))
					messageType = Channel.Allies;
				else if(words.get(0).equals("(Groupe)"))
					messageType = Channel.Group;
				else if(words.get(0).equals("à") || words.get(0).equals("de"))
					messageType = Channel.Private;
				else if(words.get(0).equals("(Kolizéum)"))
					messageType = Channel.Kolizeum;
				else if(words.get(0).equals("(Recrutement)"))
					messageType = Channel.Recruitment;
				else if(words.get(0).equals("(Commerce)"))
					messageType = Channel.Business;
			}
			else if(words.get(1).equals(":")) {
				sender = words.get(0);
				messageType = Channel.General;
				text = words.subList(2, words.size()).stream().collect(Collectors.joining(" "));
			}
			else
				text = line;
		}
		else
			text = line;
	
		return new Message(sender, messageType, text);
	}
	
	  /************************/
	 /** GET CHANNEL PREFIX **/
	/************************/
	
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
	
}
