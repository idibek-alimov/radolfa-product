package tj.alimov.productservice.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.module.ProductAttributeTemplate;
import tj.alimov.productservice.module.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductAttributeTemplateRepository extends JpaRepository<ProductAttributeTemplate, Long> {
    @Query("SELECT pat FROM ProductAttributeTemplate pat WHERE pat.name = :name AND pat.productType.id = :id")
    Optional<ProductAttributeTemplate> findByNameAndType(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT COUNT(pat) > 0 FROM ProductAttributeTemplate pat WHERE pat.name = :name AND pat.productType.id = :id")
    boolean existsByNameAndType(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT pat FROM ProductAttributeTemplate pat WHERE pat.productType.id = :id")
    List<ProductAttributeTemplate> findByProductType(@Param("id") Long id);

}
