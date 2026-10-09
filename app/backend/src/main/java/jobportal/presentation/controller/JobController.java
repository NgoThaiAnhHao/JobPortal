package jobportal.presentation.controller;

import jobportal.application.dto.jobs.JobResponse;
import jobportal.application.use_cases.jobs.SearchAndGetAllJobsUseCase;
import jobportal.domain.enums.EmploymentTypeEnum;
import jobportal.domain.enums.SortJobsEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final SearchAndGetAllJobsUseCase searchAndGetAllJobsUseCase;

    public JobController(SearchAndGetAllJobsUseCase searchAndGetAllJobsUseCase) {
        this.searchAndGetAllJobsUseCase = searchAndGetAllJobsUseCase;
    }

    @GetMapping
    public Page<JobResponse> searchAndGetAllJobs(
            @RequestParam(required = false) String searchKeyword,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long subcategoryId, // For subcategory filter
            @RequestParam(required = false) EmploymentTypeEnum employmentType, // For employment type filter
            @RequestParam(required = false) Boolean remote, // For remote filter
            @RequestParam(required = false) BigDecimal minSalary, // For min salary filter
            @RequestParam(required = false) BigDecimal maxSalary, // For max salary filter
            @RequestParam(required = false) SortJobsEnum sort,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return searchAndGetAllJobsUseCase.execute(
                searchKeyword,
                categoryId,
                subcategoryId,
                employmentType,
                remote,
                minSalary,
                maxSalary,
                sort,
                pageable);
    }
}
