package fr.B4D.dofus.items;

import java.util.List;

/**
 * The {@code Pet} interface used to manipulate items of type Pet.
 * 
 * @author Lucas
 *
 */
public interface Pet extends ItemInterface{

	/** Return the pet's level.
	 * @return Pet's level.
	 */
	public String getLevel();

	/** Return the pet's description.
	 * @return Pet's description.
	 */
	public String getDescription();

	/** Return the pet's conditions.
	 * @return Pet's conditions. {@code null} if there is no evolutionary effects.
	 */
	public String getConditions();

	/** Return the pet's evolutionary effects.
	 * @return Pet's evolutionary effects. {@code null} if there is no evolutionary effects.
	 */
	public List<String> getEvolutionaryEffects();
}
