package tj.alimov.productservice.model.productArticle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import tj.alimov.productservice.model.Auditable;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@Table(indexes = {@Index(name = "idx_product_price_history_product_article_active", columnList="product_article_id,active")})
@SequenceGenerator(name = "product_price_history_generator",
        sequenceName = "seq_product_price_history_generator")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceHistory extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_price_history_generator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_article_id")
    private ProductArticle productArticle;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private BigDecimal discountPercentage;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant discountData;

    @Column(nullable = false)
    private Boolean active;


    @PrePersist
    private void prePersist(){
        active = true;
    }
}
