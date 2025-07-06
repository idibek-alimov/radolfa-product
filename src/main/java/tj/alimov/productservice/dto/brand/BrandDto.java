package tj.alimov.productservice.dto.brand;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandDto {
    private String name;
    private String description;
    private String imgUrl;
}
