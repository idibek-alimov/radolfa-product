package tj.alimov.productservice.repository.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tj.alimov.productservice.model.brand.Brand;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Brand c WHERE c.name = :name")
    boolean existsByName(String name);

    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Brand c WHERE c.slug = :slug")
    boolean existsBySlug(String slug);

    @Query(value = "SELECT b FROM Brand b WHERE LOWER(b.slug) = LOWER(:slug)")
    Optional<Brand> findBySlug(String slug);


}
