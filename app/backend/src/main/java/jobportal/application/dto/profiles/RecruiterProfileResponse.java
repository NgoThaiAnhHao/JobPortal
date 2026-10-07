package jobportal.application.dto.profiles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RecruiterProfileResponse extends ProfileResponse {
    private String city;

    private String country;

    private String companyPosition;

    private String department;

    public RecruiterProfileResponse(long userId, String firstName, String lastName, String phone, String profileImage, LocalDateTime createdAt, LocalDateTime updatedAt, String city, String country, String companyPosition, String department) {
        super(userId, firstName, lastName, phone, profileImage, createdAt, updatedAt);
        this.city = city;
        this.country = country;
        this.companyPosition = companyPosition;
        this.department = department;
    }
}
