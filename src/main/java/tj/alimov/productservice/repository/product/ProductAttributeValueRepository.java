package tj.alimov.productservice.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueDto;
import tj.alimov.productservice.model.product.ProductAttributeValue;

import java.util.List;
import java.util.Optional;

public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, Long> {

    @Query("SELECT NEW tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueDto(pav.slug, pat.name, pav.value) " +
            "FROM ProductAttributeValue pav " +
            "JOIN pav.template pat " +
            "WHERE pav.slug = :slug")
    Optional<ProductAttributeValueDto> findDtoBySlug(@Param("slug") String slug);

    @Query("SELECT NEW tj.alimov.productservice.dto.productAttributeValue.ProductAttributeValueDto(pav.slug, pat.name, pav.value) " +
            "FROM ProductAttributeValue pav " +
            "JOIN pav.template pat " +
            "WHERE pav.product.slug = :slug")
    List<ProductAttributeValueDto> findAllDTOsByProductSlug(@Param("slug") String slug);
    @Query("SELECT pav FROM ProductAttributeValue  pav WHERE pav.slug = :slug")
    Optional<ProductAttributeValue> findBySlug(@Param("slug") String slug);
    @Query("SELECT CASE WHEN COUNT(pav) > 0 THEN true ELSE false END FROM ProductAttributeValue pav WHERE pav.slug = :slug")
    boolean existsBySlug(@Param("slug") String slug);
}
