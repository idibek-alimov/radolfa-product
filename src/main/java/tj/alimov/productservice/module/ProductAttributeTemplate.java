package tj.alimov.productservice.module;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(
        name = "product_attribute_template_generator",
        sequenceName = "seq_product_attribute_template_generator"
)
public class ProductAttributeTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_attribute_template_generator")
    private Long id;

    private String name;
    private boolean required;

    @Enumerated(EnumType.STRING)
    private AttributeDataType type;

    @ManyToOne
    private ProductType productType;
}
