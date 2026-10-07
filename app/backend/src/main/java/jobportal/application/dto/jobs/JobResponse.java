package jobportal.application.dto.jobs;

import jobportal.domain.enums.EmploymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {

    private Long jobId;

    private String title;

    private EmploymentTypeEnum employmentType;

    private boolean remote;

    private BigDecimal salaryMin;

    private BigDecimal salaryMax;

    private String city;

    private LocalDateTime createdAt;

    private String companyName;

    private String companyLogo;

}
