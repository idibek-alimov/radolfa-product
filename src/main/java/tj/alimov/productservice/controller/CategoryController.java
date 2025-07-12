package tj.alimov.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tj.alimov.productservice.dto.category.CategoryCreationRequest;
import tj.alimov.productservice.dto.category.CategoryDto;
import tj.alimov.productservice.service.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/admin")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryCreationRequest request){
        CategoryDto response = categoryService.createCategory(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") Long id){
        CategoryDto categoryDto = categoryService.getCategoryDto(id);
        return ResponseEntity.ok(categoryDto);
    }

    @GetMapping("/public")
    public ResponseEntity<List<CategoryDto>> getAll(@PathVariable("id") Long id){
        List<CategoryDto> list = categoryService.getAll();
        return ResponseEntity.ok(list);
    }

}
