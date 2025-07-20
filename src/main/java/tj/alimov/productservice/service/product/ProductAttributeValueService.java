package tj.alimov.productservice.service.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.product.productAttributeValue.ProductAttributeValueRequest;
import tj.alimov.productservice.repository.product.ProductAttributeValueRepository;

@Service
@RequiredArgsConstructor
public class ProductAttributeValueService {
    private final ProductAttributeValueRepository repository;

    @Transactional
    public void create(ProductAttributeValueRequest request){

    }
}
