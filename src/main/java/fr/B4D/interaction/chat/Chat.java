package fr.B4D.interaction.chat;

import java.util.concurrent.ArrayBlockingQueue;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/**
 * The {@code Chat} class represents the Dofus chat.<br><br>
 * A chat is defined by message queue, a filter and a listener.
 * 
 * @author Lucas
 *
 */
public class Chat{
	
	/**
	 * Queue of messages in the chat.
	 */
	private ArrayBlockingQueue<Message> messages;
	
	/**
	 * Filter of the chat. It is used to ignore useless messages.
	 */
	private ChatFilter filter;
	
	/**
	 * Listener specifying what to do when a message, respecting the filter criteria, is received.
	 */
	private ChatListener chatListener;
	
	/**
	 * Builder of the {@code Chat} class with a capacity of 100 messages.
	 * This is the same as {@code Chat(100)}.
	 */
	public Chat() {
		this(100);
	}

	/** Builder of the {@code Chat} class
	 * @param capacity - Maximum number of messages saved in the queue.
	 */
	public Chat(int capacity) {
		messages = new ArrayBlockingQueue<Message>(capacity);
		filter = new ChatFilter();
		chatListener = new ChatListener() {
			public void messageReceived(Message message) {
				//Do nothing by default
			}
		};
	}
	
	/**
	 * Defines the listener.
	 * @param chatListener - Listener specifying what to do when a message is received.
	 */
	public void setChatListener(ChatListener chatListener) {
		this.chatListener = chatListener;
	}
	
	/**
	 * Add pseudo filter. Only the messages coming from this player will be processed.
	 * @param pseudo - Pseudo of the player, {@code null} to remove the old filter.
	 */
	public void addPseudoFilter(String pseudo) {
		synchronized(filter) {
			filter.setPseudo(pseudo);
		}
	}

	/**
	 * Add a channel filter. Only the messages on this channel will be processed.
	 * @param channel - Channel of the message, {@code null} to remove the old filter.
	 */
	public void addChannelFilter(Channel channel) {		
		synchronized(filter) {
			filter.setChannel(channel);
		}
	}

	/**
	 * Add a regex filter. Only the messages containing the substring will be processed.
	 * @param regex - Regex that the message must contain, {@code null} to remove the old filter.
	 */
	public void addTextFilter(String regex) {
		synchronized(filter) {
			filter.setRegex(regex);
		}
	}
	
	/**
	 * Add a message in the queue. If the message doesn't respect the filter criteria, it will dot be added.
	 * @param message - Message to add in the queue.
	 */
	public void addMessage(Message message) {
		boolean pass;
		synchronized(filter) {
			pass = filter.filter(message);
		}
		if(pass) {
			synchronized(messages){
				if(!messages.offer(message)) {
					messages.poll();
					messages.offer(message);
				}
				messages.notifyAll();
			}
		}
	}
	
	/**
	 * Process an infinite number of messages. This is the same as {@code read(-1)}.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void read() throws StopProgramException, CancelProgramException, B4DException {
		read(-1);
	}
	
	/**
	 * Process a finite number of messages.
	 * @param countTo - Number of messages to process.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void read(int countTo) throws StopProgramException, CancelProgramException, B4DException {		
		int count = 0;
		Message message;
		while(count != countTo) {
			message = waitForMessage(0);
			chatListener.messageReceived(message);
			count++;
		}
	}
	
	/**
	 * Wait for a message with no timeout. This is the same as {@code waitForMessage(0)}.
	 * @return Oldest messages respecting the filter criteria.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public Message waitForMessage() throws StopProgramException, CancelProgramException {
		return waitForMessage(0);
	}
	/**
	 * Wait for a message with a timeout.
	 * @param timeout - Timeout in ms.
	 * @return Oldest messages respecting the filter criteria, {@code null} if timeout.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public Message waitForMessage(long timeout) throws StopProgramException, CancelProgramException {		
		Message message = null;

		B4D.logger.debug("Attente d'un message");
		message = messages.poll();
		if(message == null) {
			synchronized(messages){
				B4D.wait.waitOnObject(messages, timeout);
				message = messages.poll();
			}
		}
		if(message == null)
			B4D.logger.debug("Aucun message reçu (timeout)");
		else
			B4D.logger.debug("Message reçu [" + message + "]");

		return message;
	}
	
	/**
	 * Clears the queue.
	 */
	public void clear() {
		synchronized(messages){
			messages.clear();
		}
	}
}
