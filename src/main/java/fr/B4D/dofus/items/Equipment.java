package fr.B4D.dofus.items;

import java.util.List;
import java.util.Map;

/**
 * The {@code Equipment} interface used to manipulate items of type Equipment.
 * 
 * @author Lucas
 *
 */
public interface Equipment extends ItemInterface{

	/** Return the equipment's level.
	 * @return Equipment's level.
	 */
	public String getLevel();

	/** Return the equipment's description.
	 * @return Equipment's description.
	 */
	public String getDescription();

	/** Return the equipment's effects.
	 * @return Equipment's effects. {@code null} if there is no effects.
	 */
	public List<String> getEffects();

	/** Return the equipment's conditions.
	 * @return Equipment's conditions. {@code null} if there is no conditions.
	 */
	public String getConditions();

	/** Return the equipment's craft.
	 * @return Equipment's craft. {@code null} if you cannot craft it.
	 */
	public Map<String, Integer> getCraft();
}
