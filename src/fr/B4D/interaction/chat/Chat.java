package fr.B4D.interaction.chat;

import java.util.concurrent.ArrayBlockingQueue;

import fr.B4D.bot.B4D;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

public class Chat extends Thread{

	private ArrayBlockingQueue<Message> messages;
	private ChatFilter filter;
	private ChatListener chatListener;
	
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
		synchronized(messages){
			if(!messages.offer(message)) {
				messages.poll();
				messages.offer(message);
			}
			messages.notifyAll();
		}
	}

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
					}
				}
			}while(!filter.filter(message));
			
			if(message == null)
				B4D.logger.debug(this, "Aucun message re�u (timeout)");
			else
				B4D.logger.debug(this, "Message re�u [" + message + "]");
		}
		catch(InterruptedException e) {
			B4D.logger.error(e);
		}
		return message;
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
}
