package fr.B4D.dofus;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.B4D.bot.B4DException;
import fr.B4D.dofus.items.CeremonialItem;
import fr.B4D.dofus.items.Consumable;
import fr.B4D.dofus.items.Equipment;
import fr.B4D.dofus.items.Harness;
import fr.B4D.dofus.items.Idol;
import fr.B4D.dofus.items.Item;
import fr.B4D.dofus.items.Monster;
import fr.B4D.dofus.items.Mount;
import fr.B4D.dofus.items.Pet;
import fr.B4D.dofus.items.Resource;
import fr.B4D.dofus.items.Set;
import fr.B4D.dofus.items.Sidekick;
import fr.B4D.dofus.items.Weapon;

@SuppressWarnings("javadoc")
public class DofusDatabaseTest {
	
	private static DofusDatabase dofusDatabase;
	
	@BeforeClass
	public static void init() {
		dofusDatabase = new DofusDatabase("/data/items.fr.json");
	}

	@Test
	public void testFindItems() throws B4DException {
		List<Item> itemsByName = dofusDatabase.findItemsByName("Dagob mineure");
		List<Item> itemsById = dofusDatabase.findItemsById("12");
		
		for (Item item:itemsByName)
			Assert.assertNotNull(item);

		for (Item item:itemsById)
			Assert.assertNotNull(item);

		Assert.assertEquals(itemsByName.size(), 2);
		Assert.assertEquals(itemsById.size(), 4);
				
		Assert.assertTrue(DofusDatabase.isSet(itemsById.get(0).getUrl()));
		Assert.assertTrue(DofusDatabase.isMount(itemsById.get(1).getUrl()));
		Assert.assertTrue(DofusDatabase.isIdol(itemsById.get(2).getUrl()));
		Assert.assertTrue(DofusDatabase.isSidekick(itemsById.get(3).getUrl()));
	}

	
	@Test
	public void testFindMonster() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/650-abrakne");
		Monster monsterByUrl = dofusDatabase.findMonsterByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/monstres/650-abrakne");
		Monster monsterById = dofusDatabase.findMonsterById("650");
		Monster monsterByName = dofusDatabase.findMonsterByName("Abrakne");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(monsterByUrl);
		Assert.assertNotNull(monsterById);
		Assert.assertNotNull(monsterByName);

