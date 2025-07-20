package tj.alimov.productservice.model;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(
        name = "color_generator",
        sequenceName = "seq_color_generator"
)
public class ColorOption {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "color_generator")
    private Long id;
    private String name;
    private String hexCode;
}
