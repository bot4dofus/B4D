package fr.B4D.building.hdv;

import fr.B4D.utils.PointF;

/**
 * The {@code HDVEquipmentCategoryFilter} represent a category filter for HDVs.
 * 
 * @author Lucas
 *
 */
public enum HDVEquipmentCategoryFilter {

	/**
	 * Amuet category filter.
	 */
	Amulet("Amulette", new PointF(0.04, 0.2645)),
	
	/**
	 * Weapon category filter.
	 */
	Weapon("Arme", new PointF(0.0888, 0.2675)),
	
	/**
	 * Ring category filter.
	 */
	Ring("Anneau", new PointF(0.1368, 0.2675)),
	
	/**
	 * Belt category filter.
	 */
	Belt("Ceinture", new PointF(0.1872, 0.2645)),
	
	/**
	 * Boot category filter.
	 */
	Boot("Bottes", new PointF(0.04, 0.3194)),
	
	/**
	 * Sheild category filter.
	 */
	Sheild("Bouclier", new PointF(0.0864, 0.3224)),
	
	/**
	 * Cosmetics category filter.
	 */
	Cosmetics("Cosmétique", new PointF(0.136, 0.3253)),
	
	/**
	 * Hat category filter.
	 */
	Hat("Chapeau", new PointF(0.184, 0.3263)),
	
	/**
	 * Cape category filter.
	 */
	Cape("Cape", new PointF(0.0432, 0.3792)),
	
	/**
	 * Trophy category filter.
	 */
	Trophy("Trophée", new PointF(0.088, 0.3792)),
	
	/**
	 * Sidekick category filter.
	 */
	Sidekick("Compagnon", new PointF(0.136, 0.3782));

	private String name;
	private PointF filterPosition;
	
	HDVEquipmentCategoryFilter(String name, PointF filterPosition){
		this.name = name;
		this.filterPosition = filterPosition;
	}
	
	/**
	 * Returns the name of the filter.
	 * @return Name of the filter.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the position of the filter in relative coordinates.
	 * @return Position of the filter.
	 */
	public PointF getFilterPosition() {
		return filterPosition;
	}
}
