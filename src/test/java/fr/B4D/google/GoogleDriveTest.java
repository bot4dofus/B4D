package fr.B4D.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.api.services.drive.model.File;

// https://o7planning.org/en/11889/manipulating-files-and-folders-on-google-drive-using-java

@SuppressWarnings("javadoc")
@Ignore
public class GoogleDriveTest {

	private static final String CREDENTIALS = "b4d_service.json";
	private static final String DRIVE_URL = "https://drive.google.com/drive/folders/1HLj2cvMY3FO1XOlNJo1KUCamKRlfyaQj?fbclid=IwAR2NqwO3XcxHDvEqGsu6s_ggFw7fnuVNDvFrSyH3JmXCrcXlUxYkeoZmEWc";
	private static final String DRIVE_ID = "1HLj2cvMY3FO1XOlNJo1KUCamKRlfyaQj";

	private GoogleDrive drive;

	@Before
	public void setup() throws GeneralSecurityException, IOException {
		drive = new GoogleDrive(DRIVE_ID, CREDENTIALS);
	}

	@Test
	public void getId() throws IOException {
		String id = GoogleDrive.getIdFromUrl(DRIVE_URL);
		Assert.assertEquals(DRIVE_ID, id);
	}

	/* LIST */

	@Test
	public void listAll() throws IOException {
		List<File> items = drive.listAll();
		items.stream().forEach(i-> System.out.println(i));
	}

	@Test
	public void listFiles() throws IOException {
		List<File> files = drive.listFiles();
		files.stream().forEach(f -> System.out.println(f));
	}

	@Test
	public void listFolders() throws IOException {
		List<File> folders = drive.listFolders();
		folders.stream().forEach(f -> System.out.println(f));
	}

	/* CREATE/REMOVE FILE */

	@Test
	public void createFile() throws IOException {
		File file = drive.createFile("text/plain", "Test create");
		Assert.assertNotNull(file);
		drive.removeFile(file.getId());
	}

	@Test
	public void removeFile() throws IOException {
		File file = drive.createFile("text/plain", "Test create");
		Assert.assertNotNull(file);
		drive.removeFile(file.getId());
	}

	/* MANAGING FILE */

	@Test
	public void copyFile() throws IOException {
		File file = drive.createFile("text/plain", "Test copy");
		File newFile = drive.copyFile(file.getId(), "Copied by B4D");
		Assert.assertNotNull(file);
		Assert.assertNotNull(newFile);
		drive.removeFile(file.getId());
		drive.removeFile(newFile.getId());
	}

	@Test
	public void moveFile() throws IOException {
		File folder = drive.createFolder("Test move");
		File file = drive.createFile("text/plain", "Test move");
		File newFile = drive.moveFile(file.getId(), folder.getId());
		Assert.assertEquals(file.getName(), newFile.getName());
		drive.removeFile(newFile.getId());
		drive.removeFolder(folder.getId());
	}

	@Test
	public void renameFile() throws IOException {
		File file = drive.createFile("text/plain", "Test rename");
		File newFile = drive.renameFile(file.getId(), "Renamed by B4D");
		Assert.assertEquals("Renamed by B4D", newFile.getName());
		drive.removeFile(newFile.getId());
	}

	/* UPLOAD/DOWNLOAD FILE */

	@Test
	public void uploadFile() throws IOException {
		java.io.File file = new java.io.File("Test upload");
		if(! file.exists())
			file.createNewFile();
		File newFile = drive.uploadFile("text/plain", "Uploaded by B4D", file);
		Assert.assertNotNull(newFile);
		file.delete();
		drive.removeFile(newFile.getId());
	}

	@Test
	public void downloadFile() throws IOException {
		java.io.File file = drive.downloadFile("1BEnsmHu3EACmxvBlizY1c_PWpp9DQsxz", "Downloaded by B4D");
		Assert.assertNotNull(file);
	}

	/* FOLDER */

	@Test
	public void createFolder() throws IOException {
		File folder = drive.createFolder("Dossier test");
		Assert.assertNotNull(folder);
		drive.removeFolder(folder.getId());
	}

	@Test
	public void removeFolder() throws IOException {
		File folder = drive.createFolder("Dossier test");
		Assert.assertNotNull(folder);
		drive.removeFolder(folder.getId());
	}

	/* NAVIGATION */

	@Test
	public void stepInto() throws IOException {
		File folder = drive.createFolder("Dossier de test");
		drive.stepInto(folder.getId());
		createFile();
		stepBack();
		listAll();
	}

	@Test
	public void stepBack() {
		drive.stepBack();
	}
}
