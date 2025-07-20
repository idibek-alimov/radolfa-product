package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.product.request.ProductAttributeTemplateRequest;
import tj.alimov.productservice.dto.product.response.ProductAttributeTemplateDto;
import tj.alimov.productservice.module.ProductAttributeTemplate;
import tj.alimov.productservice.service.product.ProductAttributeTemplateService;

import java.util.List;

@RestController
@RequestMapping("/product-attribute-templates")
@RequiredArgsConstructor
public class ProductAttributeTemplateController {
    private final ProductAttributeTemplateService productAttributeTemplateService;

    @PostMapping("")
    public ResponseEntity<ProductAttributeTemplateDto> create(ProductAttributeTemplateRequest request){
        ProductAttributeTemplateDto dto = productAttributeTemplateService.createTemplate(request);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductAttributeTemplateDto> get(@PathVariable("id") Long id){
        ProductAttributeTemplateDto templateDto = productAttributeTemplateService.getProductAttributeTemplateDto(id);
        return ResponseEntity.ok(templateDto);
    }

    @GetMapping("/product-type/{id}")
    public ResponseEntity<List<ProductAttributeTemplateDto>> getByProductType(@PathVariable("id") Long id){
        List<ProductAttributeTemplateDto> templates = productAttributeTemplateService.getByProductType(id);
        return ResponseEntity.ok(templates);
    }
}
