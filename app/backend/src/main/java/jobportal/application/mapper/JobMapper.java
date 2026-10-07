package jobportal.application.mapper;

import jobportal.application.dto.jobs.JobResponse;
import jobportal.domain.entity.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobResponse toResponse(Job job);
}
