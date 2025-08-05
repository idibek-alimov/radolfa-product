package tj.alimov.productservice.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.dto.product.ProductDto;
import tj.alimov.productservice.model.product.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.slug = :slug")
    Optional<Product> findBySlug(@Param("slug") String slug);
    @Query("SELECT CASE WHEN COUNT(p)>0 THEN true ELSE false END FROM Product p WHERE p.slug = :slug")
    boolean existsBySlug(@Param("slug") String slug);

    @Query("SELECT NEW tj.alimov.productservice.dto.product.ProductDto(p.slug, c.slug, b.slug, pt.slug, p.name, p.description) " +
            "FROM Product p " +
            "JOIN p.category c " +
            "JOIN p.brand b " +
            "JOIN p.productType pt " +
            "WHERE p.slug = :slug")
    Optional<ProductDto> getProductDto(@Param("slug")String slug);

    @Query(value = "SELECT NEW tj.alimov.productservice.dto.product.ProductDto(p.slug, c.slug, b.slug, pt.slug, p.name, p.description) " +
            "FROM Product p " +
            "JOIN p.category c " +
            "JOIN p.brand b " +
            "JOIN p.productType pt ",
            countQuery = "SELECT COUNT(p) FROM Product p")
    Page<ProductDto> getAll(Pageable pageable);
}
