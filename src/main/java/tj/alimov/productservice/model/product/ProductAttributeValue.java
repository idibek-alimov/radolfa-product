package tj.alimov.productservice.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {@Index(name = "idx_product_attribute_value_slug", columnList = "slug")},
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "uk_pav_fields",
                    columnNames = {"product", "template"}
            )
        })
@Data
@SequenceGenerator(
        name = "product_attribute_value",
        sequenceName = "seq_product_attribute_value"
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_attribute_value")
    private Long id;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String value;
    @ManyToOne
    private Product product;

    @ManyToOne
    private ProductAttributeTemplate template;
}
