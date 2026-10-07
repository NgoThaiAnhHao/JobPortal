package jobportal.infrastructure.seeder;

import jobportal.infrastructure.persistence.companies.JpaCompanyRepository;
import jobportal.infrastructure.persistence.job_subcategories.JpaJobSubcategoryRepository;
import jobportal.infrastructure.persistence.jobs.JpaJobRepository;
import jobportal.infrastructure.persistence.users.JpaUserRepository;
import jobportal.infrastructure.seeder.constants.SeedJobConstants;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class JobSeeder {

    private final JpaJobRepository jpaJobRepository;
    private final JpaCompanyRepository jpaCompanyRepository;
    private final JpaJobSubcategoryRepository jpaJobSubcategoryRepository;
    private final JpaUserRepository jpaUserRepository;

    public JobSeeder(JpaJobRepository jpaJobRepository, JpaCompanyRepository jpaCompanyRepository, JpaJobSubcategoryRepository jpaJobSubcategoryRepository, JpaUserRepository jpaUserRepository) {
        this.jpaJobRepository = jpaJobRepository;
        this.jpaCompanyRepository = jpaCompanyRepository;
        this.jpaJobSubcategoryRepository = jpaJobSubcategoryRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    public void seed() {
        if (jpaJobRepository.count() > 0) {
            return;
        }

        // Create jobs and save to db
        jpaJobRepository.saveAll(
            List.of(
                    SeedJobConstants.JAVA_BACKEND_DEVELOPER,
                    SeedJobConstants.FRONTEND_DEVELOPER,
                    SeedJobConstants.DEVOPS_ENGINEER_INTERN,
                    SeedJobConstants.DIGITAL_MARKETING_SPECIALIST,
                    SeedJobConstants.UI_UX_DESIGNER
            )
        );
    }
}
