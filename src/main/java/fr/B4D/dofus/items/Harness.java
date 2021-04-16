package fr.B4D.dofus.items;

import java.util.Map;

/**
 * The {@code Harness} interface used to manipulate items of type Harness.
 * 
 * @author Lucas
 *
 */
public interface Harness extends ItemInterface{

	/** Return the harness's level.
	 * @return Harness's level.
	 */
	public String getLevel();
	
	/** Return the harness's description.
	 * @return Harness's description.
	 */
	public String getDescription();
	
	/** Return the harness's conditions.
	 * @return Harness's conditions. {@code null} if there is no conditions.
	 */
	public String getConditions();

	
	/** Return the harness's craft.
	 * @return Harness's craft. {@code null} if you cannot craft it.
	 */
	public Map<String, Integer> getCraft();
}
