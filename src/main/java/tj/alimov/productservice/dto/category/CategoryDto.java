package tj.alimov.productservice.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public record CategoryDto(@NotBlank String name, String slug){}
