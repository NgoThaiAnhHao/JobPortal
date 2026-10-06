package jobportal.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOtp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "otp_code", nullable = false, unique = true)
    private String otpCode;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void onCreate() {
        this.expiredAt = LocalDateTime.now().plusMinutes(15);
    }

    public VerificationOtp(String otpCode, User user) {
        this.otpCode = otpCode;
        this.user = user;
    }
}
