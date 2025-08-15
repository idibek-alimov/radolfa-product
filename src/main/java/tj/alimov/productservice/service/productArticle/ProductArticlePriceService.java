package tj.alimov.productservice.service.productArticle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tj.alimov.productservice.dto.productPriceHistory.ProductPriceHistoryCreationRequest;
import tj.alimov.productservice.model.productArticle.ProductPriceHistory;
import tj.alimov.productservice.repository.productArticle.ProductPriceHistoryRepository;

@Service
@RequiredArgsConstructor
public class ProductArticlePriceService {

    private final ProductPriceHistoryRepository productPriceHistoryRepository;

    @Transactional
    public ProductPriceHistory createPriceHistory(ProductPriceHistoryCreationRequest request){
        productPriceHistoryRepository.deactivateAll(request.productArticle());
        ProductPriceHistory priceHistory = ProductPriceHistory.builder()
                .productArticle(request.productArticle())
                .price(request.price())
                .discountPercentage(request.discountPercentage())
                .build();
        productPriceHistoryRepository.save(priceHistory);
        return priceHistory;
    }
}
