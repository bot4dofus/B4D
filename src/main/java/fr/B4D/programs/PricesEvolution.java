package fr.B4D.programs;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.hdv.HDV;
import fr.B4D.dofus.Dofus;
import fr.B4D.dofus.items.Resource;
import fr.B4D.google.GoogleSheet;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.socket.event.HDVItemViewEvent;
import fr.B4D.socket.store.EventStore;
import fr.B4D.utils.PointF;

/**
 * The {@code PricesEvolution} class contains program relative to price evolution.
 * 
 * @author Lucas
 *
 */
public final class PricesEvolution extends Program{

	/**
	 * The google sheet id field in the bundle file.
	 */
	public static final String GOOGLE_SHEET_ID_FIELD = "sheetId";

	private static GoogleSheet googelSheet;

	/**
	 * Constructor of the prices evelolution program.
	 */
	public PricesEvolution() {
		super(Place.Tous, Category.Autre, "Statistics", "Prices Evolution", null, null);
	}
	
	@Override
	public void intro(Person person) throws CancelProgramException {
		String googleSheetId = null;
		
		try {
			googleSheetId = getBundleFile().getString(GOOGLE_SHEET_ID_FIELD);
			googelSheet = new GoogleSheet(googleSheetId, getGoogleCredentials());
		}
		catch(IOException | GeneralSecurityException e) {
			throw new CancelProgramException("Couldn't open the google sheet with id \"" + googleSheetId + "\". Either the credentials or the id is wrong.");
		}
	}

	@Override
	public void cycle(Person person) throws B4DException {
		HDV.ASTRUB.open(person);

		B4D.mouse.leftClick(new PointF(0.3289, 0.1959), false); //Select the first item

		HDVItemViewEvent result;
		Integer previousId = null;
		List<HDVItemViewEvent> results = new LinkedList<HDVItemViewEvent>();
		while((result = EventStore.getInstance().waitForEvent(HDVItemViewEvent.class, 1000)) != null) {
			if(result.getId().equals(previousId)) {
				B4D.keyboard.sendKey(KeyEvent.VK_ENTER, 200);
				break;
			}
			B4D.wait.sleep(200);
			results.add(result);
			previousId = result.getId();
			B4D.keyboard.sendKey(KeyEvent.VK_ENTER, 400);
			B4D.keyboard.sendKey(KeyEvent.VK_DOWN, 200);
			B4D.keyboard.sendKey(KeyEvent.VK_ENTER, 400);
		}

		
		JSONObject database = Dofus.getInstance().getDatabase().loadDatabase();
		List<List<Object>> values = new ArrayList<List<Object>>();
		for(HDVItemViewEvent r:results) {
			Resource resource = Dofus.getInstance().getDatabase().findItemByKey("resources", "id", r.getId().toString(), database);
			if(resource != null) {
				String formatedDate = r.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
				values.add(Arrays.asList(formatedDate, resource.getName(), r.getPrice1(), r.getPrice10(), r.getPrice100()));
			}
			else
				B4D.logger.warning("Couldn't find ressource with id " + r.getId().toString() + " in the database.");
		}
		
		try {
			googelSheet.append(values, "A1");
		} catch (IOException e) {
			B4D.logger.error(e);
		}
		
		HDV.ASTRUB.close(person);
	}

	@Override
	public void outro(Person person) throws CancelProgramException {}
}
