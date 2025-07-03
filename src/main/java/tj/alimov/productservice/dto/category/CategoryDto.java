package tj.alimov.productservice.dto.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private String name;
    private String slug;
    private Long parentCategoryId;
}
