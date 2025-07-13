package tj.alimov.productservice.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.module.ProductType;

import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    boolean existsByName(String name);
    @Query("SELECT ptype from ProductType ptype WHERE ptype.name = :name")
    Optional<ProductType> findByName(@Param("name") String name);
}
