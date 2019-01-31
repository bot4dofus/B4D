package fr.B4D.google.sheet;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.common.collect.Lists;

public class GoogleAuthorizeUtil {
	
	  public static final String CLOUD_PLATFORM = "https://www.googleapis.com/auth/cloud-platform";
	  public static final String SQLSERVICE_ADMIN = "https://www.googleapis.com/auth/sqlservice.admin";
	  public static final String SPREADSHEET = "https://www.googleapis.com/auth/spreadsheets";
	  public static final String DRIVE = "https://www.googleapis.com/auth/drive";
	
    public static Credential authorize() throws IOException, GeneralSecurityException {
    	InputStream in = GoogleAuthorizeUtil.class.getResourceAsStream("/main/resources/b4d_service.json");
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
