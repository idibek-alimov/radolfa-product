package tj.alimov.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.model.color.ColorOption;

import java.util.Optional;

public interface ColorOptionRepository extends JpaRepository<ColorOption, Long> {
    @Query("SELECT CASE WHEN COUNT(co)>0 THEN true ELSE false END FROM ColorOption co WHERE co.name = :name OR co.hexCode = :hexCode")
    boolean existsByNameOrHexCode(@Param("name")String name, @Param("hexCode")String hexCode);
    @Query("SELECT CASE WHEN COUNT(co)>0 THEN true ELSE false END FROM ColorOption co WHERE co.slug = :slug")
    boolean existsBySlug(@Param("slug")String slug);

    @Query("SELECT co FROM ColorOption co WHERE co.slug = :slug")
    Optional<ColorOption> findBySlug(@Param("slug")String slug);
}
