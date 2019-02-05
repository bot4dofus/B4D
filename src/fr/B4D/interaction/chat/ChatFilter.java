package fr.B4D.interaction.chat;

/** La classe {@code ChatFilter} représente un filtre de chat.<br/>
 * Un filtre de chat est défini par un pseudo, un canal et une expression régulière.
 */
public class ChatFilter {

	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private String pseudo, regex;
	private Channel channel;

	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructeur de la classe {@code ChatFilter}.
	 */
	public ChatFilter() {}

	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/
	
	/** Retourne le pseudo du filtre.
	 * @return Pseudo du filtre.
	 */
	public String getPseudo() {
		return pseudo;
	}

	/** Modifie le pseudo du filtre. Seul les messages provenant de ce pseudo traverseront le filtre.
	 * @param pseudo - Nouveau pseudo du filtre. {@code null} pour retirer le filtre.
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/** Retourne le canal du filtre.
	 * @return Canal du filtre.
	 */
	public Channel getChannel() {
		return channel;
	}

	/** Modifie le canal du filtre. Seul les messages provenant de ce canal traverseront le filtre.
	 * @param channel - Nouveau canal du filtre. {@code null} pour retirer le filtre.
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	/** Retourne l'expression régulière du filtre.
	 * @return Expression régulière du filtre.
	 */
	public String getRegex() {
		return regex;
	}

	/** Modifie le canal du filtre. Seul les messages contenants cette chaine de caratère traverseront le filtre.
	 * @param regex - Nouvelle expression régulière du filtre. {@code null} pour retirer le filtre.
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}

	  /*************/
	 /** METHODS **/
	/*************/
	
	/** Permet de savoir si un message passe à travers le filtre.
	 * @param message - Message a tester.
	 * @return {@code true} si le message passe à travers le filtre et {@code false} sinon.
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
