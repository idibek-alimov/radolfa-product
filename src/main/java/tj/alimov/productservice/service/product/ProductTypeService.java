package tj.alimov.productservice.service.product;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.productType.ProductTypeCreationRequest;
import tj.alimov.productservice.dto.productType.ProductTypeDto;
import tj.alimov.productservice.dto.productType.ProductTypeUpdateRequest;
import tj.alimov.productservice.exception.productType.ProductTypeExistsException;
import tj.alimov.productservice.exception.productType.ProductTypeNotFoundException;

import tj.alimov.productservice.model.product.ProductType;

import tj.alimov.productservice.mapper.product.ProductTypeMapper;

import tj.alimov.productservice.repository.product.ProductTypeRepository;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    /** Get create product-type */
    @Transactional
    public ProductTypeDto createProductType(ProductTypeCreationRequest request){
        if(productTypeRepository.existsByName(request.name())){
            throw new ProductTypeExistsException("ProductType with current name already exits");
        }
        ProductType productType = ProductTypeMapper.toProductType(request);
        productTypeRepository.save(productType);
        return ProductTypeMapper.toDto(productType);
    }
    /** Get product-type by slug */
    public ProductTypeDto getProductType(String slug){
        ProductType productType = findProductType(slug);
        return ProductTypeMapper.toDto(productType);
    }
    /** Update product-type */
    @Transactional
    public ProductTypeDto updateProductType(ProductTypeUpdateRequest request){
        ProductType productType = findProductType(request.slug());
        productType.setName(request.name());
        productTypeRepository.save(productType);
        return ProductTypeMapper.toDto(productType);
    }
    /** Get all product-types */
    public Page<ProductTypeDto> getAll(Pageable pageable){
        Page<ProductType> page = productTypeRepository.findAll(pageable);
        return ProductTypeMapper.toDtoPage(page);
    }
    /** Find product-type by slug, if not found throw error. */
    public ProductType findProductType(String slug){
        return productTypeRepository.findBySlug(slug).orElseThrow(() -> new ProductTypeNotFoundException("Product type with given slug not found"));
    }
}
