package tj.alimov.productservice.dto.product;

public record ProductDto(String slug, String categorySlug, String brandSlug, String productTypeSlug, String name, String description) {
}
