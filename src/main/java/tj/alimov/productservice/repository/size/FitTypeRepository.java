package tj.alimov.productservice.repository.size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.model.size.FitType;

import java.util.Optional;

public interface FitTypeRepository extends JpaRepository<FitType, Long> {
    @Query("SELECT CASE WHEN COUNT(ft)>0 THEN true ELSE false END FROM FitType ft WHERE ft.slug = :slug")
    boolean existsBySlug(@Param("slug") String slug);

    @Query("SELECT CASE WHEN COUNT(ft)>0 THEN true ELSE false END FROM FitType ft WHERE ft.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT ft FROM FitType ft WHERE ft.slug = :slug")
    Optional<FitType> findBySlug(@Param("slug") String slug);
}
