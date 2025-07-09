package tj.alimov.productservice.dto.product.request;

import lombok.Data;
import tj.alimov.productservice.module.AttributeDataType;

@Data
public class ProductAttributeTemplateRequest {
    private String name;
    private boolean required;
    private AttributeDataType type;
    private Long productTypeId;
}
