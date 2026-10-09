package jobportal.domain.repository;

import jobportal.domain.entity.Job;
import jobportal.domain.enums.EmploymentTypeEnum;
import jobportal.domain.enums.SortJobsEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface JobRepository {
    Page<Job> searchAndGetAllJobs(
            String searchKeyword,
            Long categoryId,
            Long subcategoryId,
            EmploymentTypeEnum employmentType,
            Boolean remote,
            BigDecimal minSalary,
            BigDecimal maxSalary,
            SortJobsEnum sort,
            Pageable pageable);
}
