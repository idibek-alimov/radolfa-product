package tj.alimov.productservice.module;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@SequenceGenerator(
        name = "product_variant_generator",
        sequenceName = "seq_product_variant_generator"
)
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_variant_generator")
    private Long id;

    @Column(unique = true)
    private String skuCode;
    private BigDecimal price;
    private BigDecimal discount;

    private Long sellerId;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ColorOption color;

    @ManyToOne
    private SizeOption size;


}
