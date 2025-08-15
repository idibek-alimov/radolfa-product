package tj.alimov.productservice.repository.productArticle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.model.productArticle.ProductArticle;
import tj.alimov.productservice.model.productArticle.ProductPriceHistory;

public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistory, Long> {

    @Modifying
    @Query("UPDATE ProductPriceHistory pph SET pph.active = false " +
            "WHERE pph.productArticle = :productArticle " +
            "AND pph.active = true")
    void deactivateAll(@Param("productArticle")ProductArticle productArticle);
}
