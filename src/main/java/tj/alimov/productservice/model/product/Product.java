package tj.alimov.productservice.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tj.alimov.productservice.model.Auditable;
import tj.alimov.productservice.model.brand.Brand;
import tj.alimov.productservice.model.category.Category;

import java.util.List;

@Entity
@Table(indexes = @Index(name = "idx_product_category_id", columnList = "category"))
@Data
@SequenceGenerator(
        name = "product_generator",
        sequenceName = "seq_product_generator"
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String slug;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long sellerId;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductType productType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductAttributeValue> attributeValues;
}
