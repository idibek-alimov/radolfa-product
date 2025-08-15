package tj.alimov.productservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.productArticle.ProductArticleCreationRequest;
import tj.alimov.productservice.dto.productArticle.ProductArticleDto;
import tj.alimov.productservice.dto.productArticle.ProductArticleListDto;
import tj.alimov.productservice.dto.productArticle.ProductArticleUpdateRequest;
import tj.alimov.productservice.exception.productArticle.ProductArticleIllegalAccessException;
import tj.alimov.productservice.service.JwtService;
import tj.alimov.productservice.service.productArticle.ProductArticleService;
import tj.alimov.productservice.service.user.UserService;

@RestController
@RequestMapping("/product-article")
@RequiredArgsConstructor
public class ProductArticleController {
    private final ProductArticleService productArticleService;
    private final JwtService jwtService;


    @PostMapping("")
    public ResponseEntity<ProductArticleDto> createProductArticle(HttpServletRequest http, @RequestBody ProductArticleCreationRequest request){
        Long sellerId = jwtService.getUserId(http);
        System.out.println("Seller id = " + sellerId);
        ProductArticleDto productArticleDto = productArticleService.createProductArticle(request, sellerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productArticleDto);
    }

    @GetMapping("{slug}")
    public ResponseEntity<ProductArticleDto> getProductArticle(@PathVariable("slug") String slug){
        ProductArticleDto dto = productArticleService.getProductArticle(slug);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("")
    public ResponseEntity<ProductArticleDto> updateProductArticle(HttpServletRequest http, @RequestBody ProductArticleUpdateRequest request){
        Long sellerId = jwtService.getUserId(http);
        ProductArticleDto dto = productArticleService.updateProductArticle(request, sellerId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Page<ProductArticleListDto>> getAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<ProductArticleListDto> pageDTOs = productArticleService.getAll(PageRequest.of(page, size));
        return ResponseEntity.ok(pageDTOs);
    }
}
