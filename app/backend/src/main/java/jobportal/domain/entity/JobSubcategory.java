package jobportal.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "job_subcategories",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_job_subcategory_name_slug",
                        columnNames = {"name", "slug"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobSubcategoryId;

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

    @ManyToOne
    @JoinColumn(name = "job_category_id", nullable = false)
    private JobCategory jobCategory;

    @PrePersist
    public void onCreate() {
        this.isActive = true;
    }

    public JobSubcategory(JobCategory jobCategory, String name, String slug) {
        this.jobCategory = jobCategory;
        this.name = name;
        this.slug = slug;
    }
}
