package tj.alimov.productservice.dto.productAttributeTemplate;

public record ProductAttributeTemplateCreationRequest(String productTypeSlug, String name, Boolean required, String type) {
}
