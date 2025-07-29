package tj.alimov.productservice.repository.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.model.brand.BrandImage;

import java.util.List;

public interface BrandImageRepository extends JpaRepository<BrandImage, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(bi) > 0 THEN true ELSE false END FROM BrandImage bi WHERE LOWER(bi.slug) = LOWER(:slug)")
    boolean existsBySlug(String slug);

    @Query(value = "SELECT bi FROM BrandImage bi WHERE LOWER(bi.slug) IN (LOWER(:slugs))")
    List<BrandImage> findBySlugCaseInsensitive(@Param("slugs") List<String> slugs);
}
