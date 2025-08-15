package tj.alimov.productservice.repository.productArticle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tj.alimov.productservice.model.productArticle.ProductArticleImage;

import java.util.List;

public interface ProductArticleImageRepository extends JpaRepository<ProductArticleImage, Long>{

    @Query("SELECT pai FROM ProductArticleImage pai WHERE pai.productArticle.slug = :slug")
    List<ProductArticleImage> getByProductArticleSlug(@Param("slug")String slug);
}
