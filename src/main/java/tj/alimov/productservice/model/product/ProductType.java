package tj.alimov.productservice.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tj.alimov.productservice.model.Auditable;

@Entity
@Table(indexes = {@Index(name = "idx_product_type_slug", columnList = "slug")})
@Data
@SequenceGenerator(
        name = "product_type_generator",
        sequenceName = "seq_product_type_generator"
)
@AllArgsConstructor
@NoArgsConstructor
public class ProductType extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_generator")
    private Long id;

    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String name;  // Electronic , Book, Clothing

    public ProductType(String name){
        this.name = name;
    }
}
