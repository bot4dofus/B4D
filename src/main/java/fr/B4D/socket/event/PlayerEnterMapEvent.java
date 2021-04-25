package fr.B4D.socket.event;

/**
 * A {@code PlayerEnterMapEvent} occurs when an other player enter the current map.
 * 
 * @author Lucas
 *
 */
public class PlayerEnterMapEvent extends DofusEvent{
	
	/**
	 * Pseudo of the player entering the map.
	 */
	private String pseudo;
	
	/**
	 * Constructs a {@code PlayerEnterMapEvent}.
	 * @param pseudo Name of the player entering the map.
	 */
	public PlayerEnterMapEvent(String pseudo) {
		super();
		this.pseudo = pseudo;
	}

	/**
	 * Returns the pseudo of the player entering the map.
	 * @return Pseudo of the player.
	 */
	public String getPseudo() {
		return pseudo;
	}
}
