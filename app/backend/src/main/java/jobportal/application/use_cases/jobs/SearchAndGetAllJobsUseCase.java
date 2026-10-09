package jobportal.application.use_cases.jobs;

import jobportal.application.dto.jobs.JobResponse;
import jobportal.application.mapper.JobMapper;
import jobportal.domain.enums.EmploymentTypeEnum;
import jobportal.domain.enums.SortJobsEnum;
import jobportal.domain.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Service
public class SearchAndGetAllJobsUseCase {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public SearchAndGetAllJobsUseCase(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    /**
     * TODO:
     * - Xem danh sách job đang PUBLISHED, còn hạn và không bị xóa (x)
     * - Tìm kiếm theo từ khóa trong title, company name và location (x)
     * - Lọc theo category, subcategory, job type, remote, location và khoảng lương
     * - Sắp xếp theo newest, salary ascending hoặc salary descending
     * - Chỉ job PUBLISHED, còn deadline, chưa soft delete và thuộc company active mới hiển thị công khai/được apply.
     * -
     *
     *
     * @param pageable
     * @return
     */
    public Page<JobResponse> execute(
            String searchKeyword,
            Long categoryId,
            Long subcategoryId,
            EmploymentTypeEnum employmentType,
            Boolean remote,
            BigDecimal minSalary,
            BigDecimal maxSalary,
            SortJobsEnum sort,
            Pageable pageable) {

        return jobRepository
                .searchAndGetAllJobs(
                        searchKeyword,
                        categoryId,
                        subcategoryId,
                        employmentType,
                        remote,
                        minSalary,
                        maxSalary,
                        sort,
                        pageable)
                .map(jobMapper::toResponse);
    }
}
