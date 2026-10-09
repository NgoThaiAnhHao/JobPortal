package jobportal.infrastructure.persistence.jobs;

import jobportal.domain.entity.Job;
import jobportal.domain.enums.EmploymentTypeEnum;
import jobportal.domain.enums.JobStatusEnum;
import jobportal.domain.enums.SortJobsEnum;
import jobportal.domain.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public class JobRepositoryImpl implements JobRepository {

    private final JpaJobRepository jpaJobRepository;

    public JobRepositoryImpl(JpaJobRepository jpaJobRepository) {
        this.jpaJobRepository = jpaJobRepository;
    }

    @Override
    public Page<Job> searchAndGetAllJobs(
            String searchKeyword,
            Long categoryId,
            Long subcategoryId,
            EmploymentTypeEnum employmentType,
            Boolean remote,
            BigDecimal minSalary,
            BigDecimal maxSalary,
            SortJobsEnum sort,
            Pageable pageable) {

        if (searchKeyword != null ) {
            searchKeyword = searchKeyword.trim();

            if (searchKeyword.isEmpty()) {
                searchKeyword = null;
            }
        }

        // Sort
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                toSort(sort)
        );

        return jpaJobRepository.searchAndGetAllJobs(
                searchKeyword,
                categoryId,
                subcategoryId,
                employmentType,
                remote,
                minSalary,
                maxSalary,
                sort,
                JobStatusEnum.PUBLISHED,
                sortedPageable
        );
    }

    private Sort toSort(SortJobsEnum sort) {
        if (sort == null) {
            return Sort.unsorted();
        }

        return switch (sort) {
            case NEWEST ->
                    Sort.by(Sort.Direction.DESC, "createdAt");

            case SALARY_MIN_ASC ->
                    Sort.by(Sort.Direction.ASC, "salaryMin");

            case SALARY_MIN_DESC ->
                    Sort.by(Sort.Direction.DESC, "salaryMin");

            case SALARY_MAX_ASC ->
                    Sort.by(Sort.Direction.ASC, "salaryMax");

            case SALARY_MAX_DESC ->
                    Sort.by(Sort.Direction.DESC, "salaryMax");
        };
    }
}
