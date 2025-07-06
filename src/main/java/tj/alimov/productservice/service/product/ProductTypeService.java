package tj.alimov.productservice.service.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.product.ProductTypeRequest;
import tj.alimov.productservice.dto.product.ProductTypeResponse;
import tj.alimov.productservice.exception.product.ProductTypeExistsException;
import tj.alimov.productservice.exception.product.ProductTypeNotFoundException;
import tj.alimov.productservice.module.ProductType;
import tj.alimov.productservice.repository.product.ProductTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    @Transactional
    public ProductTypeResponse createProductType(ProductTypeRequest request){
        if(productTypeRepository.existsByName(request.getName())){
            throw new ProductTypeExistsException("ProductType with current name already exits");
        }

        ProductType productType = new ProductType(request.getName());
        productTypeRepository.save(productType);

        return new ProductTypeResponse(productType.getName());
    }

    public ProductTypeResponse getProductType(Long id){
        ProductType productType = productTypeRepository.findById(id).orElseThrow(() -> new ProductTypeNotFoundException("Product with given id was not found"));

        return new ProductTypeResponse(productType.getName());
    }

    public List<ProductTypeResponse> getProductTypes(Pageable pageable){
        PageRequest
    }

}
