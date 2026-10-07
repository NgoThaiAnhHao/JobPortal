package jobportal.infrastructure.seeder;

import jobportal.domain.entity.AdminProfile;
import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.RecruiterProfile;
import jobportal.domain.entity.User;
import jobportal.domain.enums.EmploymentTypeEnum;
import jobportal.domain.exception.common.authentication.UserNotFoundException;
import jobportal.infrastructure.persistence.profiles.JpaAdminProfileRepository;
import jobportal.infrastructure.persistence.profiles.JpaJobSeekerProfileRepository;
import jobportal.infrastructure.persistence.profiles.JpaRecruiterProfileRepository;
import jobportal.infrastructure.persistence.users.JpaUserRepository;
import org.springframework.stereotype.Component;

@Component
public class ProfileSeeder {

    private final JpaAdminProfileRepository jpaAdminProfileRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaJobSeekerProfileRepository jpaJobSeekerProfileRepository;
    private final JpaRecruiterProfileRepository jpaRecruiterProfileRepository;

    public ProfileSeeder(JpaAdminProfileRepository jpaAdminProfileRepository, JpaUserRepository jpaUserRepository, JpaJobSeekerProfileRepository jpaJobSeekerProfileRepository, JpaRecruiterProfileRepository jpaRecruiterProfileRepository) {
        this.jpaAdminProfileRepository = jpaAdminProfileRepository;
        this.jpaUserRepository = jpaUserRepository;
        this.jpaJobSeekerProfileRepository = jpaJobSeekerProfileRepository;
        this.jpaRecruiterProfileRepository = jpaRecruiterProfileRepository;
    }

    public void seed() {
        if (jpaAdminProfileRepository.count() > 0) {
            return;
        }

        // Find admin user
        User adminUser = jpaUserRepository
                .findByEmail(SeedUserConstants.ADMIN_EMAIL)
                .orElseThrow(() ->
                    new UserNotFoundException("User admin not found.")
                );

        // Find recruiter user
        User recruiterUser = jpaUserRepository
                .findByEmail(SeedUserConstants.RECRUITER_EMAIL)
                .orElseThrow(() ->
                        new UserNotFoundException("User recruiter not found.")
                );

        // Find jobseeker user
        User jobSeekerUser = jpaUserRepository
                .findByEmail(SeedUserConstants.JOB_SEEKER_EMAIL)
                .orElseThrow(() ->
                        new UserNotFoundException("User job seeker not found.")
                );

        // Create profiles
        AdminProfile adminProfile = new AdminProfile(
            adminUser, "Acacia", "Yedda", "(+1) 202 555 0198"
        );

        JobSeekerProfile jobSeekerProfile = new JobSeekerProfile(
            jobSeekerUser, "Farah", "Mary", "(+1) 718 999 4582",
                "New York", "Brooklyn/Queens", EmploymentTypeEnum.FULL_TIME
        );

        RecruiterProfile recruiterProfile = new RecruiterProfile(
            recruiterUser, "Edana", "Sarah", "(+1) 310 444 1122", "Los Angeles",
                "California", "HR Manager", "Human Resource"
        );

        // Save profiles
        jpaAdminProfileRepository.save(adminProfile);
        jpaJobSeekerProfileRepository.save(jobSeekerProfile);
        jpaRecruiterProfileRepository.save(recruiterProfile);
    }
}
