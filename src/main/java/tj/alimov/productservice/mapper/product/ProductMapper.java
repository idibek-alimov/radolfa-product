package tj.alimov.productservice.mapper.product;


import org.springframework.data.domain.Page;

import tj.alimov.productservice.dto.product.ProductCreationRequest;
import tj.alimov.productservice.dto.product.ProductDto;
import tj.alimov.productservice.model.brand.Brand;
import tj.alimov.productservice.model.category.Category;
import tj.alimov.productservice.model.product.Product;
import tj.alimov.productservice.model.product.ProductType;

public class ProductMapper {
    public static Product toProduct(ProductCreationRequest request, Long sellerId, ProductType productType, Category category, Brand brand){
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .sellerId(sellerId)
                .category(category)
                .productType(productType)
                .brand(brand)
                .build();
    }
    public static ProductDto toDto(Product product){
        return new ProductDto(product.getSlug(), product.getCategory().getSlug(), product.getBrand().getSlug(), product.getProductType().getSlug(), product.getName(), product.getDescription());
    }
//
//    public static Page<ProductDto> toProductDtoList(Page<Product> products){
//        return products.map(ProductMapper::toProductDto);
////        return products.stream().map(ProductMapper::toProductDto).collect(Collectors.());
//    }
}
