package fr.B4D.dofus.items;

import java.util.List;
import java.util.Map;

/**
 * The {@code Weapon} interface used to manipulate items of type Weapon.
 * 
 * @author Lucas
 * 
 */
public interface Weapon extends ItemInterface{

	/** Return the weapon's level.
	 * @return Weapon's level.
	 */
	public String getLevel();

	/** Return the weapon's description.
	 * @return Weapon's description.
	 */
	public String getDescription();

	/** Return the weapon's effects.
	 * @return Weapon's effects. {@code null} if there is no effects.
	 */
	public List<String> getEffects();

	/** Return the weapon's conditions.
	 * @return Weapon's conditions. {@code null} if there is no conditions.
	 */
	public String getConditions();

	/** Return the weapon's characteristics.
	 * @return Weapon's characteristics.
	 */
	public List<String> getCharacteristics();

	/** Return the weapon's craft.
	 * @return Weapon's craft. {@code null} if you cannot craft it.
	 */
	public Map<String, Integer> getCraft();
}
