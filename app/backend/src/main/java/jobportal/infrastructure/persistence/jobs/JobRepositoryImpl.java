package jobportal.infrastructure.persistence.jobs;

import jobportal.domain.entity.Job;
import jobportal.domain.repository.JobRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobRepositoryImpl implements JobRepository {

    private final JpaJobRepository jobRepository;

    public JobRepositoryImpl(JpaJobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }
}
