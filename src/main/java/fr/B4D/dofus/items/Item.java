package fr.B4D.dofus.items;

import java.util.List;
import java.util.Map;

/**
 * The {@code Item} class represent an item in the game.<br><br>
 * An item is represented by a url, an id, a name, an image url and a type.
 * 
 * @author Lucas
 *
 */
public class Item implements Monster, Weapon, Equipment, Set, Pet, Mount, Consumable, Resource, CeremonialItem, Sidekick, Idol, Harness{
	
	//For all items
	private String url;
	private String id;
	private String name;
	private String img;
	private String type;
	
	//For some items
	private String level;
	private String description;
	private List<String> effects;
	private String conditions;
	private List<String> characteristics;
	private List<String> resistances;
	private Map<String, Integer> craft;
	
	//Sets only
	private List<String> setBonuses;
	private List<String> setTotalBonuses;

	//Pets only
	private List<String> evolutionaryEffects;

	//Idols only
	private List<String> bonuses;
	private String spells;
	
	/** Constructor of the class {@code Item}.
	 * @param url - The item's url.
	 * @param id - The item's id.
	 * @param name - The item's name.
	 * @param img - The item's image url.
	 * @param type - The item's type.
	 */
	public Item(String url, String id, String name, String img, String type) {
		this.url = url;
		this.id = id;
		this.name = name;
		this.img = img;
		this.type = type;
	}
	
	/** Returns the item's url.
	 * @return Item's url.
	 */
	public String getUrl() {
		return url;
	}
	
	/** Returns the item's id.
	 * @return Item's id.
	 */
	public String getId() {
		return id;
	}
	
	/** Returns the item's name.
	 * @return Item's name.
	 */
	public String getName() {
		return name;
	}
	
	/** Returns the item's img.
	 * @return Item's img.
	 */
	public String getImg() {
		return img;
	}
	
	/** Returns the item's type.
	 * @return Item's type.
	 */
	public String getType() {
		return type;
	}

	/** Returns the item's level.
	 * @return Item's level.
	 */
	public String getLevel() {
		return level;
	}

	/** Sets the item's level.
	 * @param level - The item's level.
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/** Returns the item's description.
	 * @return Item's description.
	 */
	public String getDescription() {
		return description;
	}

	/** Sets the item's description.
	 * @param description - The item's description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** Returns the item's effects.
	 * @return Item's effects.
	 */
	public List<String> getEffects() {
		return effects;
	}

	/** Sets the item's effects.
	 * @param effects - The item's effects.
	 */
	public void setEffects(List<String> effects) {
		this.effects = effects;
	}

	/** Returns the item's conditions.
	 * @return Item's conditions.
	 */
	public String getConditions() {
		return conditions;
	}

	/** Sets the item's conditions.
	 * @param conditions - The item's conditions.
	 */
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	/** Returns the item's characteristics.
	 * @return Item's characteristics.
	 */
	public List<String> getCharacteristics() {
		return characteristics;
	}

	/** Sets the item's characteristics.
	 * @param characteristics - The item's characteristics.
	 */
	public void setCharacteristics(List<String> characteristics) {
		this.characteristics = characteristics;
	}

	/** Returns the item's resistances.
	 * @return Item's resistances.
	 */
	public List<String> getResistances() {
		return resistances;
	}

	/** Sets the item's resistances.
	 * @param resistances - The item's resistances.
	 */
	public void setResistances(List<String> resistances) {
		this.resistances = resistances;
	}

	/** Returns the item's craft.
	 * @return Item's craft.
	 */
	public Map<String, Integer> getCraft() {
		return craft;
	}

	/** Sets the item's craft.
	 * @param craft - The item's craft.
	 */
	public void setCraft(Map<String, Integer> craft) {
		this.craft = craft;
	}

	/** Returns the item's set bonuses.
	 * @return Item's's set bonuses.
	 */
	public List<String> getSetBonuses() {
		return setBonuses;
	}

	/** Sets the item's set bonuses.
	 * @param setBonuses - The item's set bonuses.
	 */
	public void setSetBonuses(List<String> setBonuses) {
		this.setBonuses = setBonuses;
	}

	/** Returns the item's total set bonuses.
	 * @return Item's total set bonuses.
	 */
	public List<String> getSetTotalBonuses() {
		return setTotalBonuses;
	}

