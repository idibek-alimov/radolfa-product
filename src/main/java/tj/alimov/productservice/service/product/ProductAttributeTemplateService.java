package tj.alimov.productservice.service.product;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.productAttributeTemplate.ProductAttributeTemplateCreationRequest;
import tj.alimov.productservice.dto.productAttributeTemplate.ProductAttributeTemplateDto;
import tj.alimov.productservice.dto.productAttributeTemplate.ProductAttributeTemplateUpdateRequest;
import tj.alimov.productservice.exception.productAttributeTemplate.ProductAttributeTemplateExistException;
import tj.alimov.productservice.exception.productAttributeTemplate.ProductAttributeTemplateNotFoundException;
import tj.alimov.productservice.exception.productType.ProductTypeNotFoundException;
import tj.alimov.productservice.mapper.product.ProductAttributeTemplateMapper;
import tj.alimov.productservice.model.AttributeDataType;
import tj.alimov.productservice.model.product.ProductAttributeTemplate;
import tj.alimov.productservice.model.product.ProductType;
import tj.alimov.productservice.repository.product.ProductAttributeTemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAttributeTemplateService {
    private final ProductTypeService productTypeService;
    private final ProductAttributeTemplateRepository productAttributeTemplateRepository;

    @Transactional
    public ProductAttributeTemplateDto createTemplate(ProductAttributeTemplateCreationRequest request){
        ProductType productType = validateCreationRequest(request);
        ProductAttributeTemplate template = ProductAttributeTemplateMapper.toProductAttributeTemplate(request, productType);
        template.setSlug(generateUniqueSlug(request.name()));
        productAttributeTemplateRepository.save(template);
        return ProductAttributeTemplateMapper.toDto(template);
    }

    public ProductAttributeTemplateDto getProductAttributeTemplate(String slug){
        ProductAttributeTemplate template = findProductAttributeTemplate(slug);
        return ProductAttributeTemplateMapper.toDto(template);
    }

    public List<ProductAttributeTemplateDto> getByProductType(String slug){
        List<ProductAttributeTemplate> templates = productAttributeTemplateRepository.findByProductType(slug);
        return ProductAttributeTemplateMapper.toDtoList(templates);
    }

    @Transactional
    public ProductAttributeTemplateDto updateProductAttributeTemplate(ProductAttributeTemplateUpdateRequest request){
        ProductType productType = validateUpdateRequest(request);
        ProductAttributeTemplate template = findProductAttributeTemplate(request.slug());
        template.setProductType(productType);
        template.setName(request.name());
        template.setRequired(request.required());
        template.setType(AttributeDataType.getByName(request.name()));
        productAttributeTemplateRepository.save(template);

        return ProductAttributeTemplateMapper.toDto(template);
    }

    public Page<ProductAttributeTemplateDto> findAll(Pageable pageable){
        Page<ProductAttributeTemplate> templates = productAttributeTemplateRepository.findAll(pageable);
        return ProductAttributeTemplateMapper.toDtoPage(templates);
    }
    public ProductAttributeTemplate findProductAttributeTemplate(String slug){
        return productAttributeTemplateRepository.findBySlug(slug).orElseThrow(()-> new ProductAttributeTemplateNotFoundException("Product Attribute Template with given slug not found"));
    }
    private ProductType validateCreationRequest(ProductAttributeTemplateCreationRequest request){
        ProductType productType = productTypeService.findProductType(request.productTypeSlug());
        if(productAttributeTemplateRepository.existsByNameAndType(request.name(), productType.getId())){
            throw new ProductAttributeTemplateExistException("Product attribute template with given product name and productType already exists");
        }
        return productType;
    }
    private ProductType validateUpdateRequest(ProductAttributeTemplateUpdateRequest request){
        ProductType productType = productTypeService.findProductType(request.productTypeSlug());
//        if(productAttributeTemplateRepository.existsByNameAndType(request.name(), productType.getId())){
//            throw new ProductAttributeTemplateExistException("Product attribute template with given product name and productType already exists");
//        }
        return productType;
    }

    private String generateUniqueSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(productAttributeTemplateRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }

}
