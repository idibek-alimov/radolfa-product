package tj.alimov.productservice.dto.product.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductAttributeTemplateDto {
    private String name;
    private Boolean required;
}
