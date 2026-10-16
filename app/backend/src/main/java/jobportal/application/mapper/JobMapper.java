package jobportal.application.mapper;

import jobportal.application.dto.jobs.JobResponse;
import jobportal.domain.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobMapper {
    @Mapping(target = "companyName", source = "company.name")
    @Mapping(target = "companyLogo", source = "company.logo")
    JobResponse toResponse(Job job);
}
