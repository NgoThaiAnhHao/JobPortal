package jobportal.application.mapper;

import jobportal.application.dto.usertypes.UserTypeResponse;
import jobportal.domain.entity.UserType;
import org.mapstruct.Mapper;

//
// Tạo implementation của Mapper và đăng ký nó thành một Spring Bean.
@Mapper(componentModel = "spring")
public interface UserTypeMapper {
    UserTypeResponse toResponse(UserType userType);
}
