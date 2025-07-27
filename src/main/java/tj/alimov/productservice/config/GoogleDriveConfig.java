package tj.alimov.productservice.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Component
public class GoogleDriveConfig {
    private static final String APPLICATION_NAME = "Spring Boot Drive Upload";

    public Drive getDriveService() throws IOException, GeneralSecurityException {
        // Load client secrets from credentials file
        String credentialsPath = System.getProperty("GOOGLE_CREDENTIALS_PATH");
        GoogleClientSecrets clientSecrets;
        try (FileInputStream inputStream = new FileInputStream(credentialsPath)) {
            clientSecrets = GoogleClientSecrets.load(GsonFactory.getDefaultInstance(), new InputStreamReader(inputStream));
        }

        // Set up authorization flow
        List<String> scopes = Collections.singletonList(System.getProperty("GOOGLE_DRIVE_SCOPE"));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                clientSecrets,
                scopes)
                .setDataStoreFactory(new com.google.api.client.util.store.FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        // Authorize
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        // Build Drive service
        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}