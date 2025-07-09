package tj.alimov.productservice.mapper;


import tj.alimov.productservice.dto.product.request.ProductRequest;
import tj.alimov.productservice.module.Brand;
import tj.alimov.productservice.module.Category;
import tj.alimov.productservice.module.Product;
import tj.alimov.productservice.module.ProductType;

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
}
