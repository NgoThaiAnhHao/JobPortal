package jobportal.application.mapper;

import jobportal.application.dto.profiles.RecruiterProfileResponse;
import jobportal.domain.entity.RecruiterProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecruiterProfileMapper {
    RecruiterProfileResponse toResponse(RecruiterProfile recruiterProfile);
}
