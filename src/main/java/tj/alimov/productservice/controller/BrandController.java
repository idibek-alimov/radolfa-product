package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tj.alimov.productservice.dto.brand.BrandCreationRequest;
import tj.alimov.productservice.dto.brand.BrandDto;
import tj.alimov.productservice.dto.brand.BrandUpdateRequest;
import tj.alimov.productservice.dto.img.ImageCreationRequest;
import tj.alimov.productservice.service.brand.BrandService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/")
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandCreationRequest request){
        BrandDto response = brandService.createBrand(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/public/{slug}")
    public ResponseEntity<BrandDto> getById(@PathVariable("slug") String slug){
        BrandDto brandDto = brandService.getBrandBySlug(slug);
        return ResponseEntity.ok(brandDto);
    }

    @PutMapping("/")
    public ResponseEntity<BrandDto> updateBrand(@RequestBody BrandUpdateRequest request){
        BrandDto brandDto = brandService.updateBrand(request);
        return ResponseEntity.status(HttpStatus.OK).body(brandDto);
    }

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Page<BrandDto>> getAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<BrandDto> pageList = brandService.getAll(PageRequest.of(page, size));
        return ResponseEntity.ok(pageList);
    }

    @PostMapping(value = "/images/{slug}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BrandDto> addImages(@PathVariable("slug") String slug, @RequestPart("images") MultipartFile[] images, @RequestPart("positions") Integer[] positions){
        List<ImageCreationRequest> list = new ArrayList<>();
        for(int i = 0; i < images.length; i++){
            list.add(new ImageCreationRequest(images[i], positions[i]));
        }
        BrandDto brandDto = brandService.addImages(slug, list);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandDto);
    }

    @DeleteMapping("/images")
    public ResponseEntity<Void> deleteImages(@RequestBody List<String> slugs){
        brandService.deleteImages(slugs);
        return ResponseEntity.noContent().build();
    }

}
