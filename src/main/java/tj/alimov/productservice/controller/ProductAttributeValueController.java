package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueCreationRequest;
import tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueDto;
import tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueUpdateRequest;
import tj.alimov.productservice.model.product.ProductAttributeValue;
import tj.alimov.productservice.service.product.ProductAttributeValueService;

import java.util.List;

@RestController
@RequestMapping("/product-attribute-value")
@RequiredArgsConstructor
public class ProductAttributeValueController {
    private final ProductAttributeValueService productAttributeValueService;

    @PostMapping("")
    public ResponseEntity<ProductAttributeValueDto> createProductAttributeValue(@RequestBody ProductAttributeValueCreationRequest request){
        ProductAttributeValueDto dto = productAttributeValueService.createProductAttributeValue(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ProductAttributeValueDto> getPAV(@PathVariable String slug){
        ProductAttributeValueDto dto = productAttributeValueService.getProductAttributeValue(slug);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/product/{slug}")
    public ResponseEntity<List<ProductAttributeValueDto>> getByProductSlug(@PathVariable String slug){
        List<ProductAttributeValueDto> list = productAttributeValueService.getAllByProductSlug(slug);
        return ResponseEntity.ok(list);
    }

    @PutMapping("")
    public ResponseEntity<ProductAttributeValueDto> updatePAV(@RequestBody ProductAttributeValueUpdateRequest request){
        ProductAttributeValueDto dto = productAttributeValueService.updateProductAttributeValue(request);
        return ResponseEntity.ok(dto);
    }
}
