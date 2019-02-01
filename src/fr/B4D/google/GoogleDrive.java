package fr.B4D.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
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
    private String id;
    
	  /*************/
	 /** BUILDER **/
	/*************/
    
    public GoogleDrive(String id) throws IOException, GeneralSecurityException {
    	this.id = id;
        Credential credential = GoogleAuthorizeUtil.authorize();
        
    	drive = new Drive.Builder(
    	          GoogleNetHttpTransport.newTrustedTransport(), 
    	          JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME).build();
    }
    
	  /**********/
	 /** LIST **/
	/**********/
    
    public List<File> listFiles() throws IOException{
    	String query = null;
    	if(id == null)
    		query = " mimeType != 'application/vnd.google-apps.folder' and 'root' in parents";
    	else
    		query = " mimeType != 'application/vnd.google-apps.folder' and '" + id + "' in parents";
    	return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }
    
    public List<File> listFolders() throws IOException{
    	String query = null;
    	if(id == null)
    		query = " mimeType == 'application/vnd.google-apps.folder' and 'root' in parents";
    	else
    		query = " mimeType == 'application/vnd.google-apps.folder' and '" + id + "' in parents";
        return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }
    
	  /**************/
	 /** ADD FILE **/
	/**************/
    
    public File createFile(String type, String name) throws IOException {
    	File file = new File();
    	file.setName(name);
        file.setParents(Arrays.asList(id));
        file.setMimeType(type);
        return drive.files().create(file).execute();
    }
    
    public File uploadFile(String type, String name, java.io.File _file) throws IOException {
        AbstractInputStreamContent uploadStreamContent = new FileContent(type, _file);
    	File file = new File();
    	file.setName(name);
        file.setParents(Arrays.asList(id));
        file.setMimeType(type);
        return drive.files().create(file, uploadStreamContent).execute();
    }
    
    public File copyFile(String fileId, String name) throws IOException {
    	File file = new File();
    	file.setName(name);
        file.setParents(Arrays.asList(id));
        return drive.files().copy(fileId, file).execute();
    }
    
	  /*****************/
	 /** REMOVE FILE **/
	/*****************/
    
    public void removeFile(String fileId) {
    	try {
			drive.files().delete(fileId).execute();
		} catch (IOException e) {
			//Do nothing
		}
    }
    
	  /************/
	 /** FOLDER **/
	/************/
    	
	public void createFolder(String name) throws IOException {
		createFile("application/vnd.google-apps.folder",  name);
	}
	
	public void removeFolder(String fileId) {
		removeFile(fileId);
	}
	
	  /************/
	 /** STATIC **/
	/************/
	
    public static String getIdFromUrl(String url) {
    	int begin = url.indexOf("/folders/");
    	String subUrl = url.substring(begin+9);
    	return subUrl.substring(0, subUrl.indexOf("?"));
    }
}
