package tj.alimov.productservice.service.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.product.request.ProductRequest;
import tj.alimov.productservice.exception.user.UserNotFoundException;
import tj.alimov.productservice.feign.UserServiceClient;
import tj.alimov.productservice.module.Category;
import tj.alimov.productservice.module.Product;
import tj.alimov.productservice.repository.product.ProductRepository;
import tj.alimov.productservice.service.JwtService;
import tj.alimov.productservice.service.brand.BrandService;
import tj.alimov.productservice.service.category.CategoryService;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserServiceClient userServiceClient;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final JwtService jwtService;
    @Transactional
    public void createProduct(ProductRequest request, String token){

        validateUser(id);


    }

//    private Product createProduct(ProductRequest request, Long id){
//        Category category = category
//    }

    private void validateProduct(ProductRequest request, String token){
        Long id = jwtService.extractUserId(token);
        if(!userServiceClient.existsUserById(id)){
            throw new UserNotFoundException("User with given id was not found");
        }
        if()
    }
    private void validateUser(Long id){
        if(!userServiceClient.existsUserById(id)){
            throw new UserNotFoundException("User with given id was not found");
        }
    }
}
