package fr.B4D.programs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

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

/**
 * The {@code Breaking} class contains the breaking program.
 * 
 * @author Lucas
 *
 */
public final class Breaking extends Program{	

	/**
	 * Constructor of the breaking program.
	 */
	public Breaking() {
		super(Place.Tous, Category.Autre, "Brisage", "Identificateur d'équipement rare", null, null);
	}
	
	@Override
	public void intro(Person person) {}

	@Override
	public void outro(Person person) {}

	@Override
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

		JOptionPane.showMessageDialog(null, "Ce programme n'étant pas 100% fiable, nous vous invitons à vérifier les résultats en allant vérifier par vous même dans l'HDV.\nPensez à décocher la case \"Afficher uniquement les équipements en vente actuellement\".", "Avertissement", JOptionPane.WARNING_MESSAGE);

		notForSaleItems = notForSaleItems.stream().filter(i -> {
				Integer level = Integer.valueOf(i.getLevel());
				return (100 <= level && level <= 200);
			}
		).sorted((i1, i2) -> i2.getLevel().compareTo(i1.getLevel())).collect(Collectors.toList());

		String message = "";

		for(Item item:notForSaleItems) {
			message += item.getLevel() + " : " + item.getName() + "\n";
		}
		
		JOptionPane.showMessageDialog(null, message, "Résultats", JOptionPane.INFORMATION_MESSAGE);
	}
}
