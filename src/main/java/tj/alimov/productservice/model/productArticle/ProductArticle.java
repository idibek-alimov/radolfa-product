package tj.alimov.productservice.model.productArticle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tj.alimov.productservice.model.Auditable;
import tj.alimov.productservice.model.color.ColorOption;
import tj.alimov.productservice.model.product.Product;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(name = "idx_product_article_slug", columnList="slug"),
                  @Index(name = "idx_product_article_identifier", columnList="productBaseIdentifier")})
@Data
@SequenceGenerators(value = {
        @SequenceGenerator(name = "product_article_generator",
                           sequenceName = "seq_product_article_generator"),
        @SequenceGenerator(name = "product_article_identifier_generator",
                           sequenceName = "seq_product_article_identifier_generator",
                            initialValue = 10000000)})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductArticle extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_article_generator")
    private Long id;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false, unique = true)
    private BigInteger productBaseIdentifier;

    @Column(nullable = false)
    private Long sellerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_option_id")
    private ColorOption colorOption;

    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany
    private List<ProductPriceHistory> productPriceHistories = new ArrayList<>();

    @OneToMany
    private List<ProductArticleImage> images = new ArrayList<>();

    @PrePersist
    private void activate(){
        active = true;
    }

    public void addProductPriceHistory(ProductPriceHistory price){
        if(productPriceHistories == null){
            productPriceHistories = new ArrayList<>();
        }
        productPriceHistories.add(price);
    }
}
