package jobportal.application.dto.profiles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jobportal.domain.enums.EmploymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateJobSeekerProfileRequest {

    @Size(max = 50, message = "First name must not exceed 50 characters")
    @NotBlank(message = "First name must not at least 1 character")
    private String firstName;

    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @NotBlank(message = "Last name must not at least 1 character")
    private String lastName;

    @Pattern(
            regexp = "^\\+?\\d{8,15}$",
            message = "Phone number must contain 8–15 digits and may start with +"
    )
    private String phone;

    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @Size(max = 100, message = "City must not exceed 100 characters")
    private String country;

//    @URL(message = "Profile image must be a valid URL")
//    @Size(max = 500, message = "Profile image must not exceed 500 characters")
//    private String profileImage;
//
//    @URL(message = "Resume must be a valid URL")
//    @Size(max = 500, message = "Resume URL must not exceed 500 characters")
//    private String resumeUrl;

    private EmploymentTypeEnum employmentType;

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }
}
