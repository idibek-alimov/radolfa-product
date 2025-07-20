package tj.alimov.productservice.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tj.alimov.productservice.model.category.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c FROM Category c WHERE LOWER(c.slug) = LOWER(:slug)")
    Optional<Category> findBySlug(String slug);

    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM Category c WHERE c.name = :name")
    boolean existsByName(String name);

//    @Query(value = "DELETE FROM Category c WHERE c.slug = :slug")
//    void deleteBySlug(String slug);
}
