package tj.alimov.productservice.service.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.product.response.ProductAttributeTemplateDto;
import tj.alimov.productservice.dto.product.request.ProductAttributeTemplateRequest;
import tj.alimov.productservice.exception.product.ProductAttributeTemplateException;
import tj.alimov.productservice.exception.product.ProductTypeNotFoundException;
import tj.alimov.productservice.mapper.ProductAttributeTemplateMapper;
import tj.alimov.productservice.module.ProductAttributeTemplate;
import tj.alimov.productservice.module.ProductType;
import tj.alimov.productservice.repository.product.ProductAttributeTemplateRepository;
import tj.alimov.productservice.repository.product.ProductTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAttributeTemplateService {
    private final ProductTypeRepository productTypeRepository;
    private final ProductAttributeTemplateRepository productAttributeTemplateRepository;

    @Transactional
    public ProductAttributeTemplateDto createTemplate(ProductAttributeTemplateRequest request){
        ProductType productType = validatePATRequest(request);
        ProductAttributeTemplate template = ProductAttributeTemplateMapper.toProductAttributeTemplate(request, productType);
        productAttributeTemplateRepository.save(template);
        return ProductAttributeTemplateMapper.toDto(template);
    }

    public ProductAttributeTemplateDto getProductAttributeTemplateDto(Long id){
        ProductAttributeTemplate template = productAttributeTemplateRepository.findById(id).orElseThrow(() -> new ProductAttributeTemplateException("Product Attribute Template not found with given name"));
        return ProductAttributeTemplateMapper.toDto(template);
    }
    public List<ProductAttributeTemplateDto> getByProductType(Long id){
        List<ProductAttributeTemplate> templates = productAttributeTemplateRepository.findByProductType(id);
        return ProductAttributeTemplateMapper.toDtoList(templates);
    }
    private ProductType validatePATRequest(ProductAttributeTemplateRequest request){
        if(productAttributeTemplateRepository.existsByNameAndType(request.getName(), request.getProductTypeId())){
            throw new ProductAttributeTemplateException("Product attribute template with given product name and productTypeId already exists");
        }
        return productTypeRepository.findById(request.getProductTypeId()).orElseThrow(() -> new ProductTypeNotFoundException("ProductType with given id not found"));
    }
}
