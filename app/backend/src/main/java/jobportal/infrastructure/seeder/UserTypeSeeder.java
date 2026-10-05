package jobportal.infrastructure.seeder;

import jobportal.domain.entity.UserType;
import jobportal.domain.enums.UserTypeEnum;
import jobportal.infrastructure.persistence.usertypes.JpaUserTypeRepository;
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
                        new UserType(UserTypeEnum.ADMIN),
                        new UserType(UserTypeEnum.RECRUITER),
                        new UserType(UserTypeEnum.JOB_SEEKER)
                )
        );
    }
}
