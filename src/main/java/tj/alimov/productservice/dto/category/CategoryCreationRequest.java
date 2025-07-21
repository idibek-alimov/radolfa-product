package tj.alimov.productservice.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


public record CategoryCreationRequest(@NotBlank String name, String parentCategorySlug){}
