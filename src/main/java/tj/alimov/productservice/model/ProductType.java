package tj.alimov.productservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(
        name = "product_type_generator",
        sequenceName = "seq_product_type_generator"
)
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_generator")
    private Long id;
    private String name;  // Electronic , Book, Clothing

    public ProductType(String name){
        this.name = name;
    }
}
