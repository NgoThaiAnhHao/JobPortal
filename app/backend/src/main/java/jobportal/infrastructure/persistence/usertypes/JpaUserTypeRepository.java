package jobportal.infrastructure.persistence.usertypes;

import jobportal.domain.entity.UserType;
import jobportal.domain.enums.UserTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserTypeRepository extends JpaRepository<UserType, Integer> {

    UserType findByUserTypeEnum(UserTypeEnum userTypeEnum);
}
