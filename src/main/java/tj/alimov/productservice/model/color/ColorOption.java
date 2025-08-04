package tj.alimov.productservice.model.color;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {@Index(name = "idx_color_option_slug", columnList = "slug")})
@SequenceGenerator(
        name = "color_generator",
        sequenceName = "seq_color_generator"
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorOption {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "color_generator")
    private Long id;
    @Column(nullable = false, unique = true)
    private String slug;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String hexCode;
}
