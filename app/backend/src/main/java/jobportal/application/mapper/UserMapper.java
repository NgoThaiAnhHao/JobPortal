package jobportal.application.mapper;

import jobportal.application.dto.authentication.RegisterRequest;
import jobportal.application.dto.user.UserResponse;
import jobportal.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);

    User toEntity(RegisterRequest registerRequest);
}
