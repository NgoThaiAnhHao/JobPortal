package jobportal.application.use_cases.jobs;

import jobportal.application.dto.jobs.JobResponse;
import jobportal.application.mapper.JobMapper;
import jobportal.domain.entity.Job;
import jobportal.domain.exception.common.ResourceNotFoundException;
import jobportal.domain.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class ViewJobDetails {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public ViewJobDetails(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public JobResponse execute(Long jobId) {
        Job jobFound = jobRepository.viewJobDetails(jobId);

        if (jobFound == null) {
            throw new ResourceNotFoundException("Job not found");
        }

        return jobMapper.toResponse(jobFound);
    }
}
