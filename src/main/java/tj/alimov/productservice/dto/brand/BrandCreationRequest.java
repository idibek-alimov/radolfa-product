package tj.alimov.productservice.dto.brand;

import lombok.Data;

public record BrandCreationRequest(String name, String description, String url){}
