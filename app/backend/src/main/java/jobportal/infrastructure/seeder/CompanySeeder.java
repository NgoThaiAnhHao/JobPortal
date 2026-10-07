package jobportal.infrastructure.seeder;

import jobportal.infrastructure.persistence.companies.JpaCompanyRepository;
import jobportal.infrastructure.seeder.constants.SeedCompanyConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanySeeder {

    private final JpaCompanyRepository jpaCompanyRepository;

    public CompanySeeder(JpaCompanyRepository jpaCompanyRepository) {
        this.jpaCompanyRepository = jpaCompanyRepository;
    }

    public void seed() {
        if (jpaCompanyRepository.count() > 0) {
            return;
        }

        // Create companies and save to db
        jpaCompanyRepository.saveAll(
                List.of(
                    SeedCompanyConstants.FPT_SOFTWARE,
                    SeedCompanyConstants.VNG,
                    SeedCompanyConstants.KMS,
                    SeedCompanyConstants.NASH_TECH,
                    SeedCompanyConstants.ABC_TECHNOLOGY
                )
        );
    }
}
