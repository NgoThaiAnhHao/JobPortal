package jobportal.infrastructure.seeder.constants;

import jobportal.domain.entity.AdminProfile;
import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.RecruiterProfile;
import jobportal.domain.enums.EmploymentTypeEnum;

public final class SeedProfileConstants {

    public static final AdminProfile ADMIN_PROFILE = new AdminProfile(
            SeedUserConstants.ADMIN,
    "Acacia",
            "Yedda",
            "(+1) 202 555 0198"
    );

    public static final RecruiterProfile RECRUITER_PROFILE = new RecruiterProfile(
            SeedUserConstants.RECRUITER,
            "Edana",
            "Sarah",
            "(+1) 310 444 1122",
            "Los Angeles",
            "California",
            "HR Manager",
            "Human Resource"
    );

    public static final JobSeekerProfile JOB_SEEKER_PROFILE = new JobSeekerProfile(
            SeedUserConstants.JOB_SEEKER,
            "Farah",
            "Mary",
            "(+1) 718 999 4582",
            "New York",
            "Brooklyn/Queens",
            EmploymentTypeEnum.FULL_TIME
    );

    public SeedProfileConstants() {
    }
}
