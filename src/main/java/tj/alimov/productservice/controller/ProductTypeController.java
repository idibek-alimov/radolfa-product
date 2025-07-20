package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.product.request.ProductTypeRequest;
import tj.alimov.productservice.dto.product.response.ProductTypeDto;
import tj.alimov.productservice.service.product.ProductTypeService;

@RestController
@RequestMapping("product-types/")
@RequiredArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @PostMapping("")
    public ResponseEntity<ProductTypeDto> createProductType(@RequestBody ProductTypeRequest request){
        ProductTypeDto dto = productTypeService.createProductType(request);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductTypeDto> getProductType(@PathVariable("id") Long id){
        ProductTypeDto dto = productTypeService.getProductTypeDto(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Page<ProductTypeDto>> getProductTypePage(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<ProductTypeDto> pageList = productTypeService.getProductTypes(PageRequest.of(page, size));
        return ResponseEntity.ok(pageList);
    }

    @GetMapping("/search")
    public ResponseEntity<ProductTypeDto> findByName(@RequestParam String name){
        ProductTypeDto dto = productTypeService.findByName(name);
        return ResponseEntity.ok(dto);
    }
}
