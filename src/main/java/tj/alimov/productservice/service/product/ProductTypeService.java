package tj.alimov.productservice.service.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.product.request.ProductTypeRequest;
import tj.alimov.productservice.dto.product.response.ProductTypeDto;
import tj.alimov.productservice.exception.product.ProductTypeExistsException;
import tj.alimov.productservice.exception.product.ProductTypeNotFoundException;

import tj.alimov.productservice.model.ProductType;

import tj.alimov.productservice.mapper.ProductTypeMapper;
import tj.alimov.productservice.model.ProductType;

import tj.alimov.productservice.repository.product.ProductTypeRepository;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    @Transactional
    public ProductTypeDto createProductType(ProductTypeRequest request){
        if(productTypeRepository.existsByName(request.getName())){
            throw new ProductTypeExistsException("ProductType with current name already exits");
        }
        ProductType productType = ProductTypeMapper.toProductType(request);
        productTypeRepository.save(productType);
        return ProductTypeMapper.toDto(productType);
    }

    public ProductTypeDto getProductTypeDto(Long id){
        ProductType productType = getProductType(id);
        return ProductTypeMapper.toDto(productType);
    }
    public Page<ProductTypeDto> getProductTypes(Pageable pageable){
        Page<ProductType> page = productTypeRepository.findAll(pageable);
        return ProductTypeMapper.toDtoPage(page);
    }
    public ProductTypeDto findByName(String name){
        ProductType productType = productTypeRepository.findByName(name).orElseThrow(() -> new ProductTypeNotFoundException("Product type with given name not found"));
        return ProductTypeMapper.toDto(productType);
    }
    public ProductType getProductType(Long id){
        return productTypeRepository.findById(id).orElseThrow(() -> new ProductTypeNotFoundException("Product with given id was not found"));
    }
    public boolean existsById(Long id){
        return productTypeRepository.existsById(id);
    }

}
