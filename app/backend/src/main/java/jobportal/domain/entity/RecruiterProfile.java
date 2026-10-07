package jobportal.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "recruiter_profile")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterProfile {

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

    private String companyPosition;

    private String department;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public RecruiterProfile(User user, String firstName, String lastName, String phone, String city, String country, String companyPosition, String department) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.companyPosition = companyPosition;
        this.department = department;
    }
}