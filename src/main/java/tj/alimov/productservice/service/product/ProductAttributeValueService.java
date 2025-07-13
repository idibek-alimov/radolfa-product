package tj.alimov.productservice.service.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.product.productAttributeValue.ProductAttributeValueRequest;
import tj.alimov.productservice.repository.product.ProductAttributeValueRepository;
import tj.alimov.productservice.service.user.UserService;

@Service
@RequiredArgsConstructor
public class ProductAttributeValueService {
    private final ProductAttributeValueRepository repository;
    private final UserService userService;
    private final ProductService productService;
    @Transactional
    public void create(ProductAttributeValueRequest request, Long id){
        userService.validateUser(id);

    }
}
