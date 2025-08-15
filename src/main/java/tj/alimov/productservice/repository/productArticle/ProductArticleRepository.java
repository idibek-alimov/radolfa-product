package tj.alimov.productservice.repository.productArticle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.dto.productArticle.ProductArticleRawDto;
import tj.alimov.productservice.dto.productArticle.ProductArticleRawListDto;
import tj.alimov.productservice.model.productArticle.ProductArticle;

import java.util.Optional;

public interface ProductArticleRepository extends JpaRepository<ProductArticle, Long> {
    @Query("SELECT CASE WHEN COUNT(pa)>0 THEN true ELSE false END FROM ProductArticle pa WHERE pa.slug = :slug")
    boolean existsBySlug(@Param("slug")String slug);

    @Query("SELECT NEW tj.alimov.productservice.dto.productArticle.ProductArticleRawDto(" +
            "pa.slug, " +
            "pa.productBaseIdentifier, " +
            "pph.price, " +
            "pph.discountPercentage, " +
            "p.name) " +
            "FROM ProductArticle pa " +
            "JOIN pa.product p " +
            "JOIN pa.productPriceHistories pph " +
            "WHERE pa.slug = :slug AND pph.active = true")
    Optional<ProductArticleRawDto> findProductArticleDtoBySlug(@Param("slug")String slug);

    @Query("SELECT pa FROM ProductArticle pa WHERE pa.slug = :slug")
    Optional<ProductArticle> findBySlug(@Param("slug")String slug);

    @Query(value = "SELECT NEW tj.alimov.productservice.dto.productArticle.ProductArticleRawListDto(" +
            "pa.slug, " +
            "pa.productBaseIdentifier, " +
            "pph.price, " +
            "pph.discountPercentage, " +
            "p.name) " +
            "FROM ProductArticle pa " +
            "JOIN pa.product p " +
            "JOIN pa.productPriceHistories pph " +
            "WHERE pph.active = true",
        countQuery = "SELECT COUNT(pa) FROM ProductArticle pa")
    Page<ProductArticleRawListDto> findAllListDTOs(Pageable pageable);

}
