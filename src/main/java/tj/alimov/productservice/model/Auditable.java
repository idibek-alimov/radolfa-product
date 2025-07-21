package tj.alimov.productservice.model;

import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@MappedSuperclass
public abstract class Auditable {
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updateAt;
}
