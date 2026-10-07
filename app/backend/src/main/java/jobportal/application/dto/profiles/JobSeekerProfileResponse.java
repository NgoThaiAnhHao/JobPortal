package jobportal.application.dto.profiles;

import jobportal.domain.enums.EmploymentTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class JobSeekerProfileResponse extends ProfileResponse {

    private String city;

    private String country;

    private String resumeUrl;

    private EmploymentTypeEnum employmentType;

    public JobSeekerProfileResponse(long userId, String firstName, String lastName, String phone, String profileImage, LocalDateTime createdAt, LocalDateTime updatedAt, String city, String country, String resumeUrl, EmploymentTypeEnum employmentType) {
        super(userId, firstName, lastName, phone, profileImage, createdAt, updatedAt);
        this.city = city;
        this.country = country;
        this.resumeUrl = resumeUrl;
        this.employmentType = employmentType;
    }
}
