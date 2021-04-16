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
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
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

/**
 * The {@code GoogleSheet} is used to acces usefull method related to Google Sheet.
 * 
 * @author Lucas
 *
 */
public class GoogleSheet {
	
    private static final String APPLICATION_NAME = "B4D-program";
	
  /**
   * Returns the id of a sheet from its url.
   * @param url - Url of the sheet.
   * @return Id of the sheet.
   */
  public static String getIdFromUrl(String url) {
  	int begin = url.indexOf("/d/");
  	String subUrl = url.substring(begin+3);
  	return subUrl.substring(0, subUrl.indexOf("/"));
  }
    
    /**
     * Instance of the sheet under modification.
     */
    private Sheets sheets;
    
    /**
     * Id of the sheet under modification.
     */
    private String id;
    
	/**
	 * Constructor of the {@code GoogleDrive} class.
     * @param id - Id of the sheet to modify.
     * @param credentials - Path to the Google Sheet API credential file.
     * @throws IOException if no file has been found.
     * @throws GeneralSecurityException if a security issue occurs.
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
    
	/** 
	 * Writes in one or multiple cells.
	 * @param content - List of objects to write in the cells. 
	 * @param range - Cell in which write the content.
	 * @return Result of the operation.
     * @throws IOException if cannot perform the operation.
	 */
	public UpdateValuesResponse write(List<List<Object>> content, String range) throws IOException {
		ValueRange body = new ValueRange().setValues(content);
		return sheets.spreadsheets().values()
				.update(id, range, body)
				.setValueInputOption("RAW")
				.execute();
	}
    
	/** 
	 * Writes in one or multiple cells by adding lines.
	 * @param content - List of objects to write in the cells. 
	 * @param range - Cell in which write the content.
	 * @return Result of the operation.
     * @throws IOException if cannot perform the operation.
	 */
	public AppendValuesResponse append(List<List<Object>> content, String range) throws IOException {
		ValueRange body = new ValueRange().setValues(content);
		return sheets.spreadsheets().values()
				.append(id, range, body)
				.setValueInputOption("RAW")
				.execute();
	}

	/**
	 * Reads the content of one or multiple cells.
	 * @param range - Range of data to read (e.g: A5:H12).
	 * @return List of objects in the range.
     * @throws IOException if cannot perform the operation.
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
	
	/**
	 * Clears the content of one or multiple cells.
	 * @param range - Range of data to clear (e.g: A5:H12).
	 * @return Result of the operation.
     * @throws IOException if cannot perform the operation.
	 */
	public ClearValuesResponse clear(String range) throws IOException {
		return sheets.spreadsheets().values()
				.clear(id, range, new ClearValuesRequest())
				.execute();
	}
	
	/**
	 * Lists the pages of the sheet.
	 * @return List of pages.
     * @throws IOException if cannot perform the operation.
	 */
	public List<Sheet> listSheets() throws IOException {
		return sheets.spreadsheets().get(id).execute().getSheets();
	}
	
	/**
	 * Creates a new page on the sheet.
	 * @param name - Name of the page.
	 * @return Result of the operation.
     * @throws IOException if cannot perform the operation.
	 */
	public BatchUpdateSpreadsheetResponse createSheet(String name) throws IOException {
		AddSheetRequest addSheetRequest = new AddSheetRequest().setProperties(new SheetProperties().setTitle(name));
		Request request = new Request().setAddSheet(addSheetRequest);
		List<Request> requests = new ArrayList<>();
		requests.add(request);
		BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest().setRequests(requests);
		return sheets.spreadsheets().batchUpdate(id, requestBody).execute();
	}
	
	/**
	 * Copies a page of the sheet.
	 * @param sheetId - Id of the page to copy.
	 * @param name - Name of the page after copy.
	 * @return Result of the operation.
     * @throws IOException if cannot perform the operation.
	 */
	public SheetProperties copySheet(Integer sheetId, String name) throws IOException {
		CopySheetToAnotherSpreadsheetRequest requestBody = new CopySheetToAnotherSpreadsheetRequest().setDestinationSpreadsheetId(id);
		SheetProperties result = sheets.spreadsheets().sheets().copyTo(id, sheetId, requestBody).execute();
		result.setTitle(name);
		return result;
	}
	
	/**
	 * Renames a page of the sheet.
	 * @param sheetId - Id of the page.
	 * @param name - New name of the page.
	 * @return Result of the operation.
     * @throws IOException if cannot perform the operation.
	 */
	public BatchUpdateSpreadsheetResponse renameSheet(int sheetId, String name) throws IOException {
		//TO DO
		return null;
	}
	
	/**
	 * Removes a page on the sheet.
	 * @param sheetId - Id of the page to remove.
	 * @return Result of the operation.
     * @throws IOException if cannot perform the operation.
	 */
	public BatchUpdateSpreadsheetResponse removeSheet(int sheetId) throws IOException {		
		DeleteSheetRequest deleteSheetRequest = new DeleteSheetRequest().setSheetId(sheetId);
		Request request = new Request().setDeleteSheet(deleteSheetRequest);
		List<Request> requests = new ArrayList<>();
		requests.add(request);
		BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest().setRequests(requests);
		return sheets.spreadsheets().batchUpdate(id, requestBody).execute();
	}
}
