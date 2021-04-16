package fr.B4D.interaction.chat;

/**
 * The {@code ChatFilter} class represents a chat filter.<br><br>
 * A filter is defined by a pseudo, a channel and a regex.
 * 
 * @author Lucas
 *
 */
public class ChatFilter {

	
	/**
	 * Pseudo of the player the message must come from.
	 */
	private String pseudo;

	/**
	 * Channel the message must be sent on.
	 */
	private Channel channel;
	
	/**
	 * Substring the message must contain.
	 */
	private String regex;
	
	/**
	 * Constructor of the {@code ChatFilter} class.
	 */
	public ChatFilter() {
		super();
	}
	
	/**
	 * Returns the pseudo of the filter.
	 * @return Pseudo of the filter.
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Defines the pseudo of the filter. Only the messages coming from this player will be processed.
	 * @param pseudo - Pseudo of the filter. {@code null} to remove the old filter.
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * Returns the channel of the filter.
	 * @return Channel of the filter.
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * Defines the channel of the filter. Only the messages on this channel will be processed.
	 * @param channel - Channel of the filter. {@code null} to remove the old filter.
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	/**
	 * Returns the regex of the filter.
	 * @return Regex of the filter.
	 */
	public String getRegex() {
		return regex;
	}
	
    /**
	 * Defines the regex of the filter. Only the messages containing the substring will be processed.
	 * @param regex - Regex that the message must contain, {@code null} to remove the old filter.
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	/**
	 * Checks whether a message pass the filter.
	 * @param message - Message to test.
	 * @return {@code true} if the message respect all the criteria, {@code false} otherwise.
	 */
	public boolean filter(Message message) {
		if(pseudo != null) {
			if(!message.getPseudo().equals(pseudo))
				return false;
		}
		if(channel != null) {
			if(!message.getChannel().equals(channel))
				return false;
		}
		if(regex != null) {
			if(!message.getText().contains(regex))
				return false;
		}
		return true;
	}
}
