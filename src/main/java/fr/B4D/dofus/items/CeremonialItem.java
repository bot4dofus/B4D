package fr.B4D.dofus.items;

import java.util.List;
import java.util.Map;

/**
 * The {@code CeremonialItem} interface used to manipulate items of type CeremonialItem.
 * 
 * @author Lucas
 *
 */
public interface CeremonialItem extends ItemInterface{

	/** Return the ceremonial item's level.
	 * @return Ceremonial item's level.
	 */
	public String getLevel();
	
	/** Return the ceremonial item's description.
	 * @return Ceremonial item's description.
	 */
	public String getDescription();
	
	/** Return the ceremonial item's effects.
	 * @return Ceremonial item's effects. {@code null} if there is no effects.
	 */
	public List<String> getEffects();
	
	/** Return the ceremonial item's conditions.
	 * @return Ceremonial item's conditions. {@code null} if there is no conditions.
	 */
	public String getConditions();
	
	/** Return the ceremonial item's characteristics.
	 * @return Ceremonial item's characteristics. {@code null} if there is no characteristics.
	 */
	public List<String> getCharacteristics();
	
	/** Return the ceremonial item's craft.
	 * @return Ceremonial item's craft. {@code null} if you cannot craft it.
	 */
	public Map<String, Integer> getCraft();
}
