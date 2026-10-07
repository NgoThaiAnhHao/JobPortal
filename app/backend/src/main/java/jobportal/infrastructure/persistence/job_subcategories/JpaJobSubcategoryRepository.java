package jobportal.infrastructure.persistence.job_subcategories;

import jobportal.domain.entity.JobSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJobSubcategoryRepository extends JpaRepository<JobSubcategory, Long> {
}
