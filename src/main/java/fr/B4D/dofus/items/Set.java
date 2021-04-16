package fr.B4D.dofus.items;

import java.util.List;

/**
 * The {@code Set} interface used to manipulate items of type Set.
 * 
 * @author Lucas
 *
 */
public interface Set extends ItemInterface{

	/** Return the set's level.
	 * @return Set's level.
	 */
	public String getLevel();

	/** Return the set's set bonuses.
	 * @return Set's set bonuses.
	 */
	public List<String> getSetBonuses();

	/** Return the set's set total bonuses.
	 * @return Set's set total bonuses. {@code null} if there is no set total bonuses.
	 */
	public List<String> getSetTotalBonuses();
}
