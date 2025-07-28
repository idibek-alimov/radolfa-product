package tj.alimov.productservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@SequenceGenerator(
        name = "size_generator",
        sequenceName = "seq_size_generator"
)
public class SizeOption {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "size_generator")
    private Long id;

    private String name;
    private String region;
}
