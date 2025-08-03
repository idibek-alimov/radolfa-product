package tj.alimov.productservice.repository.size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.model.size.SizeType;

import java.util.Optional;

public interface SizeTypeRepository extends JpaRepository<SizeType, Long> {
    @Query("SELECT CASE WHEN COUNT(st)>0 THEN true ELSE false END FROM SizeType st WHERE st.name = :name")
    boolean existsByName(@Param("name") String name);
    @Query("SELECT CASE WHEN COUNT(st)>0 THEN true ELSE false END FROM SizeType st WHERE st.slug = :slug")
    boolean existsBySlug(@Param("slug") String slug);
    @Query("SELECT st FROM SizeType st WHERE st.slug = :slug")
    Optional<SizeType> findBySlug(@Param("slug") String slug);

}
