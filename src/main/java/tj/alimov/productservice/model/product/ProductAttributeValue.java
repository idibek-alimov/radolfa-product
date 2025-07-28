package tj.alimov.productservice.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
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

    private String value;
    @ManyToOne
    private Product product;

    @ManyToOne
    private ProductAttributeTemplate template;
}
