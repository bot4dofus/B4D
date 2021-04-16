package fr.B4D.dofus.items;

/**
 * Define the accessible methods for an item.
 * 
 * @author Lucas
 *
 */
public interface ItemInterface {
	
	/** Return the item's url.
	 * @return Item's url.
	 */
	public String getUrl();
	
	/** Return the item's id.
	 * @return Item's id.
	 */
	public String getId();
	
	/** Return the item's name.
	 * @return Item's name.
	 */
	public String getName();
	
	/** Return the item's image url.
	 * @return Item's image url.
	 */
	public String getImg();
	
	/** Return the item's type.
	 * @return Item's type.
	 */
	public String getType();
}
