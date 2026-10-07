package jobportal.application.dto.profiles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    private long userId;

    private String firstName;

    private String lastName;

    private String phone;

    private String profileImage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
