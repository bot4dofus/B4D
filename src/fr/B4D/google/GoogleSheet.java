package fr.B4D.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GoogleSheet {
    private static final String APPLICATION_NAME = "B4D-program";
    
    private Sheets sheets;
    private String id;
    
    public GoogleSheet(String id) throws IOException, GeneralSecurityException {
    	this.id = id;
    	
        Credential credential = GoogleAuthorizeUtil.authorize();
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
	
	public List<List<Object>> clear(String string) {
		return null;
	}
	
	public Spreadsheet addSheet(String name) throws IOException {
		Spreadsheet spreadSheet = new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle(name));
		return sheets.spreadsheets().create(spreadSheet).execute();
	}
	
	public Spreadsheet removeSheet(String sheetId) {
		return null;
	}
    
    public static String getIdFromUrl(String url) {
    	int begin = url.indexOf("/d/");
    	String subUrl = url.substring(begin+3);
    	return subUrl.substring(0, subUrl.indexOf("/"));
    }
}
