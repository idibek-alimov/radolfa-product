package tj.alimov.productservice.mapper.product;

import tj.alimov.productservice.dto.product.productAttributeValue.ProductAttributeValueDto;
import tj.alimov.productservice.dto.product.productAttributeValue.ProductAttributeValueRequest;
import tj.alimov.productservice.module.Product;
import tj.alimov.productservice.module.ProductAttributeTemplate;
import tj.alimov.productservice.module.ProductAttributeValue;

public class ProductAttributeValueMapper {
    public static ProductAttributeValue toProductAttributeValue(ProductAttributeValueRequest request, Product product, ProductAttributeTemplate template){
        return ProductAttributeValue.builder()
                .value(request.value())
                .product(product)
                .template(template)
                .build();
    }

    public static ProductAttributeValueDto toDto(ProductAttributeValue productAttributeValue){
        return new ProductAttributeValueDto(productAttributeValue.getTemplate().getName(), productAttributeValue.getValue());
    }
}