	/** Sets the item's set total bonuses.
	 * @param setTotalBonuses - The item's set total bonuses.
	 */
	public void setSetTotalBonuses(List<String> setTotalBonuses) {
		this.setTotalBonuses = setTotalBonuses;
	}

	/** Returns the item's evolutionary effects.
	 * @return Item's evolutionary effects.
	 */
	public List<String> getEvolutionaryEffects() {
		return evolutionaryEffects;
	}

	/** Sets the item's evolutionary effects.
	 * @param evolutionaryEffects - The item's evolutionary effects.
	 */
	public void setEvolutionaryEffects(List<String> evolutionaryEffects) {
		this.evolutionaryEffects = evolutionaryEffects;
	}

	/** Returns the item's bonuses.
	 * @return Item's bonuses.
	 */
	public List<String> getBonuses() {
		return bonuses;
	}

	/** Sets the item's bonuses.
	 * @param bonuses - The item's bonuses.
	 */
	public void setBonuses(List<String> bonuses) {
		this.bonuses = bonuses;
	}

	/** Returns the item's spells.
	 * @return Item's spells.
	 */
	public String getSpells() {
		return spells;
	}

	/** Sets the item's spells.
	 * @param spells - The item's spells.
	 */
	public void setSpells(String spells) {
		this.spells = spells;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonuses == null) ? 0 : bonuses.hashCode());
		result = prime * result + ((characteristics == null) ? 0 : characteristics.hashCode());
		result = prime * result + ((conditions == null) ? 0 : conditions.hashCode());
		result = prime * result + ((craft == null) ? 0 : craft.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((effects == null) ? 0 : effects.hashCode());
		result = prime * result + ((evolutionaryEffects == null) ? 0 : evolutionaryEffects.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((img == null) ? 0 : img.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((resistances == null) ? 0 : resistances.hashCode());
		result = prime * result + ((setBonuses == null) ? 0 : setBonuses.hashCode());
		result = prime * result + ((setTotalBonuses == null) ? 0 : setTotalBonuses.hashCode());
		result = prime * result + ((spells == null) ? 0 : spells.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (bonuses == null) {
			if (other.bonuses != null)
				return false;
		} else if (!bonuses.equals(other.bonuses))
			return false;
		if (characteristics == null) {
			if (other.characteristics != null)
				return false;
		} else if (!characteristics.equals(other.characteristics))
			return false;
		if (conditions == null) {
			if (other.conditions != null)
				return false;
		} else if (!conditions.equals(other.conditions))
			return false;
		if (craft == null) {
			if (other.craft != null)
				return false;
		} else if (!craft.equals(other.craft))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (effects == null) {
			if (other.effects != null)
				return false;
		} else if (!effects.equals(other.effects))
			return false;
		if (evolutionaryEffects == null) {
			if (other.evolutionaryEffects != null)
				return false;
		} else if (!evolutionaryEffects.equals(other.evolutionaryEffects))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (img == null) {
			if (other.img != null)
				return false;
		} else if (!img.equals(other.img))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (resistances == null) {
			if (other.resistances != null)
				return false;
		} else if (!resistances.equals(other.resistances))
			return false;
		if (setBonuses == null) {
			if (other.setBonuses != null)
				return false;
		} else if (!setBonuses.equals(other.setBonuses))
			return false;
		if (setTotalBonuses == null) {
			if (other.setTotalBonuses != null)
				return false;
		} else if (!setTotalBonuses.equals(other.setTotalBonuses))
			return false;
		if (spells == null) {
			if (other.spells != null)
				return false;
		} else if (!spells.equals(other.spells))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [url=" + url + ", id=" + id + ", name=" + name + ", img=" + img + ", type=" + type + ", level="
				+ level + ", description=" + description + ", effects=" + effects + ", conditions=" + conditions
				+ ", characteristics=" + characteristics + ", resistances=" + resistances + ", craft=" + craft
				+ ", setBonuses=" + setBonuses + ", setTotalBonuses=" + setTotalBonuses + ", evolutionaryEffects="
				+ evolutionaryEffects + ", bonuses=" + bonuses + ", spells=" + spells + "]";
	}
}
