package fr.B4D.dofus.items;

import java.util.List;

/**
 * The {@code Sidekick} interface used to manipulate items of type Sidekick.
 * 
 * @author Lucas
 */
public interface Sidekick extends ItemInterface{
	
	/** Return the sidekick's description.
	 * @return Sidekick's description.
	 */
	public String getDescription();
	
	/** Return the sidekick's characteristics.
	 * @return Sidekick's characteristics.
	 */
	public List<String> getCharacteristics();
}
