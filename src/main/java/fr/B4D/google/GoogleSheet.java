package fr.B4D.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AddSheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.ClearValuesResponse;
import com.google.api.services.sheets.v4.model.CopySheetToAnotherSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.DeleteSheetRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

/** La classe {@code GoogleSheet} permet de gérer un google spreadSheet grace à l'API sheet de google.
 */
public class GoogleSheet {

	  /***************/
	 /** CONSTANTS **/
	/***************/
	
    private static final String APPLICATION_NAME = "B4D-program";
    
	  /**************/
	 /** ATRIBUTS **/
	/**************/
    
    private Sheets sheets;
    private String id;
    
	  /*************/
	 /** BUILDER **/
	/*************/
    
	/** Constructeur de la classe {@code GoogleDrive}. 
     * @param id - Id du spreadSheet.
     * @param credentials - Chemin vers le fichier contenant le certificat d'utilisation des API google.
     * @throws IOException Si aucun fichier n'a été trouvé.
     * @throws GeneralSecurityException Si problèmes de sécurité survient.
     */
    public GoogleSheet(String id, String credentials) throws IOException, GeneralSecurityException {
    	this.id = id;
    	
        Credential credential = GoogleAuthorize.authorize(credentials);
        sheets = new Sheets.Builder(
          GoogleNetHttpTransport.newTrustedTransport(), 
          JacksonFactory.getDefaultInstance(), credential)
          .setApplicationName(APPLICATION_NAME)
          .build();
    }

	  /*************/
	 /** METHODS **/
	/*************/
    
	/** Permet d'écrire dans une ou plusieurs cellules.
	 * @param content - Liste de liste d'objets à écrire. 
	 * @param range - Cellule dans laquelle écrire.
	 * @return Résultat de l'éxecution.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public UpdateValuesResponse write(List<List<Object>> content, String range) throws IOException {
		ValueRange body = new ValueRange().setValues(content);
		return sheets.spreadsheets().values()
				.update(id, range, body)
				.setValueInputOption("RAW")
				.execute();
	}

	/** Permet de lire le contenu de une ou plusieurs cellules.
	 * @param range - Plage de donnée que l'on veut lire.
	 * @return Liste de liste d'objets luent.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public List<List<Object>> read(String range) throws IOException {
		List<String> ranges = Arrays.asList(range);
		return sheets.spreadsheets().values()
				.batchGet(id)
				.setRanges(ranges)
				.execute()
				.getValueRanges()
				.get(0)
				.getValues();
	}
	
	/** Permet d'effacer le contenu d'une ou plusieurs cellules.
	 * @param range - Plage de donnée à effasser.
	 * @return Résultat de l'éxecution.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public ClearValuesResponse clear(String range) throws IOException {
		return sheets.spreadsheets().values()
				.clear(id, range, new ClearValuesRequest())
				.execute();
	}
	
	/** Permet de lister les pages du tableur.
	 * @return List des pages.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public List<Sheet> listSheets() throws IOException {
		return sheets.spreadsheets().get(id).execute().getSheets();
	}
	
	/** Permet de créer une nouvelle page au tableur.
	 * @param name - Nom de la page.
	 * @return Résultat de l'éxecution.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public BatchUpdateSpreadsheetResponse createSheet(String name) throws IOException {
		AddSheetRequest addSheetRequest = new AddSheetRequest().setProperties(new SheetProperties().setTitle(name));
		Request request = new Request().setAddSheet(addSheetRequest);
		List<Request> requests = new ArrayList<>();
		requests.add(request);
		BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest().setRequests(requests);
		return sheets.spreadsheets().batchUpdate(id, requestBody).execute();
	}
	
	/** Permet de copier une page du tableur.
	 * @param sheetId - Id de la page à copier.
	 * @param name - Nom de la page.
	 * @return Résultat de l'éxecution.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public SheetProperties copySheet(Integer sheetId, String name) throws IOException {
		CopySheetToAnotherSpreadsheetRequest requestBody = new CopySheetToAnotherSpreadsheetRequest().setDestinationSpreadsheetId(id);
		SheetProperties result = sheets.spreadsheets().sheets().copyTo(id, sheetId, requestBody).execute();
		result.setTitle(name);
		return result;
	}
	
	/** Permet de renommer une page.
	 * @param sheetId - Id de la page.
	 * @param name - Nouveau nom de la page
	 * @return Résultat de l'éxecution.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public BatchUpdateSpreadsheetResponse renameSheet(int sheetId, String name) throws IOException {
		//TO DO
		return null;
	}
	
	/** Permet de supprimer une page du tableur.
	 * @param sheetId - Id de la page à supprimer.
	 * @return Résultat de l'éxecution.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public BatchUpdateSpreadsheetResponse removeSheet(int sheetId) throws IOException {		
		DeleteSheetRequest deleteSheetRequest = new DeleteSheetRequest().setSheetId(sheetId);
		Request request = new Request().setDeleteSheet(deleteSheetRequest);
		List<Request> requests = new ArrayList<>();
		requests.add(request);
		BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest().setRequests(requests);
		return sheets.spreadsheets().batchUpdate(id, requestBody).execute();
	}
    

	  /************/
	 /** STATIC **/
	/************/
	
    /** Permet de récupérer l'id d'un tableur à partir de l'url.
     * @param url - Url du tableur.
     * @return Id du tableur.
     */
    public static String getIdFromUrl(String url) {
    	int begin = url.indexOf("/d/");
    	String subUrl = url.substring(begin+3);
    	return subUrl.substring(0, subUrl.indexOf("/"));
    }
}
