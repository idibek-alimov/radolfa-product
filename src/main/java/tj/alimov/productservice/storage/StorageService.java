package tj.alimov.productservice.storage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.UUID;

@Service
public class StorageService {

    public String saveFile(MultipartFile file){
        String name = getName();
        return name;
    }
    public String getName(){
        String timeStamp = Instant.now().toString().replace(":", "-");
        String name = timeStamp + "_" + UUID.randomUUID().toString();
        return name;
    }
}
