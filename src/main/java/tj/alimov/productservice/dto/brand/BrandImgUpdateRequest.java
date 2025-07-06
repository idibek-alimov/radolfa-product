package tj.alimov.productservice.dto.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandImgUpdateRequest {
    private Long id;
    private MultipartFile img;
}
