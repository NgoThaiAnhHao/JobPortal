package jobportal.infrastructure.seeder;

import jobportal.infrastructure.persistence.users.JpaUserRepository;
import jobportal.infrastructure.seeder.constants.SeedUserConstants;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserSeeder {

    private final JpaUserRepository jpaUserRepository;

    public UserSeeder(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    public void seed() {
        if (jpaUserRepository.count() > 0) {
            return;
        }

        jpaUserRepository.saveAll(
            List.of(
                    SeedUserConstants.ADMIN,
                    SeedUserConstants.RECRUITER,
                    SeedUserConstants.JOB_SEEKER
            )
        );
    }
}
