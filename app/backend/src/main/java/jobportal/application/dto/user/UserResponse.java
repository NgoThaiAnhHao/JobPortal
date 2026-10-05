package jobportal.application.dto.user;

import jobportal.domain.entity.UserType;
import jobportal.domain.enums.ProviderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String email;

    private ProviderEnum provider;

    private String providerId;

    private boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime emailVerifiedAt;

    private UserType userType;

}
