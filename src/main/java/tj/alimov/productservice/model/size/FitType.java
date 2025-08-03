package tj.alimov.productservice.model.size;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {@Index(name = "idx_fit_type_slug", columnList = "slug")})
@SequenceGenerator(name = "fit_type_generator", sequenceName = "seq_fit_type_generator")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FitType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fit_type_generator")
    private Long id;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false, unique = true)
    private String name;
}
