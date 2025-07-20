package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.brand.BrandCreationRequest;
import tj.alimov.productservice.dto.brand.BrandDto;
import tj.alimov.productservice.service.brand.BrandService;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/admin")
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandCreationRequest request){
        BrandDto response = brandService.createBrand(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<BrandDto> getById(@PathVariable("id") Long id){
        BrandDto brandDto = brandService.getBrandDto(id);
        return ResponseEntity.ok(brandDto);
    }

    @GetMapping("/public")
    public ResponseEntity<List<BrandDto>> getAll(@PathVariable("id") Long id){
        List<BrandDto> list = brandService.getBrands();
        return ResponseEntity.ok(list);
    }

}
