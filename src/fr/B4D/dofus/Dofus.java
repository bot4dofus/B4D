package fr.B4D.dofus;

import fr.B4D.interaction.chat.Chat;

public class Dofus {
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private static Chat chat;
	private static World world;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	public Dofus() {
		chat = new Chat(100);
		world = new World();
	}
	
	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/
	
	public static Chat getChat() {
		return chat;
	}
	public static World getWorld() {
		return world;
	}
}
