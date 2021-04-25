package fr.B4D.interaction.chat;

import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.annotation.Nonnull;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.dofus.Dofus;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.socket.event.DofusEvent;

/**
 * The {@code Message} class represents a message in the chat.<br><br>
 * A message is defined by a pseudo, a channel, and a text.
 * 
 * @author Lucas
 *
 */
public class Message extends DofusEvent implements Serializable{
	
	private static final long serialVersionUID = 5508700695491701427L;
	
	/**
	 * Pseudo of the player which send the message.
	 */
	private String pseudo;
	
	/**
	 * Channel of the message.
	 */
	private Channel channel;
	
	/**
	 * Text of the message.
	 */
	private String text;
	
	/**
	 * Constructor of the {@code Message} class with a text to send.
	 * This is the same as {@code Message(null, null, text)}.
	 * This constructor is used to send messages not to a specific player nor specific channel.
	 * @param text - Text of the message.
	 */
	public Message(String text) {
		this(null, null, text);
	}
	
	/** Constructor of the {@code Message} class with a channel and a text to send.
	 * This is the same as {@code Message(null, channel, text)}.
	 * This constructor is used to send messages on a specific channel.
	 * @param channel - Channel of the message.
	 * @param text - Text of the message.
	 */
	public Message(Channel channel, String text) {
		this(null, channel, text);
	}
	
	/**
	 * Constructor of the {@code Message} class with a pseudo and a text to send.
	 * This is the same as {@code Message(pseudo, Channel.PRIVATE, text)}.
	 * This constructor is used to send a private messages to a specific player.
	 * @param pseudo - Pseudo of the player to send the message.
	 * @param text - Text of the message.
	 */
	public Message(String pseudo, String text) {
		this(pseudo, Channel.PRIVATE, text);
	}
	
	/**
	 * Constructor of the {@code Message} class with a pseudo, a channel and a text to send.
	 * @param pseudo - Pseudo of the player, {@code null} if no specific player.
	 * @param channel - Channel of the message, @code null} if no specific channel.
	 * @param text - Text of the message, {@code null}
	 */
	public Message(String pseudo, Channel channel, @Nonnull String text) {
		this.pseudo = pseudo;
		this.channel = channel;
		this.text = text;
	}
	
	/**
	 * Returns the pseudo of the player.
	 * @return Pseudo of the player.
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * Returns the text of the message.
	 * @return Text of the message.
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Returns the channel of the message.
	 * @return Channel of the message.
	 */
	public Channel getChannel() {
		return channel;
	}
	
	/**
	 * Sends the message with and waits for 500 ms after it.
	 * This is the same as {@code send(500)}.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void send() throws StopProgramException, CancelProgramException, B4DException {
		send(500);
	}
	
	/**
	 * Sends the message with a waiting time after it.
	 * @param millis - Time to wait in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void send(int millis) throws StopProgramException, CancelProgramException, B4DException {
		if (channel == null) {
			if (pseudo == null) {
				send(text, millis);
			}
			else {
				send(pseudo + " " + text, millis);
			}
		}
		else {
			if (channel == Channel.PRIVATE)
				send(channel.getPrefix() + " " + pseudo + " " + text, millis);
			else
				send(channel.getPrefix() + " " + text, millis);
		}
	}
	
	/**
	 * Sends a message in the chat with a waiting time after it.
	 * @param text - Text to send.
	 * @param millis - Time to wait in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	private void send(String text, int millis) throws StopProgramException, CancelProgramException, B4DException {
		B4D.mouse.chatClick();
		B4D.keyboard.writeKeyboard(text, millis);
		B4D.keyboard.sendKey(KeyEvent.VK_ENTER, 100);
	}
	
	/**
	 * Replies to a player in the private channel and waits for 500 ms after it.
	 * This is the same as {@code reply(text, 500)}.
	 * If the pseudo of the player is {@code null}, it does nothing.
	 * @param text - Text to reply.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void reply(String text) throws StopProgramException, CancelProgramException, B4DException {
		reply(text, 500);
	}

	/**
	 * Replies to a player in the private channel with a waiting time after it.
	 * If the pseudo of the player is {@code null}, it does nothing.
	 * @param text - Text to reply.
	 * @param millis - Time to wait in ms.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unexpected error occurred.
	 */
	public void reply(String text, int millis) throws StopProgramException, CancelProgramException, B4DException {
		if(pseudo != null) {
			if(channel == Channel.BUSINESS || channel == Channel.RECRUITMENT)
				channel = Channel.PRIVATE;
			this.text = text;
			send(millis);
		}
	}
	
	/**
	 * Waits for a reply from the player with no timeout.
	 * If the pseudo is known, the answer will be detected on all the possible channels, otherwise only the private channel will be granted.
	 * This is the same as {@code waitForReply(0)}.
	 * @return Reply of the player.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public Message waitForReply() throws StopProgramException, CancelProgramException {
		return waitForReply(0);
	}
	
	/**
	 * Waits for a reply from the player with a timeout.
	 * If the pseudo is known, the answer will be detected on all the possible channels, otherwise only the private channel will be granted.
	 * @param timeout - Timeout in ms.
	 * @return Reply of the player, {@code null} if timeout.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public Message waitForReply(long timeout) throws StopProgramException, CancelProgramException {
		B4D.logger.debug("Attente d'une réponse");
		if(pseudo != null)
			Dofus.getInstance().getChat().addPseudoFilter(pseudo);
		else
			Dofus.getInstance().getChat().addChannelFilter(Channel.PRIVATE);
		
		Message message = Dofus.getInstance().getChat().waitForMessage(timeout);
		
		Dofus.getInstance().getChat().addPseudoFilter(null);
		Dofus.getInstance().getChat().addChannelFilter(null);
		return message;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if(pseudo != null)
			return "[" + channel.toString() +"][" + pseudo + "] " + text;
		else
			return "[" + channel.toString() +"] " + text;
	}
}
