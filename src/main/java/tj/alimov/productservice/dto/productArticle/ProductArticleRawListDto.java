package tj.alimov.productservice.dto.productArticle;

import java.math.BigDecimal;
import java.math.BigInteger;

public record ProductArticleRawListDto(
        String slug,
        BigInteger productBaseIdentifier,
        BigDecimal currentPrice,
        BigDecimal discountPercentage,
        String name) {
}
