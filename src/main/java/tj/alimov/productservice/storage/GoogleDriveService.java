//package tj.alimov.productservice.storage;
//
//import com.google.api.client.http.FileContent;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.model.File;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import tj.alimov.productservice.config.GoogleDriveConfig;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.security.GeneralSecurityException;
//
//@Service
//public class GoogleDriveService {
//    private final GoogleDriveConfig googleDriveConfig;
//
//    @Autowired
//    public GoogleDriveService(GoogleDriveConfig googleDriveConfig) {
//        this.googleDriveConfig = googleDriveConfig;
//    }
//
//    public String uploadImage(MultipartFile multipartFile, String name) throws IOException, GeneralSecurityException {
//        // Convert MultipartFile to temporary file
//        Path tempFile = Files.createTempFile("upload-", multipartFile.getOriginalFilename());
//        multipartFile.transferTo(tempFile.toFile());
//
//        // Set up file metadata
//        File fileMetadata = new File();
//        fileMetadata.setName(name);
//        String mimeType = multipartFile.getContentType();
//        FileContent mediaContent = new FileContent(mimeType, tempFile.toFile());
//
//        // Upload to Google Drive
//        Drive driveService = googleDriveConfig.getDriveService();
//        File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
//                .setFields("id, webContentLink")
//                .execute();
//
//        // Clean up temporary file
//        Files.delete(tempFile);
//
//        // Return the file's web content link
//        return uploadedFile.getWebContentLink();
//    }
//}