package tj.alimov.productservice.model.product;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(
        name = "product_variant_image_generator",
        sequenceName = "seq_product_variant_image_generator"
)
public class ProductVariantImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_variant_image_generator")
    private Long id;

    private String imageUrl;
    private Integer position;

    @ManyToOne
    private ProductVariant productVariant;
}
