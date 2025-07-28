package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.productAttributeTemplate.ProductAttributeTemplateCreationRequest;
import tj.alimov.productservice.dto.productAttributeTemplate.ProductAttributeTemplateDto;
import tj.alimov.productservice.dto.productAttributeTemplate.ProductAttributeTemplateUpdateRequest;
import tj.alimov.productservice.service.product.ProductAttributeTemplateService;

import java.util.List;

@RestController
@RequestMapping("/product-attribute-templates")
@RequiredArgsConstructor
public class ProductAttributeTemplateController {
    private final ProductAttributeTemplateService productAttributeTemplateService;

    @PostMapping("")
    public ResponseEntity<ProductAttributeTemplateDto> create(ProductAttributeTemplateCreationRequest request){
        ProductAttributeTemplateDto dto = productAttributeTemplateService.createTemplate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @GetMapping("/{slug}")
    public ResponseEntity<ProductAttributeTemplateDto> getPAT(@PathVariable("slug") String slug){
        ProductAttributeTemplateDto templateDto = productAttributeTemplateService.getProductAttributeTemplate(slug);
        return ResponseEntity.ok(templateDto);
    }
    @GetMapping("/product-type/{slug}")
    public ResponseEntity<List<ProductAttributeTemplateDto>> getByProductType(@PathVariable("slug") String slug){
        List<ProductAttributeTemplateDto> templates = productAttributeTemplateService.getByProductType(slug);
        return ResponseEntity.ok(templates);
    }

    @PutMapping("")
    public ResponseEntity<ProductAttributeTemplateDto> updatePAT(@RequestBody ProductAttributeTemplateUpdateRequest request){
        ProductAttributeTemplateDto template = productAttributeTemplateService.updateProductAttributeTemplate(request);
        return ResponseEntity.ok(template);
    }

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Page<ProductAttributeTemplateDto>> getByProductType(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<ProductAttributeTemplateDto> templates = productAttributeTemplateService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(templates);
    }
}
