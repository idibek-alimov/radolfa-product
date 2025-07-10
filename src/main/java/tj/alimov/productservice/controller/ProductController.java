package tj.alimov.productservice.controller;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.product.request.ProductRequest;
import tj.alimov.productservice.dto.product.request.ProductUpdateRequest;
import tj.alimov.productservice.dto.product.response.ProductDto;
import tj.alimov.productservice.exception.user.TokenNotProvidedException;
import tj.alimov.productservice.module.ProductType;
import tj.alimov.productservice.service.JwtService;
import tj.alimov.productservice.service.product.ProductService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final JwtService service;
    private final ProductService productService;

//    @PostMapping("/token")
//    public ResponseEntity<Void> manageToken(@RequestBody String token){
//        System.out.println(token);
//        Claims claims = service.extractClaims(token);
//        System.out.println(claims.get("roles"));
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/admin")
//    public ResponseEntity<String> hasPermission(){
//        System.out.println("User has role admin");
//        return ResponseEntity.ok("Hello");
//    }

    @PostMapping("")
    public ResponseEntity<Void> createProduct(HttpServletRequest servletRequest, @RequestBody ProductRequest request){
        String token = retrieveToken(servletRequest);
        productService.createProduct(request, token);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
        ProductDto productDto = productService.getProductDto(id);
        return ResponseEntity.ok(productDto);
    }
    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Page<ProductDto>> getProducts(@PathVariable("page") Integer page, @PathVariable Integer size){
        Page<ProductDto> productDtos = productService.getProducts(PageRequest.of(page, size));
        return ResponseEntity.ok(productDtos);
    }

    @PutMapping("")
    public ResponseEntity<Void> updateProduct(HttpServletRequest servletRequest, @RequestBody ProductUpdateRequest request){
        String token = retrieveToken(servletRequest);
        productService.updateProduct(request, token);
        return ResponseEntity.ok().build();
    }

    private String retrieveToken(HttpServletRequest request){
        String header = request.getHeader("Authentication");
        if(header == null || !header.startsWith("Bearer")){
            throw new TokenNotProvidedException("Token is not provided");
        }
        String token = header.substring("Bearer ".length());
        return token;
    }
}
