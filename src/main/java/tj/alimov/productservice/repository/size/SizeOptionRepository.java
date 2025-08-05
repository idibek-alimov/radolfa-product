package tj.alimov.productservice.repository.size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.dto.sizeOption.SizeOptionCreationRequest;
import tj.alimov.productservice.dto.sizeOption.SizeOptionDto;
import tj.alimov.productservice.model.size.SizeOption;

import java.util.List;
import java.util.Optional;

public interface SizeOptionRepository extends JpaRepository<SizeOption, Long> {
    @Query("SELECT CASE WHEN COUNT(so)>0 THEN true ELSE false END " +
            "FROM SizeOption so " +
            "JOIN so.fitType ft " +
            "JOIN so.sizeType st " +
            "WHERE so.value = :#{#request.value()} " +
            "AND so.region = :#{#request.region()} " +
            "AND ft.slug = :#{#request.fitTypeSlug()} " +
            "AND st.slug = :#{#request.sizeTypeSlug()}")
    boolean sizeOptionExists(@Param("request")SizeOptionCreationRequest request);

    @Query("SELECT CASE WHEN COUNT(so)>0 THEN true ELSE false END FROM SizeOption so WHERE so.slug = :slug")
    boolean existsBySlug(@Param("slug") String slug);

    @Query("SELECT so FROM SizeOption so WHERE so.slug = :slug")
    Optional<SizeOption> findBySlug(@Param("slug") String slug);

    @Query("SELECT NEW tj.alimov.productservice.dto.sizeOption.SizeOptionDto(so.slug, so.ordering, st.name, st.description, so.value, ft.name, so.region) " +
            "FROM SizeOption so " +
            "JOIN so.sizeType st " +
            "JOIN so.fitType ft " +
            "WHERE so.slug = :slug")
    Optional<SizeOptionDto> getDtoBySlug(@Param("slug") String slug);

    @Query(value = "SELECT NEW tj.alimov.productservice.dto.sizeOption.SizeOptionDto(so.slug, so.ordering, st.name, st.description, so.value, ft.name, so.region) " +
            "FROM SizeOption so " +
            "JOIN so.sizeType st " +
            "JOIN so.fitType ft " +
            "WHERE ft.slug = :slug",
            countQuery = "SELECT COUNT(so) FROM SizeOption so JOIN so.fitType ft WHERE ft.slug = :slug"
    )
    Page<SizeOptionDto> getDtoByFitTypeSlug(@Param("slug") String slug, Pageable pageable);

    @Query(value = "SELECT NEW tj.alimov.productservice.dto.sizeOption.SizeOptionDto(so.slug, so.ordering, st.name, st.description, so.value, ft.name, so.region) " +
            "FROM SizeOption so " +
            "JOIN so.sizeType st " +
            "JOIN so.fitType ft " +
            "WHERE st.slug = :slug",
            countQuery = "SELECT COUNT(so) FROM SizeOption so JOIN so.sizeType st WHERE st.slug = :slug"
    )
    Page<SizeOptionDto> getDtoBySizeTypeSlug(@Param("slug") String slug, Pageable pageable);

//    @Query("SELECT NEW tj.alimov.productservice.dto.sizeOption.SizeOptionDto(so.slug, so.order, st.name, st.description, so.value, ft.name, so.region) " +
//            "FROM SizeOption so " +
//            "JOIN so.sizeType st " +
//            "JOIN so.fitType ft " +
//            "WHERE  = :slug")

}
