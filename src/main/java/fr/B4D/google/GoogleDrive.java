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

/** La classe {@code GoogleDrive} permet de gérer un drive grace à l'API drive de google.
 */
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

	/** Constructeur de la classe {@code GoogleDrive}. 
     * @param id - Id du répertoire parent.
     * @param credentials - Chemin vers le fichier contenant le certificat d'utilisation des API google.
     * @throws IOException Si aucun fichier n'a été trouvé.
     * @throws GeneralSecurityException Si problèmes de sécurité survient.
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
    
	  /**********/
	 /** LIST **/
	/**********/
    
    /** Liste les fichiers et dossiers du répertoire courant.
     * @return Liste des fichiers et dossiers.
     * @throws IOException Si impossible d'éxecuter l'opération.
     */
    public List<File> listAll() throws IOException{
    	String query = "'" + getCurrentId() + "' in parents";
    	return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }
    
    /** Liste les fichiers du répertoire courant.
     * @return Liste des fichiers.
     * @throws IOException Si impossible d'éxecuter l'opération.
     */
    public List<File> listFiles() throws IOException{
    	String query = " mimeType != 'application/vnd.google-apps.folder' and '" + getCurrentId() + "' in parents";
    	return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }
    
    /** Liste les dossiers du répertoire courant.
     * @return Liste des dossiers.
     * @throws IOException Si impossible d'éxecuter l'opération.
     */
    public List<File> listFolders() throws IOException{
    	String query = " mimeType = 'application/vnd.google-apps.folder' and '" + getCurrentId() + "' in parents";
        return drive.files().list().setQ(query).setPageSize(100).execute().getFiles();
    }
    
	  /************************/
	 /** CREATE/REMOVE FILE **/
	/************************/
    
    /** Créer un fichier.
     * @param type - Type du fichier.
     * @param name - Nom du fichier.
     * @return Fichier créé.
     * @throws IOException Si impossible d'éxecuter l'opération.
     */
    public File createFile(String type, String name) throws IOException {
    	File file = new File();
    	file.setName(name);
        file.setParents(Arrays.asList(getCurrentId()));
        file.setMimeType(type);
        return drive.files().create(file).execute();
    }
    
    /** Permet de supprimer un fichier. Ne fai rien si celui-ci n'existe pas.
     * @param fileId - Id du fichier.
     */
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
    
    /** Permet de copier un fichier.
     * @param fileId - Id du fichier à copier.
     * @param name - Nom de la copie.
     * @return Fichier copié.
     * @throws IOException Si impossible d'éxecuter l'opération.
     */
    public File copyFile(String fileId, String name) throws IOException {
    	File file = new File();
    	file.setName(name);
    	file.setParents(Arrays.asList(getCurrentId()));
        return drive.files().copy(fileId, file).execute();
    }
    
    /** Permet de déplacer un fichier.
     * @param fileId - Id du fichier à déplacer.
     * @param folderId - Id du répertoire vers lequel déplacer le fichier.
     * @return Fichier déplacé.
     * @throws IOException Si impossible d'éxecuter l'opération.
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
    
    /** Permet de renommer un fichier.
     * @param fileId - Id du fichier à renommer.
     * @param name - Nouveau nom du fichier.
     * @return Fichier renommé.
     * @throws IOException Si impossible d'éxecuter l'opération.
     */
    public File renameFile(String fileId, String name) throws IOException {
    	File file = copyFile(fileId, name);
    	removeFile(fileId);
    	return file;
    }
    
	  /**************************/
	 /** UPLOAD/DOWNLOAD FILE **/
	/**************************/
    
    /** Permet d'uploader un fichier vers le drive.
     * @param type - Type du fichier.
     * @param name - Nom du fichier sur le drive.
     * @param _file - Fichier à uploader sur le drive.
     * @return Fichier uploadé sur le drive.
     * @throws IOException Si impossible d'éxecuter l'opération.
     */
    public File uploadFile(String type, String name, java.io.File _file) throws IOException {
        AbstractInputStreamContent uploadStreamContent = new FileContent(type, _file);
    	File file = new File();
    	file.setName(name);
        file.setParents(Arrays.asList(getCurrentId()));
        file.setMimeType(type);
        return drive.files().create(file, uploadStreamContent).execute();
    }
    
    /** Permet de télécharger un fichier depuis le drive.
     * @param fileId - Id du fichier à télécharger.
     * @param name - Nom du fichier une fois téléchargé.
     * @return Fichier téléchargé.
     * @throws IOException Si impossible d'éxecuter l'opération.
     */
    public java.io.File downloadFile(String fileId, String name) throws IOException{  	
    	OutputStream outputStream = new ByteArrayOutputStream();
    	drive.files().export(fileId, "text/plain").executeMediaAndDownloadTo(outputStream);
    	return null;
    }
    
	  /************/
	 /** FOLDER **/
	/************/
    	
	/** Permet de créer un dossier.
	 * @param name - Nom du dossier.
	 * @return Dossier créé.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public File createFolder(String name) throws IOException {
		return createFile("application/vnd.google-apps.folder",  name);
	}
	
	/** Permet de supprimer un dossier.
	 * @param fileId - Id du dossier à supprimer.
	 */
	public void removeFolder(String fileId) {
		removeFile(fileId);
	}
	
	  /****************/
	 /** NAVIGATION **/
	/****************/
	
	/** Permet de se déplacer dans un dossier.
	 * @param folderId - Id du dossier dans lequel se déplacer.
     * @throws IOException Si impossible d'éxecuter l'opération.
	 */
	public void stepInto(String folderId) throws IOException {
		File folder = drive.files().get(folderId).execute();
		if(folder != null)
			this.idS.add(folder.getId());
	}
	
	/** Permet de revenir un dossier en arrière.
	 */
	public void stepBack() {
		if(this.idS.size() > 1)
			this.idS.remove(getCurrentId());
	}
	
	  /************/
	 /** STATIC **/
	/************/
	
    /** Permet de récupérer l'id d'un répertoire à partir de l'url.
     * @param url - Url du drive.
     * @return Id du dossier parent du drive.
     */
    public static String getIdFromUrl(String url) {
    	int begin = url.indexOf("/folders/");
    	String subUrl = url.substring(begin+9);
    	return subUrl.substring(0, subUrl.indexOf("?"));
    }
    
      /*************/
     /** PRIVATE **/
    /*************/

    /** Permet de récupérer l'id du dossier courant.
     * @return Id du dossier courant.
     */
    private String getCurrentId() {
    	return this.idS.get(idS.size() - 1);
    }
}
