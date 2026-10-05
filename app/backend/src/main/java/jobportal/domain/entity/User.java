package jobportal.domain.entity;

import jakarta.persistence.*;
import jobportal.domain.enums.ProviderEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProviderEnum provider;

    private String providerId;

    @Column(nullable = false)
    private boolean enabled;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime emailVerifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_type_id", nullable = false)
    private UserType userType;

    public User(String email, String password, ProviderEnum provider, boolean enabled, UserType userType, LocalDateTime emailVerifiedAt) {
        this.email = email;
        this.password = password;
        this.provider = provider;
        this.enabled = enabled;
        this.userType = userType;
        this.emailVerifiedAt = emailVerifiedAt;
    }
}