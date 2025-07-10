package tj.alimov.productservice.mapper;


import org.springframework.data.domain.Page;
import tj.alimov.productservice.dto.product.request.ProductRequest;
import tj.alimov.productservice.dto.product.response.ProductDto;
import tj.alimov.productservice.module.Brand;
import tj.alimov.productservice.module.Category;
import tj.alimov.productservice.module.Product;
import tj.alimov.productservice.module.ProductType;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static Product toProduct(ProductRequest request, Long sellerId, ProductType productType, Category category, Brand brand){
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .sellerId(sellerId)
                .category(category)
                .productType(productType)
                .brand(brand)
                .build();
    }

    public static ProductDto toProductDto(Product product){
        return ProductDto.builder()
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

    public static Page<ProductDto> toProductDtoList(Page<Product> products){
        return products.map(ProductMapper::toProductDto);
//        return products.stream().map(ProductMapper::toProductDto).collect(Collectors.());
    }
}
