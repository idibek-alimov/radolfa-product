package tj.alimov.productservice.model.size;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( indexes = {@Index(name = "idx_size_option_slug", columnList = "slug")},
        uniqueConstraints = {
            @UniqueConstraint(name = "unique_size",
                              columnNames = {"value", "fitType", "sizeType", "region"})
        })
@SequenceGenerator(name = "size_option_generator", sequenceName = "seq_size_option_generator")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SizeOption {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "size_option_generator")
    private Long id;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String value;
    @Column(nullable = false)
    private Integer ordering;
    @ManyToOne(fetch = FetchType.LAZY)
    private FitType fitType;
    @ManyToOne(fetch = FetchType.LAZY)
    private SizeType sizeType;
    @Column(nullable = false)
    private String region;
}
