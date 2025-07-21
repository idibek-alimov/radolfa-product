package tj.alimov.productservice.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tj.alimov.productservice.dto.img.ImageCreationRequest;
import tj.alimov.productservice.model.brand.Brand;
import tj.alimov.productservice.model.brand.BrandImage;

import java.time.Instant;
import java.util.UUID;

@Service
public class StorageService {

    public String saveFile(MultipartFile file){
        String name = getName();
        return name;
    }

    public BrandImage saveBrandImage(ImageCreationRequest request){
        BrandImage brandImage = new BrandImage();
        brandImage.setBaseUrl(getName());
        brandImage.setThumbnailUrl(getName() + "thumbnail");
        brandImage.setPosition(request.position());
        return brandImage;
    }
    public String getName(){
        String timeStamp = Instant.now().toString().replace(":", "-");
        String name = timeStamp + "_" + UUID.randomUUID().toString();
        return name;
    }
}
