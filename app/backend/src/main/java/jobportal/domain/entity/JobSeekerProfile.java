package jobportal.domain.entity;

import jakarta.persistence.*;
import jobportal.domain.enums.EmploymentTypeEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_seeker_profile")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerProfile {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    private String firstName;

    private String lastName;

    private String phone;

    private String city;

    private String country;

    private String profileImage;

    private String resumeUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type")
    private EmploymentTypeEnum employmentType;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public JobSeekerProfile(User user, String firstName, String lastName, String phone, String city, String country, EmploymentTypeEnum employmentType) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.employmentType = employmentType;
    }
}