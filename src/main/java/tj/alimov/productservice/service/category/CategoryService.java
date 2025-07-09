package tj.alimov.productservice.service.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.category.CategoryCreationRequest;
import tj.alimov.productservice.dto.category.CategoryDto;
import tj.alimov.productservice.exception.category.CategoryDoesNotExistException;
import tj.alimov.productservice.exception.category.CategoryExistsException;
import tj.alimov.productservice.mapper.CategoryMapper;
import tj.alimov.productservice.module.Category;
import tj.alimov.productservice.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryDto createCategory(CategoryCreationRequest request){
        Category parentCategory = validateAndGetParentCategory(request.getParentCategoryId());
        validateCategoryCreationRequest(request);
        Category category = CategoryMapper.toCategory(request, parentCategory);
        categoryRepository.save(category);

        return CategoryMapper.toCategoryDto(category);
    }

    public CategoryDto getCategoryDto(Long id){
        Category category = getCategory(id);
        return CategoryMapper.toCategoryDto(category);
    }
    public Category getCategory(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryDoesNotExistException("Category with given name does not exist"));
    }
    public List<CategoryDto> getAll(){
        List<Category> list = categoryRepository.findAll();
        System.out.println("Category size = " + list.size());
        return CategoryMapper.toCategoryDtoList(list);
    }
    public boolean existById(Long id){
        return categoryRepository.existsById(id);
    }

    private Category validateAndGetParentCategory(Long id){
        if(id != null){
            return categoryRepository.findById(id).orElseThrow(() -> new CategoryDoesNotExistException("Category with given id does not exist"));
        }
        return null;
    }
    private void validateCategoryCreationRequest(CategoryCreationRequest request){
        if(categoryRepository.existsByName(request.getName())){
            throw new CategoryExistsException("Category with given name already exists");
        }
        if(categoryRepository.existsBySlug(request.getSlug())){
            throw new CategoryExistsException("Category with given slug already exists");
        }
    }
}
