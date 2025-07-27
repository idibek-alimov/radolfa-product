package tj.alimov.productservice.dto.productType;

import lombok.Data;


public record ProductTypeUpdateRequest(String name, String slug) {
}
