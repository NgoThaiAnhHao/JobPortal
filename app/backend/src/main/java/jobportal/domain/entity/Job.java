package jobportal.domain.entity;

import jakarta.persistence.*;
import jobportal.domain.enums.EmploymentTypeEnum;
import jobportal.domain.enums.JobStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmploymentTypeEnum employmentType;

    @Column(nullable = false)
    private boolean remote;

    private BigDecimal salaryMin;

    private BigDecimal salaryMax;

    private String city;

    private String country;

    private LocalDateTime deadlineAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatusEnum status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "posted_by_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_subcategory_id", nullable = false)
    private JobSubcategory jobSubcategory;
}
