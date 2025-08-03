package tj.alimov.productservice.dto.sizeOption;

public record SizeOptionCreationRequest(String value, Integer order, String fitTypeSlug, String region, String sizeTypeSlug) {
}
