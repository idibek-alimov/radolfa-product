package tj.alimov.productservice.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.model.product.ProductAttributeTemplate;

import java.util.List;
import java.util.Optional;

public interface ProductAttributeTemplateRepository extends JpaRepository<ProductAttributeTemplate, Long> {
    @Query("SELECT pat FROM ProductAttributeTemplate pat WHERE pat.name = :name AND pat.productType.id = :id")
    Optional<ProductAttributeTemplate> findByNameAndType(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT COUNT(pat) > 0 FROM ProductAttributeTemplate pat WHERE pat.name = :name AND pat.productType.id = :id")
    boolean existsByNameAndType(@Param("name") String name, @Param("id") Long id);


    @Query("SELECT pat FROM ProductAttributeTemplate pat WHERE pat.productType.slug = :slug")
    List<ProductAttributeTemplate> findByProductType(@Param("slug") String slug);

    @Query("SELECT pat FROM ProductAttributeTemplate pat WHERE pat.slug = :slug")
    Optional<ProductAttributeTemplate> findBySlug(@Param("slug") String slug);

    @Query("SELECT CASE WHEN COUNT(pat)>0 THEN true ELSE false END FROM ProductAttributeTemplate  pat WHERE pat.slug = :slug")
    boolean existsBySlug(@Param("slug") String slut);
}
