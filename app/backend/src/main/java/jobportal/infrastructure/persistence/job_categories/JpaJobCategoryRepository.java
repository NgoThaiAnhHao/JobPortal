package jobportal.infrastructure.persistence.job_categories;

import jobportal.domain.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaJobCategoryRepository extends JpaRepository<JobCategory, Long> {
    Optional<JobCategory> findByNameAndSlug(String name, String slug);
}
