package jobportal.application.use_cases.jobs;

import jobportal.application.dto.jobs.JobResponse;
import jobportal.application.mapper.JobMapper;
import jobportal.domain.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllJobsUseCase {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public GetAllJobsUseCase(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public List<JobResponse> execute() {

        return jobRepository
                .findAll()
                .stream()
                .map(jobMapper::toResponse)
                .toList();
    }
}
