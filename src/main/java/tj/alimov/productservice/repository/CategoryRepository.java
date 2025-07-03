package tj.alimov.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tj.alimov.productservice.module.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    boolean existsBySlug(String slug);
    boolean existsById(Long id);
}