		Assert.assertEquals(itemByUrl, monsterByUrl);
		Assert.assertEquals(itemByUrl, monsterById);
		Assert.assertEquals(itemByUrl, monsterByName);
	}
	
	@Test
	public void testFindWeapon() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/armes/19267-lance-corrompue");
		Weapon weaponByUrl = dofusDatabase.findWeaponByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/armes/19267-lance-corrompue");
		Weapon weaponById = dofusDatabase.findWeaponById("19267");
		Weapon weaponByName = dofusDatabase.findWeaponByName("Lance corrompue");
		
		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(weaponByUrl);
		Assert.assertNotNull(weaponById);
		Assert.assertNotNull(weaponByName);
		
		Assert.assertEquals(itemByUrl, weaponByUrl);
		Assert.assertEquals(itemByUrl, weaponById);
		Assert.assertEquals(itemByUrl, weaponByName);
	}
	
	@Test
	public void testFindEquipment() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/equipements/15743-ceinture-vortex");	
		Equipment equipmentByUrl = dofusDatabase.findEquipmentByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/equipements/15743-ceinture-vortex");
		Equipment equipmentById = dofusDatabase.findEquipmentById("15743");
		Equipment equipmentByName = dofusDatabase.findEquipmentByName("Ceinture de Vortex");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(equipmentByUrl);
		Assert.assertNotNull(equipmentById);
		Assert.assertNotNull(equipmentByName);
		
		Assert.assertEquals(itemByUrl, equipmentById);
		Assert.assertEquals(itemByUrl, equipmentByName);
		Assert.assertEquals(itemByUrl, equipmentById);
		Assert.assertEquals(itemByUrl, equipmentByName);
	}
	
	@Test
	public void testFindSet() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/panoplies/270-panoplie-comte-harebourg");
		Set setByUrl = dofusDatabase.findSetByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/panoplies/270-panoplie-comte-harebourg");
		Set setById = dofusDatabase.findSetById("270");
		Set setByName = dofusDatabase.findSetByName("Panoplie du Comte Harebourg");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(setByUrl);
		Assert.assertNotNull(setById);
		Assert.assertNotNull(setByName);

		Assert.assertEquals(itemByUrl, setByUrl);
		Assert.assertEquals(itemByUrl, setById);
		Assert.assertEquals(itemByUrl, setByName);
	}
	
	@Test
	public void testFindPet() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/familiers/13679-toukancre");
		Pet petByUrl = dofusDatabase.findPetByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/familiers/13679-toukancre");
		Pet petById = dofusDatabase.findPetById("13679");
		Pet petByName = dofusDatabase.findPetByName("Toukancre");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(petByUrl);
		Assert.assertNotNull(petById);
		Assert.assertNotNull(petByName);

		Assert.assertEquals(itemByUrl, petByUrl);
		Assert.assertEquals(itemByUrl, petById);
		Assert.assertEquals(itemByUrl, petByName);
	}
	
	@Test
	public void testFindMount() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/montures/46-dragodinde-doree-rousse");
		Mount mountByUrl = dofusDatabase.findMountByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/montures/46-dragodinde-doree-rousse");
		Mount mountById = dofusDatabase.findMountById("46");
		Mount mountByName = dofusDatabase.findMountByName("Dragodinde Dorée et Rousse");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(mountByUrl);
		Assert.assertNotNull(mountById);
		Assert.assertNotNull(mountByName);

		Assert.assertEquals(itemByUrl, mountByUrl);
		Assert.assertEquals(itemByUrl, mountById);
		Assert.assertEquals(itemByUrl, mountByName);
	}
	
	@Test
	public void testFindConsumable() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/consommables/20962-elixir-uchronique");
		Consumable consumableByUrl = dofusDatabase.findConsumableByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/consommables/20962-elixir-uchronique");
		Consumable consumableById = dofusDatabase.findConsumableById("20962");
		Consumable consumableByName = dofusDatabase.findConsumableByName("Élixir uchronique");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(consumableByUrl);
		Assert.assertNotNull(consumableById);
		Assert.assertNotNull(consumableByName);

		Assert.assertEquals(itemByUrl, consumableByUrl);
		Assert.assertEquals(itemByUrl, consumableById);
		Assert.assertEquals(itemByUrl, consumableByName);
	}
	
	@Test
	public void testFindResource() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/ressources/14143-fermeture-eclair");
		Resource resourceByUrl = dofusDatabase.findResourceByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/ressources/14143-fermeture-eclair");
		Resource resourceById = dofusDatabase.findResourceById("14143");
		Resource resourceByName = dofusDatabase.findResourceByName("Fermeture éclair");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(resourceByUrl);
		Assert.assertNotNull(resourceById);
		Assert.assertNotNull(resourceByName);

		Assert.assertEquals(itemByUrl, resourceByUrl);
		Assert.assertEquals(itemByUrl, resourceById);
		Assert.assertEquals(itemByUrl, resourceByName);
	}
	
	@Test
	public void testFindCeremonialItem() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/objets-d-apparat/2518-cape-vioutifoule");
		CeremonialItem ceremonialItemByUrl = dofusDatabase.findCeremonialItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/objets-d-apparat/2518-cape-vioutifoule");
		CeremonialItem ceremonialItemById = dofusDatabase.findCeremonialItemById("2518");
		CeremonialItem ceremonialItemByName = dofusDatabase.findCeremonialItemByName("Cape Vioutifoule");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(ceremonialItemByUrl);
		Assert.assertNotNull(ceremonialItemById);
		Assert.assertNotNull(ceremonialItemByName);

		Assert.assertEquals(itemByUrl, ceremonialItemByUrl);
		Assert.assertEquals(itemByUrl, ceremonialItemById);
		Assert.assertEquals(itemByUrl, ceremonialItemByName);
	}
	
	@Test
	public void testFindSidekick() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/compagnons/16-toxine");
		Sidekick sidekickByUrl = dofusDatabase.findSidekickByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/compagnons/16-toxine");
		Sidekick sidekickById = dofusDatabase.findSidekickById("16");
		Sidekick sidekickByName = dofusDatabase.findSidekickByName("Toxine");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(sidekickByUrl);
		Assert.assertNotNull(sidekickById);
		Assert.assertNotNull(sidekickByName);

		Assert.assertEquals(itemByUrl, sidekickByUrl);
		Assert.assertEquals(itemByUrl, sidekickById);
		Assert.assertEquals(itemByUrl, sidekickByName);
	}
	
	@Test
	public void testFindIdol() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/idoles/22-dagob-mineure");
		Idol idolByUrl = dofusDatabase.findIdolByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/idoles/22-dagob-mineure");
		Idol idolById = dofusDatabase.findIdolById("22");
		Idol idolByName = dofusDatabase.findIdolByName("Dagob mineure");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(idolByUrl);
		Assert.assertNotNull(idolById);
		Assert.assertNotNull(idolByName);
		
		Assert.assertEquals(itemByUrl, idolByUrl);
		Assert.assertEquals(itemByUrl, idolById);
		Assert.assertEquals(itemByUrl, idolByName);
	}
	
	@Test
	public void testFindHarness() throws B4DException {
		Item itemByUrl = dofusDatabase.findItemByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/harnachements/21481-harnachement-muldo-roublard");
		Harness harnessByUrl = dofusDatabase.findHarnessByUrl("https://www.dofus.com/fr/mmorpg/encyclopedie/harnachements/21481-harnachement-muldo-roublard");
		Harness harnessById = dofusDatabase.findHarnessById("21481");
		Harness harnessByName = dofusDatabase.findHarnessByName("Harnachement de Muldo Roublard");

		Assert.assertNotNull(itemByUrl);
		Assert.assertNotNull(harnessByUrl);
		Assert.assertNotNull(harnessById);
		Assert.assertNotNull(harnessByName);

		Assert.assertEquals(itemByUrl, harnessByUrl);
		Assert.assertEquals(itemByUrl, harnessById);
		Assert.assertEquals(itemByUrl, harnessByName);
	}
}
