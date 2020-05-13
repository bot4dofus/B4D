package fr.B4D.building.hdv;

import fr.B4D.utils.PointF;

public enum HDVEquipmentCategoryFilter {

	Amulet("Amulette", new PointF(0.04, 0.2645)),
	Weapon("Arme", new PointF(0.0888, 0.2675)),
	Ring("Anneau", new PointF(0.1368, 0.2675)),
	Belt("Ceinture", new PointF(0.1872, 0.2645)),
	Boot("Bottes", new PointF(0.04, 0.3194)),
	Sheild("Bouclier", new PointF(0.0864, 0.3224)),
	Cosmetics("Cosmétique", new PointF(0.136, 0.3253)),
	Hat("Chapeau", new PointF(0.184, 0.3263)),
	Cape("Cape", new PointF(0.0432, 0.3792)),
	Trophy("Trophée", new PointF(0.088, 0.3792)),
	Sidekick("Compagnon", new PointF(0.136, 0.3782));

	private String name;
	private PointF filterPosition;
	
	HDVEquipmentCategoryFilter(String name, PointF filterPosition){
		this.name = name;
		this.filterPosition = filterPosition;
	}
	
	public String getName() {
		return name;
	}
	
	public PointF getFilterPosition() {
		return filterPosition;
	}
}
