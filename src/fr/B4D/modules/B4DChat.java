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

import fr.B4D.classes.Bot;
import fr.B4D.classes.Message;
import fr.B4D.classes.PointF;
import fr.B4D.enu.MessageType;

public final class B4DChat {
	
	  /****************/
	 /** WRITE CHAT **/
	/****************/
	
	public static void sendChat(String text, double time) {
		try {
			B4DMouse.leftClick(new PointF(0.05347166799, 0.98902195608), false, 0.5);
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
	
	  /**************/
	 /** GET CHAT **/
	/**************/
	
	public static String getChat() throws AWTException, UnsupportedFlavorException, IOException  {
		return B4DScreen.getSelection(new Point((int)(Bot.configuration.chatFrame.x + Bot.configuration.chatFrame.width*0.95), (int)(Bot.configuration.chatFrame.y + Bot.configuration.chatFrame.height*0.95)));
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
		MessageType messageType = MessageType.Information;
		List<String> words = Arrays.asList(line.split(" "));
		
		if(words.size() > 3) {
			if(words.get(2).equals(":")) {
				sender = words.get(1);
				text = words.subList(3, words.size()).stream().collect(Collectors.joining(" "));
				if(words.get(0).equals("(Équipe)"))
					messageType = MessageType.Team;
				else if(words.get(0).equals("(Guilde)"))
					messageType = MessageType.Guild;
				else if(words.get(0).equals("(Alliance)"))
					messageType = MessageType.Allies;
				else if(words.get(0).equals("(Groupe)"))
					messageType = MessageType.Group;
				else if(words.get(0).equals("à") || words.get(0).equals("de"))
					messageType = MessageType.Private;
				else if(words.get(0).equals("(Kolizéum)"))
					messageType = MessageType.Kolizeum;
				else if(words.get(0).equals("(Recrutement)"))
					messageType = MessageType.Recruitment;
				else if(words.get(0).equals("(Commerce)"))
					messageType = MessageType.Business;
			}
			else if(words.get(1).equals(":")) {
				sender = words.get(0);
				messageType = MessageType.General;
				text = words.subList(2, words.size()).stream().collect(Collectors.joining(" "));
			}
			else
				text = line;
		}
		else
			text = line;
	
		return new Message(sender, messageType, text);
	}
}
