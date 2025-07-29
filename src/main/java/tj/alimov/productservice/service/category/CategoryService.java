package tj.alimov.productservice.service.category;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.util.Arrays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tj.alimov.productservice.dto.category.CategoryCreationRequest;
import tj.alimov.productservice.dto.category.CategoryDto;
import tj.alimov.productservice.dto.category.CategoryUpdateRequest;
import tj.alimov.productservice.exception.category.CategoryExistsException;
import tj.alimov.productservice.exception.category.CategoryNotFoundException;
import tj.alimov.productservice.mapper.category.CategoryMapper;
import tj.alimov.productservice.model.category.Category;
import tj.alimov.productservice.repository.category.CategoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /** Create new Category */
    @Transactional
    public CategoryDto createCategory(CategoryCreationRequest request){
        Category parentCategory = null;
        if(request.parentCategorySlug() != null){
            parentCategory = findBySlug(request.parentCategorySlug());
        }
        if(existByName(request.name())){
            throw new CategoryExistsException("Category with the given name already exists");
        }

        Category category = CategoryMapper.toCategory(request, parentCategory);
        category.setSlug(generateUniqueSlug(category.getName()));
        categoryRepository.save(category);
        return CategoryMapper.toCategoryDto(category);
    }
    /** Get Category by slug */
    public CategoryDto getBySlug(String slug){
        Category category = findBySlug(slug);
        return CategoryMapper.toCategoryDto(category);
    }
    /** Update Category */
    @Transactional
    public CategoryDto updateCategory(CategoryUpdateRequest request){
        Category category = findBySlug(request.slug());
        Category parentCategory = null;
        if(request.parentCategorySlug() != null){
            parentCategory = findBySlug(request.parentCategorySlug());
        }
        category.setName(request.name());
        validateNoCircularDependency(category, parentCategory);
        category.setParentCategory(parentCategory);
        categoryRepository.save(category);

        return CategoryMapper.toCategoryDto(category);
    }
//    /** Delete by slug */
//    public void deleteCategory(String slug){
//        /** Just to check if category exists*/
//        findBySlug(slug);
//        categoryRepository.deleteBySlug(slug);
//    }
    /** Get all categories */
    public Page<CategoryDto> getAll(Pageable pageable){
        Page<Category> page = categoryRepository.findAll(pageable);
        return CategoryMapper.toCategoryDtoPage(page);
    }
    /** Get All child categories of given category*/
    @Transactional(readOnly = true)
    public List<CategoryDto> getSubcategories(String slug){
        Category category = findBySlug(slug);
        List<Category> subcategories = category.getChildCategories();
        return CategoryMapper.toCategoryDtoList(subcategories);
    }
    /** Get full path(up to top ancestor) of the given category */
    @Transactional(readOnly = true)
    public List<CategoryDto> getFullPath(String slug){
        Category category = findBySlug(slug);
        category = category.getParentCategory();
        List<Category> categories = new ArrayList<>();
        while(category != null){
            categories.add(category);
            category = category.getParentCategory();
        }
        Collections.reverse(categories);
        return CategoryMapper.toCategoryDtoList(categories);
    }

    /** Helper Functions */
    private Category findBySlug(String slug){
        return categoryRepository.findBySlug(slug).orElseThrow(() -> new CategoryNotFoundException("Category with given slug not found"));
    }

    private boolean existByName(String name){
        return categoryRepository.existsByName(name);
    }

    private String generateUniqueSlug(String name){
        String baseSlug = name.trim().toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
        String slug = baseSlug;
        int suffix = 1;

        while(categoryRepository.existsBySlug(slug)){
            slug = baseSlug + "-" + (suffix++);
        }
        return slug;
    }

    private void validateNoCircularDependency(Category child, Category parent){
        while(parent != null){
            if(parent.getId() == child.getId()){
                throw new IllegalArgumentException("Category can't be its own parent");
            }
            parent = parent.getParentCategory();
        }
    }
}
