package jobportal.application.dto.usertypes;

import jobportal.domain.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTypeResponse {
    private Integer userTypeId;

    private UserTypeEnum userTypeEnum;
}
