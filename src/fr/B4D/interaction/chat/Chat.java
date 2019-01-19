package fr.B4D.interaction.chat;

import java.util.concurrent.ArrayBlockingQueue;

import fr.B4D.bot.B4D;
import fr.B4D.programs.ChatListener;

public class Chat extends Thread{

	private ArrayBlockingQueue<Message> messages;
	private ChatFilter filter;
	private ChatListener chatListener;
	private Object lock = new Object();
	private int countTo = -1, count = 0;
	
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

	public void addPseudoFilter(String pseudo) {
		filter.setPseudo(pseudo);
	}

	public void addChannelFilter(Channel channel) {
		filter.setChannel(channel);
	}

	public void addTextFilter(String regex) {
		filter.setRegex(regex);
	}

	public void removeFilter() {
		filter = new ChatFilter();
	}

	public void addMessage(Message message) {
		if(filter.filter(message)) {
			if(!messages.offer(message))
				B4D.logger.warning("Le chat est plein, le message n'a pas pu être ajouté.");
			else {
				B4D.logger.debug("Nouveau message en file d'attente.");
				synchronized(lock){
					lock.notifyAll();
				}
			}
		}
	}

	public void addChatListener(ChatListener chatListener) {
		this.chatListener = chatListener;
	}

	public void run() {
		Message message;
		while(count != countTo) {
			try {
				message = messages.poll();
				if(message == null) {
					synchronized(lock){
						lock.wait();
						message = messages.poll();
					}
				}
				chatListener.treatMessage(message);
				count++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void read(int countTo, long millis) {
		B4D.logger.debug("CHAT : Lancement du tread.");
		this.countTo = countTo;
		B4D.getSocketListener().start();
		this.start();
		try {
			this.join(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		B4D.getSocketListener().interrupt();
		B4D.logger.debug("CHAT : Arret du tread.");
	}	
	public void read(int countTo) {
		read(countTo, 0);
	}	
	public void read(long millis) {
		read(-1, millis);
	}
}
