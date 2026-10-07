package jobportal.application.mapper;

import jobportal.application.dto.profiles.ProfileResponse;
import jobportal.domain.entity.AdminProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminProfileMapper {
    ProfileResponse toResponse(AdminProfile adminProfile);
}
