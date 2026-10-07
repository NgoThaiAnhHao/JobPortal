package jobportal.infrastructure.seeder;

import jobportal.infrastructure.persistence.job_categories.JpaJobCategoryRepository;
import jobportal.infrastructure.seeder.constants.SeedJobCategoryConstants;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class JobCategorySeeder {

    public JobCategorySeeder(JpaJobCategoryRepository jpaJobCategoryRepository) {
        this.jpaJobCategoryRepository = jpaJobCategoryRepository;
    }

    private final JpaJobCategoryRepository jpaJobCategoryRepository;

    public void seed() {
        if (jpaJobCategoryRepository.count() > 0) {
            return;
        }

        // Create job categories
        jpaJobCategoryRepository.saveAll(
                List.of(
                        SeedJobCategoryConstants.IT_AND_SOFTWARE,
                        SeedJobCategoryConstants.DESIGN,
                        SeedJobCategoryConstants.MARKETING
                )
        );
    }
}
