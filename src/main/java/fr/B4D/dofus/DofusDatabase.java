package fr.B4D.dofus;

import java.io.FileInputStream;
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

public class DofusDatabase {

	  /***************/
	 /** CONSTANTS **/
	/***************/
	
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

	  /***************/
	 /** ATTRIBUTS **/
	/***************/
	
	private String databaseFile;
	private boolean isResource;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	public DofusDatabase(String databaseFile) {
		this(databaseFile, false);
	}
	public DofusDatabase(String databaseFile, boolean isResource) {
		this.databaseFile = databaseFile;
		this.isResource = isResource;
	}
	
	  /*************/
	 /** STATICS **/
	/*************/
	
	private static boolean matches(String REGEX, String url) {
		Pattern pattern = Pattern.compile(REGEX);
      Matcher matcher = pattern.matcher(url);
		return matcher.matches();
	}
	
	public static boolean isMonster(String url) {
		return matches(MONSTER_REGEX, url);
	}
	
	public static boolean isWeapon(String url) {
		return matches(WEAPON_REGEX, url);
	}
	
	public static boolean isEquipment(String url) {
		return matches(EQUIPMENT_REGEX, url);
	}
	
	public static boolean isSet(String url) {
		return matches(SET_REGEX, url);
	}
	
	public static boolean isPet(String url) {
		return matches(PET_REGEX, url);
	}
	
	public static boolean isMount(String url) {
		return matches(MOUNT_REGEX, url);
	}
	
	public static boolean isConsumable(String url) {
		return matches(CONSUMABLE_REGEX, url);
	}
	
	public static boolean isResource(String url) {
		return matches(RESOURCE_REGEX, url);
	}
	
	public static boolean isCeremonialItem(String url) {
		return matches(CEREMONIAL_ITEM_REGEX, url);
	}
	
	public static boolean isSidekick(String url) {
		return matches(SIDEKICK_REGEX, url);
	}
	
	public static boolean isIdol(String url) {
		return matches(IDOL_REGEX, url);
	}
	
	public static boolean isHarness(String url) {
		return matches(HARNESS_REGEX, url);
	}
	
	public List<String> getStringListFromJSONArray(JSONArray array){
		List<String> list = new ArrayList<String>();
		for(int j=0; j < array.size(); j++) {
			list.add((String) array.get(j));
		}
		return list;
	}
	
	  /*************/
	 /** PRIVATE **/
	/*************/
	
	public JSONObject loadDatabase() throws B4DException {
		try {
			JSONParser jsonParser = new JSONParser();
			InputStream is;
			if(isResource)
				is = DofusDatabase.class.getResourceAsStream(databaseFile);
			else
				is = new FileInputStream(databaseFile);
			
			InputStreamReader isr = new InputStreamReader(is);
			JSONObject database = (JSONObject) jsonParser.parse(isr);
			isr.close();
			is.close();
			return database;
		} catch (IOException | ParseException e) {
			throw new B4DException(e);
		}
	}
	
	public Item fromJSONObject(JSONObject item_object) {
		Item item = new Item((String) item_object.get("url"), (String)item_object.get("id"), (String)item_object.get("name"), (String)item_object.get("img"), (String)item_object.get("type"));
		
		if(item_object.containsKey("level"))
			item.setLevel((String) item_object.get("level"));
		
		if(item_object.containsKey("description"))
			item.setDescription((String) item_object.get("description"));
		
		if(item_object.containsKey("effects"))
			item.setEffects(getStringListFromJSONArray((JSONArray) item_object.get("effects")));
		
		if(item_object.containsKey("conditions"))
			item.setConditions(getStringListFromJSONArray((JSONArray) item_object.get("conditions")));
		
		if(item_object.containsKey("characteristics"))
			item.setCharacteristics(getStringListFromJSONArray((JSONArray) item_object.get("characteristics")));
		
		if(item_object.containsKey("resistances"))
			item.setResistances(getStringListFromJSONArray((JSONArray) item_object.get("resistances")));

		if(item_object.containsKey("craft")){
			Map<String, Integer> craft = new HashMap<String, Integer>();
			
			JSONArray craft_array = (JSONArray) item_object.get("craft");
			for(int j=0; j < craft_array.size(); j++) {
				JSONObject craft_object = (JSONObject) craft_array.get(j);
				craft.put((String) craft_object.get("url"), Integer.valueOf((String) craft_object.get("quantity")));
			}
			item.setCraft(craft);
		}
		
		if(item_object.containsKey("set_bonuses"))
			item.setSetBonuses(getStringListFromJSONArray((JSONArray) item_object.get("set_bonuses")));
		
		if(item_object.containsKey("set_total_bonuses"))
			item.setSetTotalBonuses(getStringListFromJSONArray((JSONArray) item_object.get("set_total_bonuses")));
		
		if(item_object.containsKey("evolutionary_effects"))
			item.setEvolutionaryEffects(getStringListFromJSONArray((JSONArray) item_object.get("evolutionary_effects")));
		
		if(item_object.containsKey("bonuses"))
			item.setBonuses(getStringListFromJSONArray((JSONArray) item_object.get("bonuses")));
		
		if(item_object.containsKey("spells"))
			item.setSpells((String) item_object.get("spells"));
		
		return item;
	}
	
