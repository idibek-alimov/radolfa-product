package tj.alimov.productservice.mapper;

import org.springframework.data.domain.Page;
import tj.alimov.productservice.dto.product.request.ProductTypeRequest;
import tj.alimov.productservice.dto.product.response.ProductTypeDto;
import tj.alimov.productservice.model.ProductType;

import java.util.List;

public class ProductTypeMapper {

    public static ProductTypeDto toDto(ProductType productType){
        return new ProductTypeDto(productType.getName());
    }

    public static ProductType toProductType(ProductTypeRequest request){
        return new ProductType(request.getName());
    }

    public static Page<ProductTypeDto> toDtoPage(Page<ProductType> page){
        return page.map(ProductTypeMapper::toDto);
    }
}
