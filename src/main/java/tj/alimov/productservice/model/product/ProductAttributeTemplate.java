<<<<<<<< HEAD:src/main/java/tj/alimov/productservice/model/ProductAttributeTemplate.java
package tj.alimov.productservice.model;
========
package tj.alimov.productservice.model.product;
>>>>>>>> master:src/main/java/tj/alimov/productservice/model/product/ProductAttributeTemplate.java

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tj.alimov.productservice.model.AttributeDataType;
import tj.alimov.productservice.model.Auditable;

@Entity
@Table(indexes = {@Index(name = "idx_product_attribute_template_slug", columnList = "slug")})
@Data
@SequenceGenerator(
        name = "product_attribute_template_generator",
        sequenceName = "seq_product_attribute_template_generator"
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductAttributeTemplate extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_attribute_template_generator")
    private Long id;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private boolean required;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttributeDataType type;

    @ManyToOne
    private ProductType productType;
}
