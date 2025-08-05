package tj.alimov.productservice.dto.sizeOption;

public record SizeOptionUpdateRequest(String slug, Integer order, String value, String fitTypeSlug, String region, String sizeTypeSlug) {
}
