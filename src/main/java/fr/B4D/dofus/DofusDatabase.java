package fr.B4D.dofus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

/**
 * The {@code DofusDatabase} represente the dofus database. This class implements all the methods you need to find items.
 * 
 * @author Lucas
 *
 */
public class DofusDatabase {
	
	private final static String ITEM_REGEX = "https://www.dofus.com/(fr|en|de|es|it|pt)/mmorpg/(encyclopedie|encyclopedia|leitfaden|enciclopedia)/%s/([0-9]+)-([a-z-]+)";
	
	private final static String MONSTER_REGEX = String.format(ITEM_REGEX,"(monstres|monsters|monster|monstruos|mostri|monstros)");
	private final static String WEAPON_REGEX = String.format(ITEM_REGEX,"(armes|weapons|waffen|armas|armi|armas)");
	private final static String EQUIPMENT_REGEX = String.format(ITEM_REGEX,"(equipements|equipment|ausruestung|equipos|equipaggiamenti|equipamentos)");
	private final static String SET_REGEX = String.format(ITEM_REGEX,"(panoplies|sets|sets|panoplie|conjuntos)");
	private final static String PET_REGEX = String.format(ITEM_REGEX,"(familiers|pets|vertraute|mascotas|famigli|mascotes)");
	private final static String MOUNT_REGEX = String.format(ITEM_REGEX,"(montures|mounts|reittiere|monturas|cavalcature|montarias)");
	private final static String CONSUMABLE_REGEX = String.format(ITEM_REGEX,"(consommables|consumables|konsumgueter|consumibles|consumabili|itens-consumiveis)");
	private final static String RESOURCE_REGEX = String.format(ITEM_REGEX,"(ressources|resources|ressourcen|recursos|risorse|recursos)");
	private final static String CEREMONIAL_ITEM_REGEX = String.format(ITEM_REGEX,"(objets-d-apparat|ceremonial-item|prunkgegenstande|objeto-de-apariencia|oggetti-di-gala|item-de-aparencia)");
	private final static String SIDEKICK_REGEX = String.format(ITEM_REGEX,"(compagnons|sidekicks|begleiter|companeros|compagni|companheiros)");
	private final static String IDOL_REGEX = String.format(ITEM_REGEX,"(idoles|idols|idole|idolos|idoli)");
	private final static String HARNESS_REGEX = String.format(ITEM_REGEX,"(harnachements|harnesses|zaumzeug|arreos|bardature|arreios)");
	
	private String databaseFile;

	/**
	 * Constructs a {@code DofusDatabase} from a database file.
	 * @param databaseFile - Database file in json format.s
	 */
	public DofusDatabase(String databaseFile) {
		this.databaseFile = databaseFile;
	}
	
	private static boolean matches(String REGEX, String url) {
		Pattern pattern = Pattern.compile(REGEX);
      Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}
	
