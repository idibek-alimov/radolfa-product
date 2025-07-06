package tj.alimov.productservice.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import tj.alimov.productservice.module.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    boolean existsByName(String name);
}
