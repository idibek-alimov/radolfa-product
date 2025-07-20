package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.category.CategoryCreationRequest;
import tj.alimov.productservice.dto.category.CategoryDto;
import tj.alimov.productservice.dto.category.CategoryUpdateRequest;
import tj.alimov.productservice.service.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreationRequest request){
        CategoryDto response = categoryService.createCategory(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("slug") String slug){
        CategoryDto categoryDto = categoryService.getBySlug(slug);
        return ResponseEntity.ok(categoryDto);
    }

    @PutMapping("/")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryUpdateRequest request){
        CategoryDto response = categoryService.updateCategory(request);
        return ResponseEntity.ok(response);
    }
//    @DeleteMapping("/{slug}")
//    public ResponseEntity<Void> deleteCategory(@PathVariable String slug){
//        categoryService.deleteCategory(slug);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping("/all/{page}/{size}")
    public ResponseEntity<Page<CategoryDto>> getAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Page<CategoryDto> pageArray = categoryService.getAll(PageRequest.of(page, size));
        return ResponseEntity.ok(pageArray);
    }

    @GetMapping("/subcategories/{slug}")
    public ResponseEntity<List<CategoryDto>> getSubcategories(@PathVariable("slug") String slug){
        List<CategoryDto> list = categoryService.getSubcategories(slug);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/full-path/{slug}")
    public ResponseEntity<List<CategoryDto>> getFullPath(@PathVariable("slug") String slug){
        List<CategoryDto> list = categoryService.getFullPath(slug);
        return ResponseEntity.ok(list);
    }
}
