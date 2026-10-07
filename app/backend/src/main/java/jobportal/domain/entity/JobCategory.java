package jobportal.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "job_categories",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_job_category_name_slug",
                        columnNames = {"name", "slug"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobCategoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    private boolean isActive;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public JobCategory(String name, String slug) {
        this.slug = slug;
        this.name = name;
    }
}
