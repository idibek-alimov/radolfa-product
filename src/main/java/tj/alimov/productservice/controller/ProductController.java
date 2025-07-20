package tj.alimov.productservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.product.request.ProductRequest;
import tj.alimov.productservice.dto.product.request.ProductUpdateRequest;
import tj.alimov.productservice.dto.product.response.ProductDto;
import tj.alimov.productservice.service.JwtService;
import tj.alimov.productservice.service.product.ProductService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final JwtService service;
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<Void> createProduct(HttpServletRequest servletRequest, @RequestBody ProductRequest request){
        Long id = service.getUserId(servletRequest);
        productService.createProduct(request, id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
        ProductDto productDto = productService.getProductDto(id);
        return ResponseEntity.ok(productDto);
    }
    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Page<ProductDto>> getProducts(@PathVariable("page") @Min(0) int page, @PathVariable("size") @Min(1) int size){
        log.info("Page request of - page={}, size={}", page, size);
        Page<ProductDto> productDtos = productService.getProducts(PageRequest.of(page, size));
        return ResponseEntity.ok(productDtos);
    }

    @PutMapping("")
    public ResponseEntity<Void> updateProduct(HttpServletRequest servletRequest, @RequestBody ProductUpdateRequest request){
        Long id = service.getUserId(servletRequest);
        productService.updateProduct(request, id);
        return ResponseEntity.ok().build();
    }

}
