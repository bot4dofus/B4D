package fr.B4D.interaction.chat;

/** Signal que le type de canal est inconnu.
*
*/
public class UnknowChannelException extends Exception {

	private static final long serialVersionUID = 924080672225339433L;
	
	/** Constructeur de l'exception {@code UnknowChannelException}.
	 * @param key - Entier représentant le canal.
	 */
	public UnknowChannelException(Integer key) {
		super("Unknow channel " + key);
	}
}
