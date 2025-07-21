package tj.alimov.productservice.dto.brand;

import lombok.Data;

public record BrandUpdateRequest(String name, String slug, String description, String url){}
