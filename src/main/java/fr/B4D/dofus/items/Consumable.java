package fr.B4D.dofus.items;

import java.util.List;
import java.util.Map;

/**
 * The {@code Consumable} interface used to manipulate items of type Consumable.
 * 
 * @author Lucas
 *
 */
public interface Consumable extends ItemInterface{

	/** Return the consumable's level.
	 * @return Consumable's level.
	 */
	public String getLevel();
	
	/** Return the consumable's description.
	 * @return Consumable's description.
	 */
	public String getDescription();
	
	/** Return the consumable's effects.
	 * @return Consumable's effects. {@code null} if there is no effects.
	 */
	public List<String> getEffects();
	
	/** Return the consumable's conditions.
	 * @return Consumable's conditions. {@code null} if there is no conditions.
	 */
	public String getConditions();
	
	/** Return the consumable's craft.
	 * @return Consumable's craft. {@code null} if you cannot craft it.
	 */
	public Map<String, Integer> getCraft();
}
