package tj.alimov.productservice.dto.category;

import lombok.Data;

@Data
public class CategoryCreationRequest {
    private String name;
    private String parentCategorySlug;
}
