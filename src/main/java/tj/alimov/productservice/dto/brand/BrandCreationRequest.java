package tj.alimov.productservice.dto.brand;

import lombok.Data;

@Data
public class BrandCreationRequest {
    private String name;
    private String description;
}
