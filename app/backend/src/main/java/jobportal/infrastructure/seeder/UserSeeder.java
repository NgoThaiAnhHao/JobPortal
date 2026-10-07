package jobportal.infrastructure.seeder;

import jobportal.domain.entity.User;
import jobportal.domain.entity.UserType;
import jobportal.domain.enums.ProviderEnum;
import jobportal.domain.enums.UserTypeEnum;
import jobportal.infrastructure.persistence.users.JpaUserRepository;
import jobportal.infrastructure.persistence.usertypes.JpaUserTypeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserSeeder {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserTypeRepository jpaUserTypeRepository;

    public UserSeeder(JpaUserRepository jpaUserRepository, JpaUserTypeRepository jpaUserTypeRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaUserTypeRepository = jpaUserTypeRepository;
    }

    //
    // Password for all accounts: @Test123
    public void seed() {
        if (jpaUserRepository.count() > 0) {
            return;
        }

        // Finding user types
        UserType admin = jpaUserTypeRepository.findByUserTypeEnum(UserTypeEnum.ADMIN);
        UserType recruiter = jpaUserTypeRepository.findByUserTypeEnum(UserTypeEnum.RECRUITER);
        UserType jobSeeker = jpaUserTypeRepository.findByUserTypeEnum(UserTypeEnum.JOB_SEEKER);

        jpaUserRepository.saveAll(
            List.of(
                // Admin
                new User(
                        SeedUserConstants.ADMIN_EMAIL,
                        "$2a$12$68NVxd//sMKyBwaKiqCh5OjDZoZX5HkUXukmwtUtUZS4i3tohPxrm",
                        ProviderEnum.LOCAL,
                        true,
                        admin,
                        LocalDateTime.now()
                ),

                // Recruiter
                new User(
                        SeedUserConstants.RECRUITER_EMAIL,
                        "$2a$12$68NVxd//sMKyBwaKiqCh5OjDZoZX5HkUXukmwtUtUZS4i3tohPxrm",
                        ProviderEnum.LOCAL,
                        true,
                        recruiter,
                        LocalDateTime.now()
                ),

                // Job Seeker
                new User(
                        SeedUserConstants.JOB_SEEKER_EMAIL,
                        "$2a$12$68NVxd//sMKyBwaKiqCh5OjDZoZX5HkUXukmwtUtUZS4i3tohPxrm",
                        ProviderEnum.LOCAL,
                        true,
                        jobSeeker,
                        LocalDateTime.now()
                )
            )
        );
    }
}
