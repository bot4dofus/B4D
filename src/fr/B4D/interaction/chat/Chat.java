package fr.B4D.interaction.chat;

import java.util.concurrent.ArrayBlockingQueue;

import fr.B4D.bot.B4D;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

public class Chat extends Thread{

	  /***************/
	 /** ATTRIBUTS **/
	/***************/
	
	private ArrayBlockingQueue<Message> messages;
	private ChatFilter filter;
	private ChatListener chatListener;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	public Chat() {
		this(100);
	}

	public Chat(int capacity) {
		messages = new ArrayBlockingQueue<Message>(capacity);
		filter = new ChatFilter();
		chatListener = new ChatListener() {
			public void treatMessage(Message message) {
				//Do nothing by default
			}
		};
	}

	  /************/
	 /** FILTER **/
	/************/
	
	public void addPseudoFilter(String pseudo) {
		filter.setPseudo(pseudo);
	}

	public void addChannelFilter(Channel channel) {
		filter.setChannel(channel);
	}

	public void addTextFilter(String regex) {
		filter.setRegex(regex);
	}
	
	  /**********/
	 /** QUEU **/
	/**********/
	
	public void addMessage(Message message) {
		synchronized(messages){
			if(!messages.offer(message)) {
				messages.poll();
				messages.offer(message);
			}
			messages.notifyAll();
		}
	}

	  /**************/
	 /** LISTENER **/
	/**************/
	
	public void addChatListener(ChatListener chatListener) {
		this.chatListener = chatListener;
	}
	
	public void read(int countTo) throws StopProgramException, CancelProgramException {		
		int count = 0;
		Message message;
		while(count != countTo) {
			message = waitForMessage(0);
			chatListener.treatMessage(message);
			count++;
		}
	}	
	public void read() throws StopProgramException, CancelProgramException {
		read(-1);
	}
	
	  /**********************/
	 /** WAIT FOR MESSAGE **/
	/**********************/
	
	public Message waitForMessage() {
		return waitForMessage(0);
	}
	public Message waitForMessage(long timeout) {		
		Message message = null;
		try {
			B4D.logger.debug(this, "Attente d'un message");
			do {
				message = messages.poll();
				if(message == null) {
					synchronized(messages){
						messages.wait(timeout);
						message = messages.poll();
						if(message == null) 
							break;
					}
				}
			}while(!filter.filter(message));
			
			if(message == null)
				B4D.logger.debug(this, "Aucun message reçu (timeout)");
			else
				B4D.logger.debug(this, "Message reçu [" + message + "]");
		}
		catch(InterruptedException e) {
			B4D.logger.error(e);
		}
		return message;
	}

	  /***********/
	 /** OTHER **/
	/***********/
	
	public void clear() {
		synchronized(messages){
			messages.clear();
		}
	}
}
