package fr.B4D.google;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

/**
 * The {@code GoogleDrive} is used to access usefull methods related to Google Drive.
 * 
 * @author Lucas
 *
 */
public class GoogleDrive {
	
    /**
     * Name of the application accessing the files.
     */
    private static final String APPLICATION_NAME = "B4D-program";
	
    /**
     * Returns the id of a directory from its url.
     * @param url - Url of the directory.
     * @return Id of the parent directory.
     */
    public static String getIdFromUrl(String url) {
    	int begin = url.indexOf("/folders/");
    	String subUrl = url.substring(begin+9);
    	return subUrl.substring(0, subUrl.indexOf("?"));
    }
    
    /**
     * Google Drive instance.
     */
    private Drive drive;
    
    /**
     * List of folder ids for navigation.
     */
    private ArrayList<String> idS;

	/**
	 * Constructor of the {@code GoogleDrive} class.
     * @param id - Id of the parent directory, {@code null} if root.
     * @param credentials - Path to the Google API credentials files.
     * @throws IOException if no file has been found.
     * @throws GeneralSecurityException if a security issue occurs.
     */
    public GoogleDrive(String id, String credentials) throws IOException, GeneralSecurityException {
    	if (id == null)
    		id = "root";
    	
       	this.idS = new ArrayList<String>();
    	this.idS.add(id);
    	
        Credential credential = GoogleAuthorize.authorize(credentials);
    	drive = new Drive.Builder(
    	          GoogleNetHttpTransport.newTrustedTransport(), 
    	          JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME).build();
    }
    
    /**
     * List all the files and folders in the current directory.
     * @return Files and folders in the current directory.
     * @throws IOException if cannot perform the operation.
     */
    public List<File> listAll() throws IOException{
    	String query = "'" + getCurrentId() + "' in parents";
    	return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }

    /**
     * List all the files in the current directory.
     * @return Files in the current directory.
     * @throws IOException if cannot perform the operation.
     */
    public List<File> listFiles() throws IOException{
    	String query = " mimeType != 'application/vnd.google-apps.folder' and '" + getCurrentId() + "' in parents";
    	return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }

    /**
     * List all the folders in the current directory.
     * @return Folders in the current directory.
     * @throws IOException if cannot perform the operation.
     */
    public List<File> listFolders() throws IOException{
    	String query = " mimeType = 'application/vnd.google-apps.folder' and '" + getCurrentId() + "' in parents";
        return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }
    
    /**
     * Create a file un the current directory.
     * @param type - File type.
     * @param name - Name of the file.
     * @return Created file.
     * @throws IOException if cannot perform the operation.
     */
    public File createFile(String type, String name) throws IOException {
    	File file = new File();
    	file.setName(name);
        file.setParents(Arrays.asList(getCurrentId()));
        file.setMimeType(type);
        return drive.files().create(file).execute();
    }
    
    /**
     * Removes a file.
     * @param fileId - Id of the file.
     */
    public void removeFile(String fileId) {
    	try {
			drive.files().delete(fileId).execute();
		} catch (IOException e) {
			//Do nothing if the file does not exist
		}
    }
    
    /**
     * Copy a file in the same directory.
     * @param fileId - Id of the file to copy.
     * @param name - Name of the copy.
     * @return Copied file.
     * @throws IOException if cannot perform the operation.
     */
    public File copyFile(String fileId, String name) throws IOException {
    	File file = new File();
    	file.setName(name);
    	file.setParents(Arrays.asList(getCurrentId()));
        return drive.files().copy(fileId, file).execute();
    }
    
    /**
     * Moves a file from a directory to another.
     * @param fileId - Id of the file to move.
     * @param folderId - Id of the destination directory.
     * @return Moved file.
     * @throws IOException if cannot perform the operation.
     */
    public File moveFile(String fileId, String folderId) throws IOException {
    	File file = drive.files().get(fileId).execute();
    	File newFile = new File();
    	newFile.setName(file.getName());
    	newFile.setParents(Arrays.asList(folderId));
    	File out = drive.files().copy(fileId, newFile).execute();
    	removeFile(file.getId());
    	return out;
    }
    
    /**
     * Renames a file.
     * @param fileId - Id of the file to rename.
     * @param name - New name of the file.
     * @return Renamed file.
     * @throws IOException if cannot perform the operation.
     */
    public File renameFile(String fileId, String name) throws IOException {
    	File file = copyFile(fileId, name);
    	removeFile(fileId);
    	return file;
    }
    
    /**
     * Upload a local file to Google Drive.
     * @param type - Type of the file..
     * @param name - Name of the file after upload.
     * @param file - Path to the file to upload.
     * @return Uploaded file.
     * @throws IOException if cannot perform the operation.
     */
    public File uploadFile(String type, String name, java.io.File file) throws IOException {
        AbstractInputStreamContent uploadStreamContent = new FileContent(type, file);
    	File localFile = new File();
    	localFile.setName(name);
    	localFile.setParents(Arrays.asList(getCurrentId()));
    	localFile.setMimeType(type);
        return drive.files().create(localFile, uploadStreamContent).execute();
    }
    
    /**
     * Downloads a file from Google Drive.
     * @param fileId - Id of the file to download.
     * @param name - Name of the file after download.
     * @return Downloaded file.
     * @throws IOException if cannot perform the operation.
     */
    public java.io.File downloadFile(String fileId, String name) throws IOException{  	
    	OutputStream outputStream = new ByteArrayOutputStream();
    	drive.files().export(fileId, "text/plain").executeMediaAndDownloadTo(outputStream);
    	return null;
    }
    	
	/**
	 * Creates a folder in the current directory.
	 * @param name - Name of the folder.
	 * @return Created folder.
     * @throws IOException if cannot perform the operation.
	 */
	public File createFolder(String name) throws IOException {
		return createFile("application/vnd.google-apps.folder",  name);
	}
	
	/**
	 * Removes a folder.
	 * @param fileId - Id of the folder to remove.
	 */
	public void removeFolder(String fileId) {
		removeFile(fileId);
	}
	
	/** Permet de se déplacer dans un dossier.
	 * Moves the current directory to a folder.
	 * @param folderId - Id of the folder to go to.
     * @throws IOException if cannot perform the operation.
	 */
	public void stepInto(String folderId) throws IOException {
		File folder = drive.files().get(folderId).execute();
		if(folder != null)
			this.idS.add(folder.getId());
	}
	
	/**
	 * Moves the current directory one step back.
	 */
	public void stepBack() {
		if(this.idS.size() > 1)
			this.idS.remove(getCurrentId());
	}
	
    /** Permet de récupérer l'id du dossier courant.
     * Returns the id of the current direcory.
     * @return Id of the current directory.
     */
    private String getCurrentId() {
    	return this.idS.get(idS.size() - 1);
    }
}
