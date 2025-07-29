package tj.alimov.productservice.mapper.product;


import tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueDto;
import tj.alimov.productservice.model.product.ProductAttributeValue;

public class ProductAttributeValueMapper {
    public static ProductAttributeValueDto toDto(ProductAttributeValue pav){
        return new ProductAttributeValueDto(pav.getSlug(), pav.getTemplate().getName(), pav.getValue());
    }
}
