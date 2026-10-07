package jobportal.application.mapper;

import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.domain.entity.JobSeekerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobSeekerProfileMapper {
    JobSeekerProfileResponse toResponse(JobSeekerProfile jobSeekerProfile);
}
