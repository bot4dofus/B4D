package fr.B4D.classes;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import fr.B4D.classes.transports.TransportInterface;
import fr.B4D.exceptions.B4DCannotFind;
import fr.B4D.modules.B4DKeyboard;
import fr.B4D.modules.B4DScreen;

public final class World implements TransportInterface{


	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	

	  /*********************/
	 /** PATCH TRANSPORT **/
	/*********************/
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void goTo(Point destination) throws AWTException, B4DCannotFind {
		//Pathfinding
	}

	public Point getPosition() throws AWTException, UnsupportedFlavorException, IOException {
		B4DKeyboard.writeChat("/s %pos%", 0.5);
		String chat = B4DScreen.getChatSelection();
		chat = chat.substring(chat.lastIndexOf("["), chat.lastIndexOf("]"));
		int middle = chat.indexOf(";");
		return new Point(Integer.valueOf(chat.substring(0, middle)),Integer.valueOf(chat.substring(middle)));
	}
	
}
