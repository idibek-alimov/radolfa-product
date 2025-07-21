package tj.alimov.productservice.model.brand;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@SequenceGenerator(name = "brand_generator", sequenceName = "seq_brand_generator")

public class BrandImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    private Long id;

    private String slug;
    private String baseUrl;
    private String thumbnailUrl;
    private Integer position;
    @ManyToOne
    private Brand brand;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
}
