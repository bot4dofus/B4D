package fr.B4D.programs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.hdv.HDVEquipmentCategoryFilter;
import fr.B4D.building.hdv.HDVEquipments;
import fr.B4D.dofus.Dofus;
import fr.B4D.dofus.items.Item;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;

/**
 * The {@code Breaking} class contains the breaking program.
 * 
 * @author Lucas
 *
 */
public final class Breaking {	

	/** 
	 * Breaking program.
	 */
	public final static Program BREAKING = new Program(Place.Tous, Category.Autre, "Brisage", "Identificateur d'équipement rare", null, null, new ProgramInterface() {
		public void intro(Person person) {}
		public void outro(Person person) {}
		public void cycle(Person person) throws B4DException {
			
			HDVEquipments.BONTA.open(person);
			
			JSONObject database = Dofus.getInstance().getDatabase().loadDatabase();
			
			List<Item> knownItems = new ArrayList<Item>();
			knownItems.addAll(Dofus.getInstance().getDatabase().findItemsByCategory("equipments", database));
			
			knownItems = knownItems.stream().filter(i -> 
				i.getType().contains("Amulette") ||
				i.getType().contains("Anneau") ||
				i.getType().contains("Ceinture") ||
				i.getType().contains("Bottes") ||
				i.getType().contains("Bouclier") ||
				i.getType().contains("Chapeau") ||
				i.getType().contains("Cape")
				).collect(Collectors.toList());
			
			//knownItems.addAll(Dofus.getInstance().getDatabase().findItemsByCategory("weapons", database));
			
			List<Item> forSaleItems = new ArrayList<Item>();
			forSaleItems.addAll(HDVEquipments.BONTA.enableCategoryFilter(HDVEquipmentCategoryFilter.Amulet));
			HDVEquipments.BONTA.disableCategoryFilter(HDVEquipmentCategoryFilter.Amulet);
			/*forSaleItems.addAll(HDVEquipments.BONTA.enableCategoryFilter(HDVEquipmentCategoryFilter.Weapon));
			HDVEquipments.BONTA.disableCategoryFilter(HDVEquipmentCategoryFilter.Weapon);*/
			forSaleItems.addAll(HDVEquipments.BONTA.enableCategoryFilter(HDVEquipmentCategoryFilter.Ring));
			HDVEquipments.BONTA.disableCategoryFilter(HDVEquipmentCategoryFilter.Ring);
			forSaleItems.addAll(HDVEquipments.BONTA.enableCategoryFilter(HDVEquipmentCategoryFilter.Belt));
			HDVEquipments.BONTA.disableCategoryFilter(HDVEquipmentCategoryFilter.Belt);
			forSaleItems.addAll(HDVEquipments.BONTA.enableCategoryFilter(HDVEquipmentCategoryFilter.Boot));
			HDVEquipments.BONTA.disableCategoryFilter(HDVEquipmentCategoryFilter.Boot);
			forSaleItems.addAll(HDVEquipments.BONTA.enableCategoryFilter(HDVEquipmentCategoryFilter.Sheild));
			HDVEquipments.BONTA.disableCategoryFilter(HDVEquipmentCategoryFilter.Sheild);
			forSaleItems.addAll(HDVEquipments.BONTA.enableCategoryFilter(HDVEquipmentCategoryFilter.Hat));
			HDVEquipments.BONTA.disableCategoryFilter(HDVEquipmentCategoryFilter.Hat);
			forSaleItems.addAll(HDVEquipments.BONTA.enableCategoryFilter(HDVEquipmentCategoryFilter.Cape));
			HDVEquipments.BONTA.disableCategoryFilter(HDVEquipmentCategoryFilter.Cape);

			List<String> forSaleItemNames = forSaleItems.stream().map(i -> i.getName()).collect(Collectors.toList());
			List<Item> notForSaleItems = new ArrayList<Item>();
			
			for(Item item:knownItems) {
				if(!forSaleItemNames.contains(item.getName())) {
					notForSaleItems.add(item);
				}
			}
			
			for(Item item:notForSaleItems) {
				Integer level = Integer.valueOf(item.getLevel());
				if(100 <= level && level <= 200)
					System.out.println(item);
			}

			System.out.println("knownItems = " + knownItems.size());
			System.out.println("forSaleItems = " + forSaleItems.size());
			System.out.println("notForSaleItems = " + notForSaleItems.size());
			
		}
	});
}
