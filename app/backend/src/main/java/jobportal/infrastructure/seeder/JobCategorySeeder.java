package jobportal.infrastructure.seeder;

import jobportal.domain.entity.JobCategory;
import jobportal.infrastructure.persistence.job_categories.JpaJobCategoryRepository;
import jobportal.infrastructure.seeder.constants.SeederCategoryConstants;
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
                        new JobCategory(
                                SeederCategoryConstants.IT_AND_SOFTWARE_NAME,
                                SeederCategoryConstants.IT_AND_SOFTWARE_SLUG
                        ),
                        new JobCategory(
                                SeederCategoryConstants.DESIGN_NAME,
                                SeederCategoryConstants.DESIGN_SLUG
                        ),
                        new JobCategory(
                                SeederCategoryConstants.MARKETING_NAME,
                                SeederCategoryConstants.MARKETING_SLUG
                        )
                )
        );
    }
}
