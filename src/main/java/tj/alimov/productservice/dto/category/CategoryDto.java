package tj.alimov.productservice.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;


public record CategoryDto(@NotBlank String name, String slug){}
