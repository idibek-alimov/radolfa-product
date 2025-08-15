package tj.alimov.productservice.repository.productArticle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.model.productArticle.ProductArticle;
import tj.alimov.productservice.model.productArticle.ProductVariant;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    @Query("SELECT CASE WHEN COUNT(pv)>0 THEN true ELSE false END FROM ProductVariant pv WHERE pv.skuCode = :skuCode")
    boolean existsBySkuCode(@Param("skuCode") String skuCode);

    @Query("SELECT pv FROM ProductVariant pv WHERE pv.productArticle = :productArticle")
    List<ProductVariant> findByProductArticle(@Param("productArticle") ProductArticle productArticle);
}
