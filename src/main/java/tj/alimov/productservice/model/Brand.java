package tj.alimov.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@SequenceGenerator(
        name = "brand_generator",
        sequenceName = "seq_brand_generator"
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    private Long id;

    private String name;
    private String description;

    private String imgUrl;
}
