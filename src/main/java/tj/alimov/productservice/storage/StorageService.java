package tj.alimov.productservice.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tj.alimov.productservice.dto.img.ImageCreationRequest;
import tj.alimov.productservice.model.brand.Brand;
import tj.alimov.productservice.model.brand.BrandImage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final GoogleDriveService googleDriveService;
    public String saveFile(MultipartFile file){
        String name = getName();
        return name;
    }

    public BrandImage saveBrandImage(ImageCreationRequest request) {
        BrandImage brandImage = new BrandImage();
        String name = getName();
        try {
            googleDriveService.uploadImage(request.image(), name);
        }catch (IOException e){
            System.out.println("IOException " + e);
        }catch (GeneralSecurityException e){
            System.out.println("GeneralSecurityException " + e);
        }

        brandImage.setBaseUrl(name);
        brandImage.setThumbnailUrl(name + "thumbnail");
        brandImage.setPosition(request.position());
        return brandImage;
    }
    public String getName(){
        String timeStamp = Instant.now().toString().replace(":", "-");
        String name = timeStamp + "_" + UUID.randomUUID().toString();
        return name;
    }
}
