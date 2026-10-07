package jobportal.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin_profile")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminProfile {

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

    private String profileImage;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public AdminProfile(User user, String firstName, String lastName, String phone) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}