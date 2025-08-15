package tj.alimov.productservice.model.productArticle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import tj.alimov.productservice.model.Auditable;
import tj.alimov.productservice.model.size.SizeOption;

@Entity
@Table(indexes = {@Index(name = "idx_product_variant_slug", columnList = "slug")})
@Data
@SequenceGenerator(
        name = "product_variant_generator",
        sequenceName = "seq_product_variant_generator"
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_variant_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_article_id")
    private ProductArticle productArticle;

    @Column(unique = true, nullable = false)
    private String skuCode;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    private SizeOption sizeOption;
}
