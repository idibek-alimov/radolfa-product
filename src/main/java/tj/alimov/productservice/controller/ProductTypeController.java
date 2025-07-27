package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.productType.ProductTypeCreationRequest;
import tj.alimov.productservice.dto.productType.ProductTypeDto;
import tj.alimov.productservice.service.product.ProductTypeService;

@RestController
@RequestMapping("product-types/")
@RequiredArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @PostMapping("")
    public ResponseEntity<ProductTypeDto> createProductType(@RequestBody ProductTypeCreationRequest request){
        ProductTypeDto dto = productTypeService.createProductType(request);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/{slug}")
    public ResponseEntity<ProductTypeDto> getProductType(@PathVariable("slug") String slug){
        ProductTypeDto dto = productTypeService.getProductType(slug);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Page<ProductTypeDto>> getProductTypePage(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<ProductTypeDto> pageList = productTypeService.getAll(PageRequest.of(page, size));
        return ResponseEntity.ok(pageList);
    }

}
