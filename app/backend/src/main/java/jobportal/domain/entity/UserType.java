package jobportal.domain.entity;

import jakarta.persistence.*;
import jobportal.domain.enums.UserTypeEnum;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "user_types")
public class UserType {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userTypeId;

    @Enumerated(EnumType.STRING)
    private UserTypeEnum userTypeEnum;

    public UserType(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
    }
}
