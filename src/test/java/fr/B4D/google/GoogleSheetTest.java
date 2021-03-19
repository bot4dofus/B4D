package fr.B4D.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.ClearValuesResponse;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;

// https://www.baeldung.com/google-sheets-java-client

@SuppressWarnings("javadoc")
@Ignore
public class GoogleSheetTest {
	private static final String CREDENTIALS = "b4d_service.json";
	private static final String SPREADSHEET_URL = "https://docs.google.com/spreadsheets/d/1g9SQS-HXscoK9jv-2hdiA38adiv5n5BJ38a_E-QnNiw/edit#gid=987770465";
	private static final String SPREADSHEET_ID = "1g9SQS-HXscoK9jv-2hdiA38adiv5n5BJ38a_E-QnNiw";
	private static final int SHEET_ID = 1102216258;
	
	private static GoogleSheet sheet;
	
	@Before
	public void setup() throws GeneralSecurityException, IOException {
		sheet = new GoogleSheet(SPREADSHEET_ID, CREDENTIALS);
	}
	
	@Test
	public void getId() throws IOException {
		String id = GoogleSheet.getIdFromUrl(SPREADSHEET_URL);
		Assert.assertEquals(SPREADSHEET_ID, id);
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
		ClearValuesResponse readResult = sheet.clear("B20:C1000");
		System.out.println(readResult);
	}
	
	@Test
	public void listSheets() throws IOException {
		List<Sheet> sheets = sheet.listSheets();
		sheets.stream().forEach(s -> System.out.println(s));
	}
	
	@Test
	public void createSheet() throws IOException {
		BatchUpdateSpreadsheetResponse result = sheet.createSheet("B4D have created this sheet");
		Assert.assertNotNull(result.getSpreadsheetId());
	}
	
	@Test
	public void copySheet() throws IOException {
		SheetProperties result = sheet.copySheet(SHEET_ID, "B4D have copied this sheet");
		Assert.assertNotNull(result);
	}

	@Test
	public void renameSheet() throws IOException {
		BatchUpdateSpreadsheetResponse result = sheet.renameSheet(456860675, "B4D have renamed this sheet");
		Assert.assertNotNull(result);
	}
	
	@Test
	public void removeSheet() throws IOException {
		BatchUpdateSpreadsheetResponse result = sheet.removeSheet(1366940185);
		Assert.assertNotNull(result.getSpreadsheetId());
	}
}
