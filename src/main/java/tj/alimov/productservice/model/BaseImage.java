package tj.alimov.productservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Persistent;

@Data
@MappedSuperclass
public abstract class BaseImage extends Auditable{
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String baseUrl;
    @Column(nullable = false)
    private String thumbnailUrl;
    @Column(nullable = false)
    private Integer position;

    private Boolean active;

    @PrePersist
    private void prePersist(){
        active = true;
    }
}
