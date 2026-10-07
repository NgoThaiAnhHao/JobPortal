package jobportal.infrastructure.persistence.jobs;

import jobportal.domain.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJobRepository extends JpaRepository<Job, Long> {
}