	public Item findItemByKey(String category, String key, String value, JSONObject database) {
		
		JSONArray item_array = (JSONArray) database.get(category);
		
		for(int i=0; i < item_array.size(); i++) {
			JSONObject item_object = (JSONObject) item_array.get(i);
			if(value.equals((String) item_object.get(key))) {
				return fromJSONObject(item_object);
			}
		}
		return null;
	}
	
	public List<Item> findItemsByKey(String key, String value, JSONObject database) throws B4DException{
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
	
	public List<Item> findItemsByCategory(String category, JSONObject database) throws B4DException{

		List<Item> items = new ArrayList<Item>();
		JSONArray item_array = (JSONArray) database.get(category);
		
		for(int i=0; i < item_array.size(); i++) {
			JSONObject item_object = (JSONObject) item_array.get(i);
			items.add(fromJSONObject(item_object));
		}
		
		return items;
	}
	
	  /************/
	 /** PUBLIC **/
	/************/
	
	//ITEM
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
	
	public List<Item> findItemsByName(String name) throws B4DException{
		return findItemsByKey("name", name, loadDatabase());
	}
	
	public List<Item> findItemsById(String id) throws B4DException{
		return findItemsByKey("id", id, loadDatabase());
	}
	
	//MONSTERS
	public List<Monster> findMonsters() throws B4DException{
		return (List<Monster>)(Object)findItemsByCategory("monsters", loadDatabase());
	}
	
	public Monster findMonsterByUrl(String url) throws B4DException {
		if(!DofusDatabase.isMonster(url))
			return null;
		return findItemByKey("monsters", "url", url, loadDatabase());
	}
	
	public Monster findMonsterById(String id) throws B4DException {
		return findItemByKey("monsters", "id", id, loadDatabase());
	}
	
	public Monster findMonsterByName(String name) throws B4DException {
		return findItemByKey("monsters", "name", name, loadDatabase());
	}

	//WEAPONS
	public List<Weapon> findWeapons() throws B4DException{
		return (List<Weapon>)(Object)findItemsByCategory("weapons", loadDatabase());
	}
	
	public Weapon findWeaponByUrl(String url) throws B4DException {
		if(!DofusDatabase.isWeapon(url))
			return null;
		return findItemByKey("weapons", "url", url, loadDatabase());
	}
	
	public Weapon findWeaponById(String id) throws B4DException {
		return findItemByKey("weapons", "id", id, loadDatabase());
	}
	
	public Weapon findWeaponByName(String name) throws B4DException {
		return findItemByKey("weapons", "name", name, loadDatabase());
	}

	//EQUIPMENTS
	public List<Equipment> findEquipments() throws B4DException{
		return (List<Equipment>)(Object)findItemsByCategory("equipments", loadDatabase());
	}
	
	public Equipment findEquipmentByUrl(String url) throws B4DException {
		if(!DofusDatabase.isEquipment(url))
			return null;
		return findItemByKey("equipments", "url", url, loadDatabase());
	}
	
	public Equipment findEquipmentById(String id) throws B4DException {
		return findItemByKey("equipments", "id", id, loadDatabase());
	}
	
	public Equipment findEquipmentByName(String name) throws B4DException {
		return findItemByKey("equipments", "name", name, loadDatabase());
	}

	//SETS
	public List<Set> findSets() throws B4DException{
		return (List<Set>)(Object)findItemsByCategory("sets", loadDatabase());
	}
	
	public Set findSetByUrl(String url) throws B4DException {
		if(!DofusDatabase.isSet(url))
			return null;
		return findItemByKey("sets", "url", url, loadDatabase());
	}
	
	public Set findSetById(String id) throws B4DException {
		return findItemByKey("sets", "id", id, loadDatabase());
	}
	
	public Set findSetByName(String name) throws B4DException {
		return findItemByKey("sets", "name", name, loadDatabase());
	}

	//PETS
	public List<Pet> findPets() throws B4DException{
		return (List<Pet>)(Object)findItemsByCategory("pets", loadDatabase());
	}
	
	public Pet findPetByUrl(String url) throws B4DException {
		if(!DofusDatabase.isPet(url))
			return null;
		return findItemByKey("pets", "url", url, loadDatabase());
	}
	
	public Pet findPetById(String id) throws B4DException {
		return findItemByKey("pets", "id", id, loadDatabase());
	}
	
	public Pet findPetByName(String name) throws B4DException {
		return findItemByKey("pets", "name", name, loadDatabase());
	}

	//MOUNTS
	public List<Mount> findMounts() throws B4DException{
		return (List<Mount>)(Object)findItemsByCategory("mounts", loadDatabase());
	}
	
	public Mount findMountByUrl(String url) throws B4DException {
		if(!DofusDatabase.isMount(url))
			return null;
		return findItemByKey("mounts", "url", url, loadDatabase());
	}
	
	public Mount findMountById(String id) throws B4DException {
		return findItemByKey("mounts", "id", id, loadDatabase());
	}
	
	public Mount findMountByName(String name) throws B4DException {
		return findItemByKey("mounts", "name", name, loadDatabase());
	}

	//CONSUMABLES
	public List<Consumable> findConsumables() throws B4DException{
		return (List<Consumable>)(Object)findItemsByCategory("consumables", loadDatabase());
	}
	
	public Consumable findConsumableByUrl(String url) throws B4DException {
		if(!DofusDatabase.isConsumable(url))
			return null;
		return findItemByKey("consumables", "url", url, loadDatabase());
	}
	
	public Consumable findConsumableById(String id) throws B4DException {
		return findItemByKey("consumables", "id", id, loadDatabase());
	}
	
	public Consumable findConsumableByName(String name) throws B4DException {
		return findItemByKey("consumables", "name", name, loadDatabase());
	}

	//RESOURCES
	public List<Resource> findResources() throws B4DException{
		return (List<Resource>)(Object)findItemsByCategory("resources", loadDatabase());
	}
	
	public Resource findResourceByUrl(String url) throws B4DException {
		if(!DofusDatabase.isResource(url))
			return null;
		return findItemByKey("resources", "url", url, loadDatabase());
	}
	
	public Resource findResourceById(String id) throws B4DException {
		return findItemByKey("resources", "id", id, loadDatabase());
	}
	
	public Resource findResourceByName(String name) throws B4DException {
		return findItemByKey("resources", "name", name, loadDatabase());
	}

	//CEREMONIAL ITEMS
	public List<CeremonialItem> findCeremonialItems() throws B4DException{
		return (List<CeremonialItem>)(Object)findItemsByCategory("ceremonial_items", loadDatabase());
	}
	
	public CeremonialItem findCeremonialItemByUrl(String url) throws B4DException {
		if(!DofusDatabase.isCeremonialItem(url))
			return null;
		return findItemByKey("ceremonial_items", "url", url, loadDatabase());
	}
	
	public CeremonialItem findCeremonialItemById(String id) throws B4DException {
		return findItemByKey("ceremonial_items", "id", id, loadDatabase());
	}
	
	public CeremonialItem findCeremonialItemByName(String name) throws B4DException {
		return findItemByKey("ceremonial_items", "name", name, loadDatabase());
	}

	//SIDEKICKS
	public List<Sidekick> findSidekicks() throws B4DException{
		return (List<Sidekick>)(Object)findItemsByCategory("sidekicks", loadDatabase());
	}
	
	public Sidekick findSidekickByUrl(String url) throws B4DException {
		if(!DofusDatabase.isSidekick(url))
			return null;
		return findItemByKey("sidekicks", "url", url, loadDatabase());
	}
	
	public Sidekick findSidekickById(String id) throws B4DException {
		return findItemByKey("sidekicks", "id", id, loadDatabase());
	}
	
	public Sidekick findSidekickByName(String name) throws B4DException {
		return findItemByKey("sidekicks", "name", name, loadDatabase());
	}

	//IDOLS
	public List<Idol> findIdols() throws B4DException{
		return (List<Idol>)(Object)findItemsByCategory("idols", loadDatabase());
	}
	
	public Idol findIdolByUrl(String url) throws B4DException {
		if(!DofusDatabase.isIdol(url))
			return null;
		return findItemByKey("idols", "url", url, loadDatabase());
	}
	
	public Idol findIdolById(String id) throws B4DException {
		return findItemByKey("idols", "id", id, loadDatabase());
	}
	
	public Idol findIdolByName(String name) throws B4DException {
		return findItemByKey("idols", "name", name, loadDatabase());
	}

	//HARNESSES
	public List<Harness> findHarnesss() throws B4DException{
		return (List<Harness>)(Object)findItemsByCategory("harnesses", loadDatabase());
	}
	
	public Harness findHarnessByUrl(String url) throws B4DException {
		if(!DofusDatabase.isHarness(url))
			return null;
		return findItemByKey("harnesses", "url", url, loadDatabase());
	}
	
	public Harness findHarnessById(String id) throws B4DException {
		return findItemByKey("harnesses", "id", id, loadDatabase());
	}
	
	public Harness findHarnessByName(String name) throws B4DException {
		return findItemByKey("harnesses", "name", name, loadDatabase());
	}
}
