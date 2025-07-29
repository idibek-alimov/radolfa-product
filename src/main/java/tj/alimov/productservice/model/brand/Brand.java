package tj.alimov.productservice.model.brand;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tj.alimov.productservice.model.Auditable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(name = "idx_brand_slug", columnList = "slug")})
@Data
@SequenceGenerator(
        name = "brand_generator",
        sequenceName = "seq_brand_generator"
)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    private Long id;
    @Column(unique = true)
    private String slug;
    @Column(unique = true)
    private String name;
    private String description;
    private String url;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    List<BrandImage> images = new ArrayList<>();
}
