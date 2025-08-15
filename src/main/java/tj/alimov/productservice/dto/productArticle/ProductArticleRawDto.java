package tj.alimov.productservice.dto.productArticle;

import tj.alimov.productservice.dto.img.ImageDto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public record ProductArticleRawDto(
         String slug,
         BigInteger productBaseIdentifier,
         BigDecimal currentPrice,
         BigDecimal discountPercentage,
         String name) {
}
