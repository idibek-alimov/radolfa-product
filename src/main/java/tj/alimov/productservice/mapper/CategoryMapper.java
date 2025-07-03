package tj.alimov.productservice.mapper;
import tj.alimov.productservice.dto.category.CategoryCreationRequest;
import tj.alimov.productservice.dto.category.CategoryDto;
import tj.alimov.productservice.module.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static Category toCategory(CategoryCreationRequest request){
        return toCategory(request, null);
    }
    public static Category toCategory(CategoryCreationRequest request, Category parentCategory){
        return Category.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .parentCategory(parentCategory)
                .build();
    }

    public static CategoryDto toCategoryDto(Category category){
        return CategoryDto.builder()
                .name(category.getName())
                .slug(category.getSlug())
                .parentCategoryId(category.getParentCategory().getId())
                .build();
    }

    public static List<CategoryDto> toCategoryDtoList(List<Category> list){
        return list.stream().map(CategoryMapper::toCategoryDto).collect(Collectors.toList());
    }
}
