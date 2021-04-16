package fr.B4D.dofus.items;

import java.util.List;

/**
 * The {@code Mount} interface used to manipulate items of type Mount.
 * 
 * @author Lucas
 *
 */
public interface Mount extends ItemInterface{

	/** Return the Mount's effects.
	 * @return Mount's effects. {@code null} if there is no effects.
	 */
	public List<String> getEffects();

	/** Return the Mount's characteristics.
	 * @return Mount's characteristics.
	 */
	public List<String> getCharacteristics();
}
