package tj.alimov.productservice.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tj.alimov.productservice.dto.product.ProductCreationRequest;
import tj.alimov.productservice.dto.product.ProductDto;
import tj.alimov.productservice.dto.product.ProductUpdateRequest;
import tj.alimov.productservice.exception.product.ProductIllegalAccessException;
import tj.alimov.productservice.exception.product.ProductNotFoundException;


import tj.alimov.productservice.feign.UserServiceClient;
import tj.alimov.productservice.mapper.product.ProductMapper;
import tj.alimov.productservice.model.brand.Brand;
import tj.alimov.productservice.model.category.Category;
import tj.alimov.productservice.model.product.Product;
import tj.alimov.productservice.model.product.ProductType;
import tj.alimov.productservice.repository.product.ProductRepository;
import tj.alimov.productservice.service.brand.BrandService;
import tj.alimov.productservice.service.category.CategoryService;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductTypeService productTypeService;

    private final UserService userService;


    @Transactional
    public ProductDto createProduct(ProductCreationRequest request, Long sellerId){
        validateProductCreation(sellerId);
        Brand brand = brandService.findBrand(request.brandSlug());
        Category category = categoryService.findCategory(request.categorySlug());
        ProductType productType = productTypeService.findProductType(request.productTypeSlug());

        Product product = ProductMapper.toProduct(request, sellerId, productType, category, brand);
        product.setSlug(generateUniqueSlug(category.getSlug() + "-" + brand.getSlug() + "-" + request.name()));
        product.setSellerId(sellerId);
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    public ProductDto getProduct(String slug){
        return productRepository.getProductDto(slug).orElseThrow(() -> new ProductNotFoundException("Product with given slug not found"));
    }
    @Transactional
    public ProductDto updateProduct(ProductUpdateRequest request, Long sellerId){
        Product product = findProduct(request.slug());
        Brand brand = brandService.findBrand(request.brandSlug());
        Category category = categoryService.findCategory(request.categorySlug());
        ProductType productType = productTypeService.findProductType(request.productTypeSlug());
        validateProductUpdate(product, sellerId);

        product.setBrand(brand);
        product.setCategory(category);
        product.setProductType(productType);
        product.setName(request.name());
        product.setDescription(request.description());
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    public Page<ProductDto> getAll(Pageable pageable){
        return productRepository.getAll(pageable);
    }


    public Product findProduct(String slug){
        return productRepository.findBySlug(slug).orElseThrow(() -> new ProductNotFoundException("Product with given slug not found"));
    }
    private void validateProductCreation(Long sellerId){
        userService.validateUser(sellerId);
    }

    private void validateProductUpdate(Product product, Long sellerId){
        if(!product.getSellerId().equals(sellerId)){
            throw new ProductIllegalAccessException("Provided user is not Seller. Only seller can update the product");
        }
    }
    private String generateUniqueSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(productRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }

}
