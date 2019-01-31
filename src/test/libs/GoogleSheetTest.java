package test.libs;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import fr.B4D.google.sheet.SheetsServiceUtil;

// https://www.baeldung.com/google-sheets-java-client

public class GoogleSheetTest {
	private static Sheets sheetsService;
	private static String SPREADSHEET_ID = "1g9SQS-HXscoK9jv-2hdiA38adiv5n5BJ38a_E-QnNiw";

	@Before
	public void setup() throws GeneralSecurityException, IOException {
		sheetsService = SheetsServiceUtil.getSheetsService();
	}

	@Test
	public void write() throws IOException {
		ValueRange body = new ValueRange()
				.setValues(Arrays.asList(
						Arrays.asList("Solwe", "50 Tacle"),
						Arrays.asList("Zaak", "2 P.A.")));
		UpdateValuesResponse result = sheetsService.spreadsheets().values()
				.update(SPREADSHEET_ID, "B20", body)
				.setValueInputOption("RAW")
				.execute();
		System.out.println(result);
	}

	@Test
	public void append() throws IOException {
		
	}

	@Test
	public void read() throws IOException {
		List<String> ranges = Arrays.asList("B20:C29");
		BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
				.batchGet(SPREADSHEET_ID)
				.setRanges(ranges)
				.execute();
		System.out.println(readResult.getValueRanges().get(0).getValues());
	}

	@Test
	public void addSheet() throws IOException {
		Spreadsheet spreadSheet = new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle("My Spreadsheet"));
		Spreadsheet result = sheetsService.spreadsheets().create(spreadSheet).execute();
		Assert.assertNotNull(result.getSpreadsheetId());
	}
}
