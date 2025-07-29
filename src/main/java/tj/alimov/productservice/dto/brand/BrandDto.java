package tj.alimov.productservice.dto.brand;

import lombok.Builder;
import lombok.Data;
import tj.alimov.productservice.dto.img.ImageDto;

import java.util.List;


public record BrandDto(String name, String slug, String description, String url, List<ImageDto> images){}
