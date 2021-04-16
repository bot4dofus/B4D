package fr.B4D.dofus.items;

import java.util.List;

/**
 * The {@code Monster} interface used to manipulate items of type Monster.
 * 
 * @author Lucas
 *
 */
public interface Monster extends ItemInterface{

	/** Return the monster's range level.
	 * @return Monster's range level.
	 */
	public String getLevel();

	/** Return the monster's characteristics.
	 * @return Monster's characteristics.
	 */
	public List<String> getCharacteristics();

	/** Return the monster's resistances.
	 * @return Monster's resistances.
	 */
	public List<String> getResistances();
}
