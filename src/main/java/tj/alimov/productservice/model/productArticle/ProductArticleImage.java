package tj.alimov.productservice.model.productArticle;

import jakarta.persistence.*;
import lombok.Data;
import tj.alimov.productservice.model.BaseImage;

@Entity
@Table(indexes = {@Index(name="idx_product_variant_image_slug", columnList = "slug")})
@Data
@SequenceGenerator(
        name = "product_variant_image_generator",
        sequenceName = "seq_product_variant_image_generator"
)
public class ProductArticleImage extends BaseImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_variant_image_generator")
    private Long id;
    @ManyToOne
    private ProductArticle productArticle;
}
