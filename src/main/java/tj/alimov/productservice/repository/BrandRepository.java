package tj.alimov.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tj.alimov.productservice.model.brand.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Brand c WHERE c.name = :name")
    boolean existsByName(String name);
}
