package jobportal.infrastructure.seeder;

import jobportal.infrastructure.persistence.usertypes.JpaUserTypeRepository;
import jobportal.infrastructure.seeder.constants.SeedUserTypeConstants;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserTypeSeeder {

    private final JpaUserTypeRepository jpaUserTypeRepository;

    public UserTypeSeeder(JpaUserTypeRepository jpaUserTypeRepository) {
        this.jpaUserTypeRepository = jpaUserTypeRepository;
    }

    public void seed() {
        if (jpaUserTypeRepository.count() > 0) {
            return;
        }

        jpaUserTypeRepository.saveAll(
                List.of(
                    SeedUserTypeConstants.ADMIN_TYPE,
                    SeedUserTypeConstants.RECRUITER_TYPE,
                    SeedUserTypeConstants.JOB_SEEKER_TYPE
                )
        );
    }
}
