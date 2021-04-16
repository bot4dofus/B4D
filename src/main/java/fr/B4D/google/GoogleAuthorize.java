package fr.B4D.google;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.common.collect.Lists;

/**
 * The {@code GoogleAuthorize} class is used to manage Google API authorizations.
 * 
 * @author Lucas
 *
 */
public class GoogleAuthorize {
	
	/**
	 * Cloud platform API url.
	 */
	public static final String CLOUD_PLATFORM = "https://www.googleapis.com/auth/cloud-platform";

	/**
	 * Sql service admin API url.
	 */
	public static final String SQLSERVICE_ADMIN = "https://www.googleapis.com/auth/sqlservice.admin";

	/**
	 * Spreadsheet API url.
	 */
	public static final String SPREADSHEET = "https://www.googleapis.com/auth/spreadsheets";

	/**
	 * Drive API url.
	 */
	public static final String DRIVE = "https://www.googleapis.com/auth/drive";

    /**
     * Returns the Google API credentials from a file.
     * @param credentials - Path to the file containing the Google API credentials.
     * @return Google credentials.
     * @throws IOException if no file has been found.
     */
    public static Credential authorize(String credentials) throws IOException {
    	InputStream in = new FileInputStream(credentials);
    	GoogleCredential credential = GoogleCredential.fromStream(in).createScoped(getAllScopes());
        return credential;
    }
    
    /**
     * Returns a list of user scopes.
     * @return Scopes of the user.
     */
    private static ArrayList<String> getAllScopes(){
    	return Lists.newArrayList(CLOUD_PLATFORM,
    			SQLSERVICE_ADMIN,
    			SPREADSHEET,
    			DRIVE);
    }
}
