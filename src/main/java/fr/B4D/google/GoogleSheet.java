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

public class GoogleSheet {
    private static final String APPLICATION_NAME = "B4D-program";
    
    private Sheets sheets;
    private String id;
    
    public GoogleSheet(String id, String credentials) throws IOException, GeneralSecurityException {
    	this.id = id;
    	
        Credential credential = GoogleAuthorize.authorize(credentials);
        sheets = new Sheets.Builder(
          GoogleNetHttpTransport.newTrustedTransport(), 
          JacksonFactory.getDefaultInstance(), credential)
          .setApplicationName(APPLICATION_NAME)
          .build();
    }
    
	public UpdateValuesResponse write(List<List<Object>> content, String range) throws IOException {
		ValueRange body = new ValueRange().setValues(content);
		return sheets.spreadsheets().values()
				.update(id, range, body)
				.setValueInputOption("RAW")
				.execute();
	}

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
	
	public ClearValuesResponse clear(String range) throws IOException {
		return sheets.spreadsheets().values()
				.clear(id, range, new ClearValuesRequest())
				.execute();
	}
	
	public List<Sheet> listSheets() throws IOException {
		return sheets.spreadsheets().get(id).execute().getSheets();
	}
	
	public BatchUpdateSpreadsheetResponse createSheet(String name) throws IOException {
		AddSheetRequest addSheetRequest = new AddSheetRequest().setProperties(new SheetProperties().setTitle(name));
		Request request = new Request().setAddSheet(addSheetRequest);
		List<Request> requests = new ArrayList<>();
		requests.add(request);
		BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest().setRequests(requests);
		return sheets.spreadsheets().batchUpdate(id, requestBody).execute();
	}
	
	public SheetProperties copySheet(Integer sheetId, String name) throws IOException {
		CopySheetToAnotherSpreadsheetRequest requestBody = new CopySheetToAnotherSpreadsheetRequest().setDestinationSpreadsheetId(id);
	    return sheets.spreadsheets().sheets().copyTo(id, sheetId, requestBody).execute().setTitle(name);
	}
	
	public BatchUpdateSpreadsheetResponse renameSheet(int sheetId, String name) throws IOException {
		//TO DO
		return null;
	}
	
	public BatchUpdateSpreadsheetResponse removeSheet(int sheetId) throws IOException {		
		DeleteSheetRequest deleteSheetRequest = new DeleteSheetRequest().setSheetId(sheetId);
		Request request = new Request().setDeleteSheet(deleteSheetRequest);
		List<Request> requests = new ArrayList<>();
		requests.add(request);
		BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest().setRequests(requests);
		return sheets.spreadsheets().batchUpdate(id, requestBody).execute();
	}
    
    public static String getIdFromUrl(String url) {
    	int begin = url.indexOf("/d/");
    	String subUrl = url.substring(begin+3);
    	return subUrl.substring(0, subUrl.indexOf("/"));
    }
}
