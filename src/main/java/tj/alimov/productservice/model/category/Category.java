package tj.alimov.productservice.model.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(indexes = {@Index(name = "idx_category_slug", columnList = "slug")})
@Data
@SequenceGenerator(
        name = "category_generator",
        sequenceName = "seq_category_generator"
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    private Long id;
    private String name;
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;


    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> childCategories;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updateAt;

}
