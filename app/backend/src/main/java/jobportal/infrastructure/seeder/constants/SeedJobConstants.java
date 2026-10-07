package jobportal.infrastructure.seeder.constants;

import jobportal.domain.entity.Job;
import jobportal.domain.enums.EmploymentTypeEnum;
import jobportal.domain.enums.JobStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class SeedJobConstants {

    public static final Job JAVA_BACKEND_DEVELOPER = new Job(
            "Java Backend Developer",
            "We are looking for a Java Backend Developer to design, develop, and maintain scalable RESTful APIs and backend services using Java and Spring Boot.",
            EmploymentTypeEnum.FULL_TIME,
            false,
            BigDecimal.valueOf(1500.00),
            BigDecimal.valueOf(2500.00),
            "Ho Chi Minh City",
            "Vietnam",
            LocalDateTime.now().plusDays(10),
            JobStatusEnum.PUBLISHED,
            SeedCompanyConstants.FPT_SOFTWARE,
            SeedUserConstants.RECRUITER,
            SeedJobSubcategoryConstants.BACKEND_DEVELOPMENT
    );

    public static final Job FRONTEND_DEVELOPER = new Job(
            "Frontend Developer",
            "Join our frontend development team to build responsive and user-friendly web applications using modern frontend technologies and best practices.",
            EmploymentTypeEnum.FULL_TIME,
            true,
            BigDecimal.valueOf(1200.00),
            BigDecimal.valueOf(2200.00),
            "Ho Chi Minh City",
            "Vietnam",
            LocalDateTime.now().plusDays(18),
            JobStatusEnum.PUBLISHED,
            SeedCompanyConstants.VNG,
            SeedUserConstants.RECRUITER,
            SeedJobSubcategoryConstants.FRONTEND_DEVELOPMENT
    );

    public static final Job DEVOPS_ENGINEER_INTERN = new Job(
            "DevOps Engineer Intern",
            "We are looking for a DevOps Intern to support CI/CD pipelines, Docker-based deployments, cloud infrastructure, and system monitoring.",
            EmploymentTypeEnum.INTERNSHIP,
            false,
            BigDecimal.valueOf(400.00),
            BigDecimal.valueOf(700.00),
            "Ho Chi Minh City",
            "Vietnam",
            LocalDateTime.now().plusDays(28),
            JobStatusEnum.PUBLISHED,
            SeedCompanyConstants.KMS,
            SeedUserConstants.RECRUITER,
            SeedJobSubcategoryConstants.DEV_OPS
    );

    public static final Job DIGITAL_MARKETING_SPECIALIST = new Job(
            "Digital Marketing Specialist",
            "We are seeking a Digital Marketing Specialist to plan and execute digital campaigns, analyze marketing performance, and improve online brand awareness.",
            EmploymentTypeEnum.FULL_TIME,
            true,
            BigDecimal.valueOf(1000.00),
            BigDecimal.valueOf(1800.00),
            "Ho Chi Minh City",
            "Vietnam",
            LocalDateTime.now().plusDays(21),
            JobStatusEnum.PUBLISHED,
            SeedCompanyConstants.NASH_TECH,
            SeedUserConstants.RECRUITER,
            SeedJobSubcategoryConstants.DIGITAL_MARKETING
    );

    public static final Job UI_UX_DESIGNER = new Job(
            "UI/UX Designer",
            "We are looking for a creative UI/UX Designer to design intuitive user experiences, wireframes, prototypes, and modern interfaces for web and mobile applications.",
            EmploymentTypeEnum.PART_TIME,
            true,
            BigDecimal.valueOf(700.00),
            BigDecimal.valueOf(1200.00),
            "Ha Noi",
            "Vietnam",
            LocalDateTime.now().plusDays(11),
            JobStatusEnum.DRAFT,
            SeedCompanyConstants.ABC_TECHNOLOGY,
            SeedUserConstants.RECRUITER,
            SeedJobSubcategoryConstants.UI_UX_DESIGN
    );

    public SeedJobConstants() {
    }
}
