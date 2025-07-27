package tj.alimov.productservice.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import tj.alimov.productservice.model.product.ProductType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
//    boolean existsByName(String name);
    @Query("SELECT ptype from ProductType ptype WHERE ptype.name = :name")
    Optional<ProductType> findByName(@Param("name") String name);
    @Query("SELECT ptype from ProductType ptype WHERE ptype.slug = :slug")
    Optional<ProductType> findBySlug(@Param("slug") String slug);

    @Query("SELECT CASE WHEN COUNT(pType) > 0 THEN true ELSE false END FROM ProductType pType WHERE pType.name = :name")
    boolean existsByName(String name);
    @Query("SELECT CASE WHEN COUNT(pType) > 0 THEN true ELSE false END FROM ProductType pType WHERE pType.slug = :slug")
    boolean existsBySlug(@Param("slug") String slug);
}
