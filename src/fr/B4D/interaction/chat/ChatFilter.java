package fr.B4D.interaction.chat;

public class ChatFilter {
	
	private String pseudo, regex;
	private Channel channel;
	
	public ChatFilter() {}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
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
