package test.libs;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;

import fr.B4D.google.GoogleSheet;

// https://www.baeldung.com/google-sheets-java-client

public class GoogleSheetTest {
	private static GoogleSheet sheet;
	private static String SHEET_URL = "https://docs.google.com/spreadsheets/d/1g9SQS-HXscoK9jv-2hdiA38adiv5n5BJ38a_E-QnNiw/edit#gid=987770465";
	private static String SHEET_ID = "1g9SQS-HXscoK9jv-2hdiA38adiv5n5BJ38a_E-QnNiw";
	
	private String id;
	
	@Before
	public void setup() throws GeneralSecurityException, IOException {
		sheet = new GoogleSheet(SHEET_ID);
	}
	
	@Test
	public void getId() throws IOException {
		String id = GoogleSheet.getIdFromUrl(SHEET_URL);
		Assert.assertEquals(SHEET_ID, id);
	}
	
	@Test
	public void write() throws IOException {
		List<List<Object>> content = Arrays.asList(
						Arrays.asList("Solwe", "50 Tacle"),
						Arrays.asList("Zaak", "2 P.A."));
		UpdateValuesResponse result = sheet.write(content, "B20");
		System.out.println(result);
	}

	@Test
	public void read() throws IOException {
		List<List<Object>> readResult = sheet.read("B20:C1000");
		System.out.println(readResult);
	}
	
	@Test
	public void clear() throws IOException {
		List<List<Object>> readResult = sheet.clear("B20:C1000");
		System.out.println(readResult);
	}
	
	@Test
	public void addSheet() throws IOException {
		Spreadsheet result = sheet.addSheet("My Spreadsheet");
		Assert.assertNotNull(result.getSpreadsheetId());
	}
	
	@Test
	public void removeSheet() throws IOException {
		Spreadsheet result = sheet.removeSheet(id);
		Assert.assertNotNull(result.getSpreadsheetId());
	}
}
