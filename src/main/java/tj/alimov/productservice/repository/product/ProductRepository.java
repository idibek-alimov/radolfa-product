package tj.alimov.productservice.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import tj.alimov.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
