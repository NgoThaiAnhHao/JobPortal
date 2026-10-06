package jobportal.domain.repository;

import jobportal.domain.entity.UserType;
import jobportal.domain.enums.UserTypeEnum;

import java.util.List;
import java.util.Optional;

public interface UserTypeRepository {
    List<UserType> findAll();

    UserType findByUserTypeEnum(UserTypeEnum userTypeEnum);
}
