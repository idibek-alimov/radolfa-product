package tj.alimov.productservice.dto.productPriceHistory;

import tj.alimov.productservice.model.productArticle.ProductArticle;

import java.math.BigDecimal;

public record ProductPriceHistoryCreationRequest(ProductArticle productArticle, BigDecimal price, BigDecimal discountPercentage) {
}
