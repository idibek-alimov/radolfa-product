package tj.alimov.productservice.module;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@SequenceGenerator(
        name = "product_generator",
        sequenceName = "seq_product_generator"
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    private Long id;

    @ManyToOne
    private Category category;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @ManyToOne
    private ProductType productType;

    private Long sellerId;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductAttributeValue> attributeValues;
}
