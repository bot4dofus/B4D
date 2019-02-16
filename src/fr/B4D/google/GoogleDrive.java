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

public class GoogleDrive {
	
	  /***************/
	 /** CONSTANTS **/
	/***************/
	
    private static final String APPLICATION_NAME = "B4D-program";
 
	  /**************/
	 /** ATRIBUTS **/
	/**************/
    
    private Drive drive;
    private ArrayList<String> idS;
    
	  /*************/
	 /** BUILDER **/
	/*************/
    
    public GoogleDrive(String id) throws IOException, GeneralSecurityException {
    	if (id == null)
    			id = "root";
    	
       	this.idS = new ArrayList<String>();
    	this.idS.add(id);
    	
        Credential credential = GoogleAuthorize.authorize();
    	drive = new Drive.Builder(
    	          GoogleNetHttpTransport.newTrustedTransport(), 
    	          JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME).build();
    }
    
	  /**********/
	 /** LIST **/
	/**********/
    
    public List<File> listAll() throws IOException{
    	String query = "'" + getCurrentId() + "' in parents";
    	return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }
    
    public List<File> listFiles() throws IOException{
    	String query = " mimeType != 'application/vnd.google-apps.folder' and '" + getCurrentId() + "' in parents";
    	return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }
    
    public List<File> listFolders() throws IOException{
    	String query = " mimeType = 'application/vnd.google-apps.folder' and '" + getCurrentId() + "' in parents";
        return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }
    
	  /************************/
	 /** CREATE/REMOVE FILE **/
	/************************/
    
    public File createFile(String type, String name) throws IOException {
    	File file = new File();
    	file.setName(name);
        file.setParents(Arrays.asList(getCurrentId()));
        file.setMimeType(type);
        return drive.files().create(file).execute();
    }
    
    public void removeFile(String fileId) {
    	try {
			drive.files().delete(fileId).execute();
		} catch (IOException e) {
			//Do nothing if the file does not exist
		}
    }
    
	  /*******************/
	 /** MANAGING FILE **/
	/*******************/
    
    public File copyFile(String fileId, String name) throws IOException {
    	File file = new File();
    	file.setName(name);
    	file.setParents(Arrays.asList(getCurrentId()));
        return drive.files().copy(fileId, file).execute();
    }
    
    public File moveFile(String fileId, String folderId) throws IOException {
    	File file = drive.files().get(fileId).execute();
    	File newFile = new File();
    	newFile.setName(file.getName());
    	newFile.setParents(Arrays.asList(folderId));
    	File out = drive.files().copy(fileId, newFile).execute();
    	removeFile(file.getId());
    	return out;
    }
    
    public File renameFile(String fileId, String name) throws IOException {
    	File file = copyFile(fileId, name);
    	removeFile(fileId);
    	return file;
    }
    
	  /**************************/
	 /** UPLOAD/DOWNLOAD FILE **/
	/**************************/
    
    public File uploadFile(String type, String name, java.io.File _file) throws IOException {
        AbstractInputStreamContent uploadStreamContent = new FileContent(type, _file);
    	File file = new File();
    	file.setName(name);
        file.setParents(Arrays.asList(getCurrentId()));
        file.setMimeType(type);
        return drive.files().create(file, uploadStreamContent).execute();
    }
    
    public java.io.File downloadFile(String fileId, String name) throws IOException{  	
    	OutputStream outputStream = new ByteArrayOutputStream();
    	drive.files().export(fileId, "text/plain").executeMediaAndDownloadTo(outputStream);
    	return null;
    }
    
	  /************/
	 /** FOLDER **/
	/************/
    	
	public File createFolder(String name) throws IOException {
		return createFile("application/vnd.google-apps.folder",  name);
	}
	
	public void removeFolder(String fileId) {
		removeFile(fileId);
	}
	
	  /****************/
	 /** NAVIGATION **/
	/****************/
	
	public void stepInto(String folderId) throws IOException {
		File folder = drive.files().get(folderId).execute();
		if(folder != null)
			this.idS.add(folder.getId());
	}
	
	public void stepBack() {
		if(this.idS.size() > 1)
			this.idS.remove(getCurrentId());
	}
	
	  /************/
	 /** STATIC **/
	/************/
	
    public static String getIdFromUrl(String url) {
    	int begin = url.indexOf("/folders/");
    	String subUrl = url.substring(begin+9);
    	return subUrl.substring(0, subUrl.indexOf("?"));
    }
    
      /*************/
     /** PRIVATE **/
    /*************/

    private String getCurrentId() {
    	return this.idS.get(idS.size() - 1);
    }
}
