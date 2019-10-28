package fr.B4D.google;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.common.collect.Lists;

/** La classe {@code GoogleAuthorize} permet de gérer les autorisations de connexion aux API google. 
 */
public class GoogleAuthorize {
	
	  public static final String CLOUD_PLATFORM = "https://www.googleapis.com/auth/cloud-platform";
	  public static final String SQLSERVICE_ADMIN = "https://www.googleapis.com/auth/sqlservice.admin";
	  public static final String SPREADSHEET = "https://www.googleapis.com/auth/spreadsheets";
	  public static final String DRIVE = "https://www.googleapis.com/auth/drive";
	
    /** 
     * @param credentials - Chemin vers le fichier contenant le certificat d'utilisation des API google.
     * @return Certificat d'utilisation des API google.
     * @throws IOException Si aucun fichier n'a été trouvé.
     */
    public static Credential authorize(String credentials) throws IOException {
    	InputStream in = new FileInputStream(credentials);
    	GoogleCredential credential = GoogleCredential.fromStream(in).createScoped(getAllScopes());
        return credential;
    }
    
    /** retourne la liste des droits de l'utilisateur.
     * @return Liste des droits de l'utilisateur.
     */
    private static ArrayList<String> getAllScopes(){
    	return Lists.newArrayList(CLOUD_PLATFORM,
    			SQLSERVICE_ADMIN,
    			SPREADSHEET,
    			DRIVE);
    }
}
