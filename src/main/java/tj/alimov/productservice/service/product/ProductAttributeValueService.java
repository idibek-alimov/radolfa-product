package tj.alimov.productservice.service.product;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueCreationRequest;
import tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueDto;
import tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueUpdateRequest;
import tj.alimov.productservice.exception.productAttributeValue.ProductAttributeValueNotFoundException;
import tj.alimov.productservice.mapper.product.ProductAttributeValueMapper;
import tj.alimov.productservice.model.product.Product;
import tj.alimov.productservice.model.product.ProductAttributeTemplate;
import tj.alimov.productservice.model.product.ProductAttributeValue;
import tj.alimov.productservice.repository.product.ProductAttributeValueRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductAttributeValueService {
//    private final UserService userService;
    private final ProductAttributeValueRepository productAttributeValueRepository;
    private final ProductAttributeTemplateService productAttributeTemplateService;
    private final ProductService productService;

    @Transactional
    public ProductAttributeValueDto createProductAttributeValue(ProductAttributeValueCreationRequest request) {
        ProductAttributeTemplate template = productAttributeTemplateService.findProductAttributeTemplate(request.templateSlug());
        Product product = productService.findProduct(request.productSlug());
        ProductAttributeValue pav = ProductAttributeValue.builder()
                .slug(generateUniqueSlug(product.getName() + template.getName()))
                .value(request.value())
                .template(template)
                .product(product)
                .build();

        productAttributeValueRepository.save(pav);
        return ProductAttributeValueMapper.toDto(pav);
    }
    public ProductAttributeValueDto getProductAttributeValue(String slug){
        ProductAttributeValueDto pav = productAttributeValueRepository.findDtoBySlug(slug).orElseThrow(() -> new ProductAttributeValueNotFoundException("Product attribute value with given slug not found"));
        return pav;
    }
    public List<ProductAttributeValueDto> getAllByProductSlug(String slug){
        return productAttributeValueRepository.findAllDTOsByProductSlug(slug);
    }
    @Transactional
    public ProductAttributeValueDto updateProductAttributeValue(ProductAttributeValueUpdateRequest request){
        ProductAttributeValue pav =  findProductAttributeValue(request.slug());
        pav.setValue(request.value());
        return ProductAttributeValueMapper.toDto(pav);
    }





//    public List<ProductAttributeValueDto> getByProduct(String slug)

    public ProductAttributeValue findProductAttributeValue(String slug){
        return productAttributeValueRepository.findBySlug(slug).orElseThrow(() -> new ProductAttributeValueNotFoundException("Product attribute value with given slug not found"));
    }
    private String generateUniqueSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(productAttributeValueRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }
}
