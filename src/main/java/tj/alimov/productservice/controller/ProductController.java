package tj.alimov.productservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.product.ProductCreationRequest;
import tj.alimov.productservice.dto.product.ProductDto;
import tj.alimov.productservice.dto.product.ProductUpdateRequest;
import tj.alimov.productservice.exception.user.TokenNotProvidedException;
import tj.alimov.productservice.service.JwtService;
import tj.alimov.productservice.service.product.ProductService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final JwtService service;
    private final ProductService productService;

    @PostMapping("")
    public ResponseEntity<ProductDto> createProduct(HttpServletRequest servletRequest, @RequestBody ProductCreationRequest request){
        Long sellerId = service.getUserId(servletRequest);
        System.out.println("Seller Id = " + sellerId);
        ProductDto dto = productService.createProduct(request, sellerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @GetMapping("/{slug}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("slug") String slug){
        ProductDto productDto = productService.getProduct(slug);
        return ResponseEntity.ok(productDto);
    }
    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Page<ProductDto>> getProducts(@PathVariable("page") @Min(0) int page, @PathVariable("size") @Min(1) int size){
        log.info("Page request of - page={}, size={}", page, size);
        Page<ProductDto> productDtos = productService.getAll(PageRequest.of(page, size));
        return ResponseEntity.ok(productDtos);
    }
//
    @PutMapping("")
    public ResponseEntity<ProductDto> updateProduct(HttpServletRequest servletRequest, @RequestBody ProductUpdateRequest request){
        Long sellerId = service.getUserId(servletRequest);
        ProductDto dto = productService.updateProduct(request, sellerId);
        return ResponseEntity.ok(dto);
    }

}
