package fr.B4D.dofus.items;

import java.util.List;
import java.util.Map;

/**
 * The {@code Idol} interface used to manipulate items of type Idol.
 * 
 * @author Lucas
 *
 */
public interface Idol extends ItemInterface{
	
	/** Return the idol's level.
	 * @return Idol's level.
	 */
	public String getLevel();
	
	/** Return the idol's description.
	 * @return Idol's description.
	 */
	public String getDescription();
	
	/** Return the idol's bonuses.
	 * @return Idol's bonuses.
	 */
	public List<String> getBonuses();
	
	/** Return the idol's spells.
	 * @return Idol's spells.
	 */
	public String getSpells();
	
	/** Return the idol's craft.
	 * @return Idol's craft.
	 */
	public Map<String, Integer> getCraft();
}
