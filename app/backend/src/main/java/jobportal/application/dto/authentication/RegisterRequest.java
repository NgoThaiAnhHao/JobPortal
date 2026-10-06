package jobportal.application.dto.authentication;

import jakarta.validation.constraints.*;
import jobportal.domain.enums.ProviderEnum;
import jobportal.domain.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    @Size(max = 254, message = "Email must not exceed 254 characters")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)\\S{8,64}$",
            message = "Password must be 8-64 characters, " +
                    "at least one uppercase letter, " +
                    "one lowercase letter, " +
                    "and one digit"
    )
    private String password;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    private String confirmPassword;

    private UserTypeEnum userTypeEnum;

    @NotNull(message = "Provider is mandatory")
    private ProviderEnum provider;

    public void setEmail(String email) {
        this.email = email == null ? null : email.toLowerCase().trim();
    }
}
