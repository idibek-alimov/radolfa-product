package tj.alimov.productservice.dto.product;

public record ProductCreationRequest(String categorySlug, String brandSlug, String productTypeSlug, String name, String description) {

}
