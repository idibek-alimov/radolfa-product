package tj.alimov.productservice.dto.brand;

import lombok.Builder;
import lombok.Data;
import tj.alimov.productservice.dto.img.ImageDto;

@Data
public record BrandDto(String name, String slug, String description, String url, ImageDto[] images){}
