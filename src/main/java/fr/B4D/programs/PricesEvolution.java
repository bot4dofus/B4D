package fr.B4D.programs;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.sun.glass.events.KeyEvent;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.building.hdv.HDV;
import fr.B4D.google.GoogleSheet;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramInterface;
import fr.B4D.socket.result.HDVItemViewSocketResult;
import fr.B4D.socket.store.HDVItemViewSocketStore;

/**
 * The {@code PricesEvolution} class contains program relative to price evolution.
 * 
 * @author Lucas
 *
 */
public final class PricesEvolution extends Program{
	
	/**
	 * The google credentials
	 */
	public static final String CREDENTIAL_FILE = "programs/pricesevolution.credentials";
	
	/**
	 * The bundle file.
	 */
	public static final String BUNDLE_FILE = "programs/pricesevolution.properties";
	
	/**
	 * The google sheet id field in the bundle file.
	 */
	public static final String GOOGLE_SHEET_ID_FIELD = "sheetId";
	
	private static GoogleSheet googelSheet;
	
	/**
	 * Constructor of the prices evelolution program.
	 */
	public PricesEvolution() {
		super(Place.Tous, Category.Autre, "Price evolution", "Get the prices of all the items in the HDVs and share it on a google sheet.", null, null, new ProgramInterface() {

			@Override
			public void intro(Person person) throws CancelProgramException {
				String googleSheetId = null;
				try {
					ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_FILE);
					googleSheetId = bundle.getString(GOOGLE_SHEET_ID_FIELD);
					if(googleSheetId == null)
						throw new CancelProgramException("The key \"" + GOOGLE_SHEET_ID_FIELD + "\" is missing in the file \"" + BUNDLE_FILE + "\".");
					
					googelSheet = new GoogleSheet(googleSheetId, CREDENTIAL_FILE);
				}
				catch(MissingResourceException  e) {
					throw new CancelProgramException("File \"" + BUNDLE_FILE + "\" is missing.");
				}
				catch(IOException | GeneralSecurityException e) {
					throw new CancelProgramException("Couldn't open the google sheet with id \"" + googleSheetId + "\". Either the credentials or the id is wrong.");
				}
			}

			@Override
			public void cycle(Person person) throws B4DException {
				HDV.ASTRUB.open(person);
				
				//clique sur le premier item
				
				
				HDVItemViewSocketResult result;
				while((result = HDVItemViewSocketStore.getInstance().waitForResult(1000)) != null) {
					List<List<Object>> values = new ArrayList<List<Object>>();
					values.add(Arrays.asList(LocalDateTime.now().toString()));
					values.add(Arrays.asList(result.getPrice1()));
					values.add(Arrays.asList(result.getPrice10()));
					values.add(Arrays.asList(result.getPrice100()));
					try {
						googelSheet.write(values, "A0");
					} catch (IOException e) {
						B4D.logger.error(e);
					}
					B4D.keyboard.sendKey(KeyEvent.VK_DOWN);
				}
				
				B4D.wait.sleep(60000);
			}

			@Override
			public void outro(Person person) throws CancelProgramException {}
		});
	}
}
