package test.libs;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.api.services.drive.model.File;

import fr.B4D.google.GoogleDrive;

// https://o7planning.org/en/11889/manipulating-files-and-folders-on-google-drive-using-java

public class GoogleDriveTest {
	
	private static final String DRIVE_URL = "https://drive.google.com/drive/folders/1HLj2cvMY3FO1XOlNJo1KUCamKRlfyaQj?fbclid=IwAR2NqwO3XcxHDvEqGsu6s_ggFw7fnuVNDvFrSyH3JmXCrcXlUxYkeoZmEWc";
	private static final String DRIVE_ID = "1HLj2cvMY3FO1XOlNJo1KUCamKRlfyaQj";
	
	private GoogleDrive drive;
	
	@Before
	public void setup() throws GeneralSecurityException, IOException {
		drive = new GoogleDrive(DRIVE_ID);
	}
	
	@Test
	public void getId() throws IOException {
		String id = GoogleDrive.getIdFromUrl(DRIVE_URL);
		Assert.assertEquals(DRIVE_ID, id);
	}
	
	@Test
	public void listFiles() throws IOException {
		List<File> files = drive.listFiles();
		files.stream().forEach(f -> System.out.println(f));
	}
	
	@Test
	public void createFile() throws IOException {
		drive.createFile("text/plain", "Test.txt");
	}

	@Test
	public void uploadFile() throws IOException {
		java.io.File file = new java.io.File("errors.txt");
		drive.uploadFile("text/plain", "erros_uploaded.txt", file);
	}

	@Test
	public void copyFile() throws IOException {
		drive.copyFile("1ZtOFbbaICeS_FJbn5-TGkJ5aEimGefzK", "copie de errors");
	}
	
	@Test
	public void removeFile() {
		drive.removeFile("1mbd5PLIHMfOi3f3gHsTcSiDDEzawWGZv");
	}
	
	@Test
	public void listFolders() throws IOException {
		List<File> folders = drive.listFolders();
		folders.stream().forEach(f -> System.out.println(f));
	}
	
	@Test
	public void createFolder() throws IOException {
		drive.createFolder("Dossier test");
	}
	
	@Test
	public void removeFolder() {
		drive.removeFolder("");
	}
}
