package jobportal.infrastructure.seeder;

import jobportal.infrastructure.persistence.job_subcategories.JpaJobSubcategoryRepository;
import jobportal.infrastructure.seeder.constants.SeedJobSubcategoryConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobSubCategorySeeder {

    private final JpaJobSubcategoryRepository jpaJobSubcategoryRepository;

    public JobSubCategorySeeder(JpaJobSubcategoryRepository jpaJobSubcategoryRepository) {
        this.jpaJobSubcategoryRepository = jpaJobSubcategoryRepository;
    }

    public void seed() {
        if (jpaJobSubcategoryRepository.count() > 0) {
            return;
        }

        // Create job subcategories
        jpaJobSubcategoryRepository.saveAll(
                List.of(
                        // IT & Software
                        SeedJobSubcategoryConstants.BACKEND_DEVELOPMENT,
                        SeedJobSubcategoryConstants.FRONTEND_DEVELOPMENT,
                        SeedJobSubcategoryConstants.DEV_OPS,

                        // Marketing
                        SeedJobSubcategoryConstants.CONTENT_MARKETING,
                        SeedJobSubcategoryConstants.DIGITAL_MARKETING,
                        SeedJobSubcategoryConstants.BRAND_MARKETING,
                        SeedJobSubcategoryConstants.SEO,
                        SeedJobSubcategoryConstants.SOCIAL_MEDIA_MARKETING,

                        // Design
                        SeedJobSubcategoryConstants.DESIGN_3D,
                        SeedJobSubcategoryConstants.GRAPHIC_DESIGN,
                        SeedJobSubcategoryConstants.MOTION_DESIGN,
                        SeedJobSubcategoryConstants.PRODUCT_DESIGN,
                        SeedJobSubcategoryConstants.UI_UX_DESIGN
                )
        );


    }
}
