package tj.alimov.productservice.dto.productArticle;

import java.math.BigDecimal;
import java.util.List;

public record ProductArticleCreationRequest(BigDecimal price, BigDecimal discount, String productSlug, String colorSlug, List<String> sizeSlugs) {
}
