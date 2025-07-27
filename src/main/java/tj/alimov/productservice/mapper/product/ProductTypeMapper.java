package tj.alimov.productservice.mapper.product;

import org.springframework.data.domain.Page;
import tj.alimov.productservice.dto.productType.ProductTypeCreationRequest;
import tj.alimov.productservice.dto.productType.ProductTypeDto;
import tj.alimov.productservice.model.product.ProductType;

public class ProductTypeMapper {

    public static ProductTypeDto toDto(ProductType productType){
        return new ProductTypeDto(productType.getName(), productType.getSlug());
    }

    public static ProductType toProductType(ProductTypeCreationRequest request){
        return new ProductType(request.name());
    }

    public static Page<ProductTypeDto> toDtoPage(Page<ProductType> page){
        return page.map(ProductTypeMapper::toDto);
    }
}
