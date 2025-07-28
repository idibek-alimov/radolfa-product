package tj.alimov.productservice.model.brand;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tj.alimov.productservice.model.BaseImage;

import java.time.Instant;

@Entity
@Data
@SequenceGenerator(name = "brand_generator", sequenceName = "seq_brand_generator")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandImage extends BaseImage{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    private Long id;
    @ManyToOne
    private Brand brand;

}
