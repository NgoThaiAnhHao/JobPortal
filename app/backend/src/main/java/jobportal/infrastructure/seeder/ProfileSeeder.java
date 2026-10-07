package jobportal.infrastructure.seeder;

import jobportal.infrastructure.persistence.profiles.JpaAdminProfileRepository;
import jobportal.infrastructure.persistence.profiles.JpaJobSeekerProfileRepository;
import jobportal.infrastructure.persistence.profiles.JpaRecruiterProfileRepository;
import jobportal.infrastructure.seeder.constants.SeedProfileConstants;
import org.springframework.stereotype.Component;

@Component
public class ProfileSeeder {

    private final JpaAdminProfileRepository jpaAdminProfileRepository;
    private final JpaJobSeekerProfileRepository jpaJobSeekerProfileRepository;
    private final JpaRecruiterProfileRepository jpaRecruiterProfileRepository;

    public ProfileSeeder(JpaAdminProfileRepository jpaAdminProfileRepository, JpaJobSeekerProfileRepository jpaJobSeekerProfileRepository, JpaRecruiterProfileRepository jpaRecruiterProfileRepository) {
        this.jpaAdminProfileRepository = jpaAdminProfileRepository;
        this.jpaJobSeekerProfileRepository = jpaJobSeekerProfileRepository;
        this.jpaRecruiterProfileRepository = jpaRecruiterProfileRepository;
    }

    public void seed() {
        if (jpaAdminProfileRepository.count() > 0
                || jpaRecruiterProfileRepository.count() > 0
                || jpaJobSeekerProfileRepository.count() > 0) {
            return;
        }

        // Save profiles
        jpaAdminProfileRepository.save(SeedProfileConstants.ADMIN_PROFILE);
        jpaJobSeekerProfileRepository.save(SeedProfileConstants.JOB_SEEKER_PROFILE);
        jpaRecruiterProfileRepository.save(SeedProfileConstants.RECRUITER_PROFILE);
    }
}
