package fr.B4D.google;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.common.collect.Lists;

public class GoogleAuthorize {
	
	  public static final String CLOUD_PLATFORM = "https://www.googleapis.com/auth/cloud-platform";
	  public static final String SQLSERVICE_ADMIN = "https://www.googleapis.com/auth/sqlservice.admin";
	  public static final String SPREADSHEET = "https://www.googleapis.com/auth/spreadsheets";
	  public static final String DRIVE = "https://www.googleapis.com/auth/drive";
	
    public static Credential authorize(String credentials) throws IOException, GeneralSecurityException {
    	//InputStream in = GoogleAuthorize.class.getResourceAsStream("/fr/B4D/google/b4d_service.json");
    	InputStream in = new FileInputStream(credentials);
    	GoogleCredential credential = GoogleCredential.fromStream(in).createScoped(getAllCredentials());
        return credential;
    }
    
    private static ArrayList<String> getAllCredentials(){
    	return Lists.newArrayList(CLOUD_PLATFORM,
    			SQLSERVICE_ADMIN,
    			SPREADSHEET,
    			DRIVE);
    }
}
