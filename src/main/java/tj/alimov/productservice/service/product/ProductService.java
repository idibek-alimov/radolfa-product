package tj.alimov.productservice.service.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.exception.product.ProductNotFoundException;
import tj.alimov.productservice.exception.product.ProductUpdateException;
import tj.alimov.productservice.mapper.product.ProductMapper;


import tj.alimov.productservice.model.product.Product;
import tj.alimov.productservice.repository.product.ProductRepository;
import tj.alimov.productservice.service.JwtService;
import tj.alimov.productservice.service.brand.BrandService;
import tj.alimov.productservice.service.category.CategoryService;
import tj.alimov.productservice.service.user.UserService;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
//    private final UserServiceClient userServiceClient;

//    @Transactional
//    public void createProduct(ProductRequest request, Long sellerId){
//        userService.validateUser(sellerId);
////        Brand brand = brandService.getBrand(request.getBrandId());
////        Category category = categoryService.getCategory(request.getCategoryId());
////        ProductType productType = productTypeService.getProductType(request.getProductTypeId());
//
////        Product product = ProductMapper.toProduct(request, sellerId, productType, category, brand);
////        productRepository.save(product);
//    }
//
//    public ProductDto getProductDto(Long id){
//        Product product = getProduct(id);
//        return ProductMapper.toProductDto(product);
//    }
//    public Product getProduct(Long id){
//        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with given id not found"));
//    }
//    public void updateProduct(ProductUpdateRequest request, Long sellerId){
//        userService.validateUser(sellerId);
//        Product product = getProduct(request.getId());
//        if(sellerId != product.getSellerId()){
//            throw new ProductUpdateException("Product does not belong to you. Only seller can update the products");
//        }
////        Brand brand = brandService.getBrand(request.getBrandId());
////        Category category = categoryService.getCategory(request.getCategoryId());
////        ProductType productType = productTypeService.getProductType(request.getProductTypeId());
//
////        product.setProductType(productType);
////        product.setBrand(brand);
////        product.setCategory(category);
//        product.setName(request.getName());
//        product.setDescription(request.getDescription());
//        productRepository.save(product);
//    }
//
//    public Page<ProductDto> getProducts(Pageable pageable){
//        Page<Product> products = productRepository.findAll(pageable);
//        return ProductMapper.toProductDtoList(products);
//    }

    public Product findProduct(String slug){
        return productRepository.findBySlug(slug).orElseThrow(() -> new ProductNotFoundException("Product with given slug not found"));
    }

}
