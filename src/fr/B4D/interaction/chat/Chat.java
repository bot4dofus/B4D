package fr.B4D.interaction.chat;

import java.util.concurrent.ArrayBlockingQueue;

import fr.B4D.bot.B4D;
import fr.B4D.programs.ChatListener;

public class Chat extends Thread{

	private ArrayBlockingQueue<Message> messages;
	private ChatFilter filter;
	private ChatListener chatListener;
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
	
	public void addChatListener(ChatListener chatListener) {
		this.chatListener = chatListener;
	}
	
	
	public void addMessage(Message message) {
		if(filter.filter(message)) {
			synchronized(messages){
				if(!messages.offer(message))
					B4D.logger.warning("Le chat est plein, le message n'a pas pu être ajouté.");
				else
					messages.notifyAll();
			}
		}
		//else
			//B4D.logger.debug("Message non ajouté en file d'attente car ne correspond pas au filtre.");
	}

	public Message waitForMessage() {
		return waitForMessage(0);
	}
	public Message waitForMessage(long timeout) {
		if(!B4D.getSocketListener().isAlive())
			B4D.getSocketListener().start();
		
		Message message = messages.poll();
		try {
			if(message == null) {
				synchronized(messages){
					messages.wait(timeout);
					message = messages.poll();
				}
			}
		}
		catch(InterruptedException e) {
			B4D.logger.error(e);
		}
		return message;
	}
	
	public void run() {
		Message message;
		while(count != countTo) {
			message = waitForMessage(0);
			chatListener.treatMessage(message);
			count++;
		}
	}
	
	public void read(int countTo, long millis) {
		if(!B4D.getSocketListener().isAlive())
			B4D.getSocketListener().start();
		
		this.countTo = countTo;
		this.start();
		try {
			this.join(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		B4D.getSocketListener().interrupt();
		this.interrupt();
	}	
	public void read(int countTo) {
		read(countTo, 0);
	}	
	public void read(long millis) {
		read(-1, millis);
	}
}
