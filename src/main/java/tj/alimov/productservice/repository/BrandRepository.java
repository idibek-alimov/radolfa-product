package tj.alimov.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tj.alimov.productservice.module.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);
}
