package tj.alimov.productservice.dto.productAttributeValue;

public record ProductAttributeValueCreationRequest(String productSlug, String templateSlug, String value) {
}
