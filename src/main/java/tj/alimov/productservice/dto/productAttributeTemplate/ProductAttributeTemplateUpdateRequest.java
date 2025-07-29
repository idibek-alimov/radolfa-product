package tj.alimov.productservice.dto.productAttributeTemplate;

public record ProductAttributeTemplateUpdateRequest(String productTypeSlug, String slug, String name, Boolean required, String type) {
}
