<<<<<<<< HEAD:src/main/java/tj/alimov/productservice/model/ProductImage.java
package tj.alimov.productservice.model;
========
package tj.alimov.productservice.model.product;
>>>>>>>> master:src/main/java/tj/alimov/productservice/model/product/ProductImage.java

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(
        name = "product_image_generator",
        sequenceName = "seq_product_image_generator"
)
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_image_generator")
    private Long id;

    private String imageUrl;

    private Integer position;

    @ManyToOne
    private Product product;
}
