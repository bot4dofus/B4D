package fr.B4D.dofus.items;

import java.util.List;
import java.util.Map;

/**
 * The {@code Resource} interface used to manipulate items of type Resource.
 * 
 * @author Lucas
 *
 */
public interface Resource extends ItemInterface{

	/** Return the resource's level.
	 * @return Resource's level.
	 */
	public String getLevel();

	/** Return the resource's description.
	 * @return Resource's description.
	 */
	public String getDescription();

	/** Return the resource's effects.
	 * @return Resource's effects. {@code null} if there is no effects.
	 */
	public List<String> getEffects();

	/** Return the resource's conditions.
	 * @return Resource's conditions. {@code null} if there is no conditions.
	 */
	public String getConditions();

	/** Return the resource's craft.
	 * @return Resource's craft. {@code null} if you cannot craft it.
	 */
	public Map<String, Integer> getCraft();
}