	/**
	 * Checks whether the corresponding url corresponds to a {@code Monster}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Monster}, {@code false} otherwise.
	 */
	public static boolean isMonster(String url) {
		return matches(MONSTER_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to a {@code Weapon}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Weapon}, {@code false} otherwise.
	 */
	public static boolean isWeapon(String url) {
		return matches(WEAPON_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to an {@code Equipment}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to an {@code Equipment}, {@code false} otherwise.
	 */
	public static boolean isEquipment(String url) {
		return matches(EQUIPMENT_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to a {@code Set}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Set}, {@code false} otherwise.
	 */
	public static boolean isSet(String url) {
		return matches(SET_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to a {@code Pet}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Pet}, {@code false} otherwise.
	 */
	public static boolean isPet(String url) {
		return matches(PET_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to a {@code Mount}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Mount}, {@code false} otherwise.
	 */
	public static boolean isMount(String url) {
		return matches(MOUNT_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to a {@code Consumable}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Consumable}, {@code false} otherwise.
	 */
	public static boolean isConsumable(String url) {
		return matches(CONSUMABLE_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to a {@code Resource}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Resource}, {@code false} otherwise.
	 */
	public static boolean isResource(String url) {
		return matches(RESOURCE_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to a {@code CeremonialItem}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code CeremonialItem}, {@code false} otherwise.
	 */
	public static boolean isCeremonialItem(String url) {
		return matches(CEREMONIAL_ITEM_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to a {@code Sidekick}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Sidekick}, {@code false} otherwise.
	 */
	public static boolean isSidekick(String url) {
		return matches(SIDEKICK_REGEX, url);
	}
	
	/**
	 * Checks whether the corresponding url corresponds to a {@code Weapon}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Weapon}, {@code false} otherwise.
	 */
	public static boolean isIdol(String url) {
		return matches(IDOL_REGEX, url);
	}

	/**
	 * Checks whether the corresponding url corresponds to a {@code Weapon}.
	 * @param url - Url to check.
	 * @return {@code true} if the url corresponds to a {@code Weapon}, {@code false} otherwise.
	 */
	public static boolean isHarness(String url) {
		return matches(HARNESS_REGEX, url);
	}
	
	  /****************/
	 /** NON STATIC **/
	/****************/
	
	/**
	 * Loads the database as JSONObject.
	 * @return The dofus database in JSONObject format.
	 * @throws B4DException If cannot load the database.
	 */
	public JSONObject loadDatabase() throws B4DException {
		try {
			JSONParser jsonParser = new JSONParser();
			InputStream is = DofusDatabase.class.getResourceAsStream(databaseFile);			
			InputStreamReader isr = new InputStreamReader(is);
			JSONObject database = (JSONObject) jsonParser.parse(isr);
			isr.close();
			is.close();
			return database;
		} catch (IOException | ParseException e) {
			throw new B4DException(e);
		}
	}

	/**
	 * Returns a List of String from a JSONArray.
	 * @param array - JSONArray to convert to String List.
	 * @return List of String object.
	 */
	public List<String> getStringListFromJSONArray(JSONArray array){
		List<String> list = new ArrayList<String>();
		for(int j=0; j < array.size(); j++) {
			list.add(array.get(j).toString());
		}
		return list;
	}
	
	/**
	 * Get the item corresponding to a JSONObject.
	 * @param itemObject - JSONObject representing an item.
	 * @return Item corresponding to the JSONObject
	 */
	public Item fromJSONObject(JSONObject itemObject) {
		Item item = new Item(itemObject.get("url").toString(), itemObject.get("id").toString(), itemObject.get("name").toString(), itemObject.get("img").toString(), itemObject.get("type").toString());
		
		if(itemObject.containsKey("level"))
			item.setLevel(itemObject.get("level").toString());
		
		if(itemObject.containsKey("description"))
			item.setDescription(itemObject.get("description").toString());
		
		if(itemObject.containsKey("effects"))
			item.setEffects(getStringListFromJSONArray((JSONArray) itemObject.get("effects")));
		
		if(itemObject.containsKey("conditions"))
			item.setConditions(itemObject.get("conditions").toString());
		
		if(itemObject.containsKey("characteristics"))
			item.setCharacteristics(getStringListFromJSONArray((JSONArray) itemObject.get("characteristics")));
		
		if(itemObject.containsKey("resistances"))
			item.setResistances(getStringListFromJSONArray((JSONArray) itemObject.get("resistances")));

		if(itemObject.containsKey("craft")){
			Map<String, Integer> craft = new HashMap<String, Integer>();
			
			JSONArray craft_array = (JSONArray) itemObject.get("craft");
			for(int j=0; j < craft_array.size(); j++) {
				JSONObject craft_object = (JSONObject) craft_array.get(j);
				craft.put(craft_object.get("url").toString(), Integer.valueOf(craft_object.get("quantity").toString()));
			}
			item.setCraft(craft);
		}
		
		if(itemObject.containsKey("set_bonuses"))
			item.setSetBonuses(getStringListFromJSONArray((JSONArray) itemObject.get("set_bonuses")));
		
		if(itemObject.containsKey("set_total_bonuses"))
			item.setSetTotalBonuses(getStringListFromJSONArray((JSONArray) itemObject.get("set_total_bonuses")));
		
		if(itemObject.containsKey("evolutionary_effects"))
			item.setEvolutionaryEffects(getStringListFromJSONArray((JSONArray) itemObject.get("evolutionary_effects")));
		
		if(itemObject.containsKey("bonuses"))
			item.setBonuses(getStringListFromJSONArray((JSONArray) itemObject.get("bonuses")));
		
		if(itemObject.containsKey("spells"))
			item.setSpells(itemObject.get("spells").toString());
		
		return item;
	}
	
	/**
	 * Finds an item by its key.
	 * @param category - Category of the item.
	 * @param key - Key to find the item.
	 * @param value - Expected value of the key.
	 * @param database - The dofus database.
	 * @return Found item, {@code null} if not found.
	 */
	public Item findItemByKey(String category, String key, String value, JSONObject database) {
		
		JSONArray item_array = (JSONArray) database.get(category);
		
		for(int i=0; i < item_array.size(); i++) {
			JSONObject item_object = (JSONObject) item_array.get(i);
			if(value.equals(item_object.get(key).toString())) {
				return fromJSONObject(item_object);
			}
		}
		return null;
	}

	/**
	 * Finds items from their keys.
	 * @param key - Key to find the items.
	 * @param value - Expected value of the keys.
	 * @param database - The dofus database.
	 * @return Found items.
	 */
	public List<Item> findItemsByKey(String key, String value, JSONObject database){
		String[] categories = {"monsters", "weapons", "equipments", "sets", "pets", "mounts", "consumables", "resources", "ceremonial_items", "idols", "harnesses", "sidekicks"};
		Item item = null;
		List<Item> items = new ArrayList<Item>();
		
		for(String category:categories) {
			item = findItemByKey(category, key, value, database);
			if(item != null)
				items.add(item);
		}
		return items;
	}


	/**
	 * Finds items from their keys.
	 * @param category - Category of the items.
	 * @param database - The dofus database.
	 * @return Found items.
	 */
	public List<Item> findItemsByCategory(String category, JSONObject database){

		List<Item> items = new ArrayList<Item>();
		JSONArray item_array = (JSONArray) database.get(category);
		
		for(int i=0; i < item_array.size(); i++) {
			JSONObject item_object = (JSONObject) item_array.get(i);
			items.add(fromJSONObject(item_object));
		}
		
		return items;
	}

	//ITEMS
	/**
	 * Find an {@code Item} from its url.
	 * @param url - Url of the item.
	 * @return {@code Item} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Item findItemByUrl(String url) throws B4DException{
		JSONObject database = loadDatabase();
		
		if(DofusDatabase.isMonster(url))
			return findItemByKey("monsters", "url", url, database);
		else if(DofusDatabase.isWeapon(url))
			return findItemByKey("weapons", "url", url, database);
		else if(DofusDatabase.isEquipment(url))
			return findItemByKey("equipments", "url", url, database);
		else if(DofusDatabase.isSet(url))
			return findItemByKey("sets", "url", url, database);
		else if(DofusDatabase.isPet(url))
			return findItemByKey("pets", "url", url, database);
		else if(DofusDatabase.isMount(url))
			return findItemByKey("mounts", "url", url, database);
		else if(DofusDatabase.isConsumable(url))
			return findItemByKey("consumables", "url", url, database);
		else if(DofusDatabase.isResource(url))
			return findItemByKey("resources", "url", url, database);
		else if(DofusDatabase.isCeremonialItem(url))
			return findItemByKey("ceremonial_items", "url", url, database);
		else if(DofusDatabase.isSidekick(url))
			return findItemByKey("sidekicks", "url", url, database);
		else if(DofusDatabase.isIdol(url))
			return findItemByKey("idols", "url", url, database);
		else if(DofusDatabase.isHarness(url))
			return findItemByKey("harnesses", "url", url, database);
		else
			return null;
	}
	
	/**
	 * Find a list of items from their names.
	 * @param name - Name of the items to find.
	 * @return List of items with the given name.
	 * @throws B4DException If cannot load the database.
	 */
	public List<Item> findItemsByName(String name) throws B4DException{
		return findItemsByKey("name", name, loadDatabase());
	}
	
	/**
	 * Find a list of items from their ids.
	 * @param id - Id of the items to find.
	 * @return List of items with the given id.
	 * @throws B4DException If cannot load the database.
	 */
	public List<Item> findItemsById(String id) throws B4DException{
		return findItemsByKey("id", id, loadDatabase());
	}
	
	//MONSTERS
	
	/**
	 * Find all the monsters.
	 * @return List of monsters.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Monster> findMonsters() throws B4DException{
		return (List<Monster>)(Object)findItemsByCategory("monsters", loadDatabase());
	}

	/**
	 * Find a {@code Monster} from its url.
	 * @param url - Url of the {@code Monster}.
	 * @return {@code Monster} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Monster findMonsterByUrl(String url) throws B4DException {
		if(!DofusDatabase.isMonster(url))
			return null;
		return findItemByKey("monsters", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code Monster} from its id.
	 * @param id - Id of the {@code Monster}.
	 * @return {@code Monster} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Monster findMonsterById(String id) throws B4DException {
		return findItemByKey("monsters", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code Monster} from its name.
	 * @param name - Name of the {@code Monster}.
	 * @return {@code Monster} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Monster findMonsterByName(String name) throws B4DException {
		return findItemByKey("monsters", "name", name, loadDatabase());
	}

	//WEAPONS
	
	/**
	 * Find all the weapons.
	 * @return List of weapons.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Weapon> findWeapons() throws B4DException{
		return (List<Weapon>)(Object)findItemsByCategory("weapons", loadDatabase());
	}

	/**
	 * Find a {@code Weapon} from its url.
	 * @param url - Url of the {@code Weapon}.
	 * @return {@code Weapon} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Weapon findWeaponByUrl(String url) throws B4DException {
		if(!DofusDatabase.isWeapon(url))
			return null;
		return findItemByKey("weapons", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code Weapon} from its id.
	 * @param id - Id of the {@code Weapon}.
	 * @return {@code Weapon} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Weapon findWeaponById(String id) throws B4DException {
		return findItemByKey("weapons", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code Weapon} from its name.
	 * @param name - Name of the {@code Weapon}.
	 * @return {@code Weapon} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Weapon findWeaponByName(String name) throws B4DException {
		return findItemByKey("weapons", "name", name, loadDatabase());
	}

	//EQUIPMENTS
	
	/**
	 * Find all the equipments.
	 * @return List of equipments.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Equipment> findEquipments() throws B4DException{
		return (List<Equipment>)(Object)findItemsByCategory("equipments", loadDatabase());
	}

	/**
	 * Find an {@code Equipment} from its url.
	 * @param url - Url of the {@code Equipment}.
	 * @return {@code Equipment} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Equipment findEquipmentByUrl(String url) throws B4DException {
		if(!DofusDatabase.isEquipment(url))
			return null;
		return findItemByKey("equipments", "url", url, loadDatabase());
	}
	
	/**
	 * Find an {@code Equipment} from its id.
	 * @param id - Id of the {@code Equipment}.
	 * @return {@code Equipment} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Equipment findEquipmentById(String id) throws B4DException {
		return findItemByKey("equipments", "id", id, loadDatabase());
	}

	/**
	 * Find an {@code Equipment} from its name.
	 * @param name - Name of the {@code Equipment}.
	 * @return {@code Equipment} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Equipment findEquipmentByName(String name) throws B4DException {
		return findItemByKey("equipments", "name", name, loadDatabase());
	}

	//SETS
	
	/**
	 * Find all the sets.
	 * @return List of sets.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Set> findSets() throws B4DException{
		return (List<Set>)(Object)findItemsByCategory("sets", loadDatabase());
	}

	/**
	 * Find a {@code Set} from its url.
	 * @param url - Url of the {@code Set}.
	 * @return {@code Set} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Set findSetByUrl(String url) throws B4DException {
		if(!DofusDatabase.isSet(url))
			return null;
		return findItemByKey("sets", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code Set} from its id.
	 * @param id - Id of the {@code Set}.
	 * @return {@code Set} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Set findSetById(String id) throws B4DException {
		return findItemByKey("sets", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code Set} from its name.
	 * @param name - Name of the {@code Set}.
	 * @return {@code Set} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Set findSetByName(String name) throws B4DException {
		return findItemByKey("sets", "name", name, loadDatabase());
	}

	//PETS
	
	/**
	 * Find all the pets.
	 * @return List of pets.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Pet> findPets() throws B4DException{
		return (List<Pet>)(Object)findItemsByCategory("pets", loadDatabase());
	}

	/**
	 * Find a {@code Pet} from its url.
	 * @param url - Url of the {@code Pet}.
	 * @return {@code Pet} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Pet findPetByUrl(String url) throws B4DException {
		if(!DofusDatabase.isPet(url))
			return null;
		return findItemByKey("pets", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code Pet} from its id.
	 * @param id - Id of the {@code Pet}.
	 * @return {@code Pet} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Pet findPetById(String id) throws B4DException {
		return findItemByKey("pets", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code Pet} from its name.
	 * @param name - Name of the {@code Pet}.
	 * @return {@code Pet} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Pet findPetByName(String name) throws B4DException {
		return findItemByKey("pets", "name", name, loadDatabase());
	}

	//MOUNTS
	
	/**
	 * Find all the mounts.
	 * @return List of mounts.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Mount> findMounts() throws B4DException{
		return (List<Mount>)(Object)findItemsByCategory("mounts", loadDatabase());
	}

	/**
	 * Find a {@code Mount} from its url.
	 * @param url - Url of the {@code Mount}.
	 * @return {@code Mount} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Mount findMountByUrl(String url) throws B4DException {
		if(!DofusDatabase.isMount(url))
			return null;
		return findItemByKey("mounts", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code Mount} from its id.
	 * @param id - Id of the {@code Mount}.
	 * @return {@code Mount} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Mount findMountById(String id) throws B4DException {
		return findItemByKey("mounts", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code Mount} from its name.
	 * @param name - Name of the {@code Mount}.
	 * @return {@code Mount} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Mount findMountByName(String name) throws B4DException {
		return findItemByKey("mounts", "name", name, loadDatabase());
	}

	//CONSUMABLES
	
	/**
	 * Find all the consumables.
	 * @return List of consumables.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Consumable> findConsumables() throws B4DException{
		return (List<Consumable>)(Object)findItemsByCategory("consumables", loadDatabase());
	}

	/**
	 * Find a {@code Consumable} from its url.
	 * @param url - Url of the {@code Consumable}.
	 * @return {@code Consumable} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Consumable findConsumableByUrl(String url) throws B4DException {
		if(!DofusDatabase.isConsumable(url))
			return null;
		return findItemByKey("consumables", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code Consumable} from its id.
	 * @param id - Id of the {@code Consumable}.
	 * @return {@code Consumable} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Consumable findConsumableById(String id) throws B4DException {
		return findItemByKey("consumables", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code Consumable} from its name.
	 * @param name - Name of the {@code Consumable}.
	 * @return {@code Consumable} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Consumable findConsumableByName(String name) throws B4DException {
		return findItemByKey("consumables", "name", name, loadDatabase());
	}

	//RESOURCES
	
	/**
	 * Find all the resources.
	 * @return List of resources.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Resource> findResources() throws B4DException{
		return (List<Resource>)(Object)findItemsByCategory("resources", loadDatabase());
	}

	/**
	 * Find a {@code Resource} from its url.
	 * @param url - Url of the {@code Resource}.
	 * @return {@code Resource} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Resource findResourceByUrl(String url) throws B4DException {
		if(!DofusDatabase.isResource(url))
			return null;
		return findItemByKey("resources", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code Resource} from its id.
	 * @param id - Id of the {@code Resource}.
	 * @return {@code Resource} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Resource findResourceById(String id) throws B4DException {
		return findItemByKey("resources", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code Resource} from its name.
	 * @param name - Name of the {@code Resource}.
	 * @return {@code Resource} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Resource findResourceByName(String name) throws B4DException {
		return findItemByKey("resources", "name", name, loadDatabase());
	}

	//CEREMONIAL ITEMS
	
	/**
	 * Find all the ceremonial items.
	 * @return List of ceremonial items.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<CeremonialItem> findCeremonialItems() throws B4DException{
		return (List<CeremonialItem>)(Object)findItemsByCategory("ceremonial_items", loadDatabase());
	}

	/**
	 * Find a {@code CeremonialItem} from its url.
	 * @param url - Url of the {@code CeremonialItem}.
	 * @return {@code CeremonialItem} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public CeremonialItem findCeremonialItemByUrl(String url) throws B4DException {
		if(!DofusDatabase.isCeremonialItem(url))
			return null;
		return findItemByKey("ceremonial_items", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code CeremonialItem} from its id.
	 * @param id - Id of the {@code CeremonialItem}.
	 * @return {@code CeremonialItem} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public CeremonialItem findCeremonialItemById(String id) throws B4DException {
		return findItemByKey("ceremonial_items", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code CeremonialItem} from its name.
	 * @param name - Name of the {@code CeremonialItem}.
	 * @return {@code CeremonialItem} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public CeremonialItem findCeremonialItemByName(String name) throws B4DException {
		return findItemByKey("ceremonial_items", "name", name, loadDatabase());
	}

	//SIDEKICKS
	
	/**
	 * Find all the sidekicks.
	 * @return List of sidekicks.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Sidekick> findSidekicks() throws B4DException{
		return (List<Sidekick>)(Object)findItemsByCategory("sidekicks", loadDatabase());
	}

	/**
	 * Find a {@code Sidekick} from its url.
	 * @param url - Url of the {@code Sidekick}.
	 * @return {@code Sidekick} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Sidekick findSidekickByUrl(String url) throws B4DException {
		if(!DofusDatabase.isSidekick(url))
			return null;
		return findItemByKey("sidekicks", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code Sidekick} from its id.
	 * @param id - Id of the {@code Sidekick}.
	 * @return {@code Sidekick} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Sidekick findSidekickById(String id) throws B4DException {
		return findItemByKey("sidekicks", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code Sidekick} from its name.
	 * @param name - Name of the {@code Sidekick}.
	 * @return {@code Sidekick} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Sidekick findSidekickByName(String name) throws B4DException {
		return findItemByKey("sidekicks", "name", name, loadDatabase());
	}

	//IDOLS
	
	/**
	 * Find all the idols.
	 * @return List of idols.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Idol> findIdols() throws B4DException{
		return (List<Idol>)(Object)findItemsByCategory("idols", loadDatabase());
	}

	/**
	 * Find an {@code Idol} from its url.
	 * @param url - Url of the {@code Idol}.
	 * @return {@code Idol} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Idol findIdolByUrl(String url) throws B4DException {
		if(!DofusDatabase.isIdol(url))
			return null;
		return findItemByKey("idols", "url", url, loadDatabase());
	}

	/**
	 * Find an {@code Idol} from its id.
	 * @param id - Id of the {@code Idol}.
	 * @return {@code Idol} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Idol findIdolById(String id) throws B4DException {
		return findItemByKey("idols", "id", id, loadDatabase());
	}

	/**
	 * Find an {@code Idol} from its name.
	 * @param name - Name of the {@code Idol}.
	 * @return {@code Idol} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Idol findIdolByName(String name) throws B4DException {
		return findItemByKey("idols", "name", name, loadDatabase());
	}

	//HARNESSES
	
	/**
	 * Find all the harnesses.
	 * @return List of harnesses.
	 * @throws B4DException If cannot load the database.
	 */
	@SuppressWarnings("unchecked")
	public List<Harness> findHarnesses() throws B4DException{
		return (List<Harness>)(Object)findItemsByCategory("harnesses", loadDatabase());
	}

	/**
	 * Find a {@code Harness} from its url.
	 * @param url - Url of the {@code Harness}.
	 * @return {@code Harness} with the given url, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Harness findHarnessByUrl(String url) throws B4DException {
		if(!DofusDatabase.isHarness(url))
			return null;
		return findItemByKey("harnesses", "url", url, loadDatabase());
	}

	/**
	 * Find a {@code Harness} from its id.
	 * @param id - Id of the {@code Harness}.
	 * @return {@code Harness} with the given id, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Harness findHarnessById(String id) throws B4DException {
		return findItemByKey("harnesses", "id", id, loadDatabase());
	}

	/**
	 * Find a {@code Harness} from its name.
	 * @param name - Name of the {@code Harness}.
	 * @return {@code Harness} with the given name, {@code null} if not found.
	 * @throws B4DException If cannot load the database.
	 */
	public Harness findHarnessByName(String name) throws B4DException {
		return findItemByKey("harnesses", "name", name, loadDatabase());
	}
}
