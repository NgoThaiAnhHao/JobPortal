package jobportal.infrastructure.persistence.jobs;

import jobportal.domain.entity.Job;
import jobportal.domain.enums.EmploymentTypeEnum;
import jobportal.domain.enums.JobStatusEnum;
import jobportal.domain.enums.SortJobsEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface JpaJobRepository extends JpaRepository<Job, Long> {

    @Query("""
        SELECT J FROM Job J
            JOIN J.company C
            JOIN J.user U
            JOIN J.jobSubcategory S
            JOIN S.jobCategory CAT
            WHERE
                J.status = :status
                
                /* Non deleted */
                AND J.deletedAt IS NULL
                AND C.deletedAt IS NULL
                
                /* Non expired */
                AND (
                    J.deadlineAt IS NULL
                    OR J.deadlineAt > CURRENT_TIMESTAMP
                )
                
                /* Verified Or Active */
                AND C.isVerified = TRUE
                AND CAT.isActive = TRUE
                AND S.isActive = TRUE
                
                /* Search */
                AND (
                    :searchKeyword IS NULL
                    OR J.title LIKE %:searchKeyword%
                    OR C.name LIKE %:searchKeyword%
                    OR C.country LIKE %:searchKeyword%
                    OR C.city LIKE %:searchKeyword%
                )
                
                 /* Category filter */
                AND (
                    :categoryId IS NULL
                    OR CAT.jobCategoryId = :categoryId
                )
                
                /* Sub Category filter */
                AND (
                    :subcategoryId IS NULL
                    OR S.jobSubcategoryId = :subcategoryId
                )
                
                /* Job Type filter */
                AND (
                    :employmentType IS NULL
                    OR J.employmentType = :employmentType
                )
                
                /* Remote filter */
                AND (
                    :remote IS NULL
                    OR J.remote = :remote
                )
                
                /* Min salary filter */
                AND (
                    :minSalary IS NULL
                    OR J.salaryMin >= :minSalary
                )
                
                /* Max salary filter */
                AND (
                    :maxSalary IS NULL
                    OR J.salaryMax <= :maxSalary
                )
""")
    Page<Job> searchAndGetAllJobs(
            String searchKeyword,
            Long categoryId,
            Long subcategoryId,
            EmploymentTypeEnum employmentType,
            Boolean remote,
            BigDecimal minSalary,
            BigDecimal maxSalary,
            SortJobsEnum sort,
            JobStatusEnum status,
            Pageable pageable
    );
}
