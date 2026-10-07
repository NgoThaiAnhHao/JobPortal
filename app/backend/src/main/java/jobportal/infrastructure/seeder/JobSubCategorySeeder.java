package jobportal.infrastructure.seeder;

import jobportal.domain.entity.JobCategory;
import jobportal.domain.entity.JobSubcategory;
import jobportal.domain.exception.common.ResourceNotFoundException;
import jobportal.infrastructure.persistence.job_categories.JpaJobCategoryRepository;
import jobportal.infrastructure.persistence.job_subcategories.JpaJobSubcategoryRepository;
import jobportal.infrastructure.seeder.constants.SeederCategoryConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobSubCategorySeeder {

    private final JpaJobSubcategoryRepository jpaJobSubcategoryRepository;
    private final JpaJobCategoryRepository jpaJobCategoryRepository;

    public JobSubCategorySeeder(JpaJobSubcategoryRepository jpaJobSubcategoryRepository, JpaJobCategoryRepository jpaJobCategoryRepository) {
        this.jpaJobSubcategoryRepository = jpaJobSubcategoryRepository;
        this.jpaJobCategoryRepository = jpaJobCategoryRepository;
    }

    public void seed() {
        if (jpaJobSubcategoryRepository.count() > 0) {
            return;
        }

        // Find Job Categories
        JobCategory itSoftWare = jpaJobCategoryRepository
                .findByNameAndSlug(
                        SeederCategoryConstants.IT_AND_SOFTWARE_NAME,
                        SeederCategoryConstants.IT_AND_SOFTWARE_SLUG
                )
                .orElseThrow(() ->
                    new ResourceNotFoundException("Job category not found.")
                );

        JobCategory marketing = jpaJobCategoryRepository
                .findByNameAndSlug(
                    SeederCategoryConstants.MARKETING_NAME,
                    SeederCategoryConstants.MARKETING_SLUG
                ).orElseThrow(() ->
                        new ResourceNotFoundException("Job category not found.")
                );

        JobCategory design = jpaJobCategoryRepository
                .findByNameAndSlug(
                        SeederCategoryConstants.DESIGN_NAME,
                        SeederCategoryConstants.DESIGN_SLUG
                ).orElseThrow(() ->
                        new ResourceNotFoundException("Job category not found.")
                );

        // Create job subcategories
        jpaJobSubcategoryRepository.saveAll(
                List.of(
                        // IT & Software
                        new JobSubcategory(itSoftWare, "Backend Development", "backend-development"),
                        new JobSubcategory(itSoftWare, "Frontend Development", "frontend-development"),
                        new JobSubcategory(itSoftWare, "DevOps", "dev-ops"),

                        // Marketing
                        new JobSubcategory(marketing, "Digital Marketing", "digital-marketing"),
                        new JobSubcategory(marketing, "Content Marketing", "content-marketing"),
                        new JobSubcategory(marketing, "Social Media Marketing", "social-media-marketing"),
                        new JobSubcategory(marketing, "SEO", "seo"),
                        new JobSubcategory(marketing, "Brand Marketing", "brand-marketing"),

                        // Design
                        new JobSubcategory(design, "UI/UX Design", "ui-ux-design"),
                        new JobSubcategory(design, "Graphic Design", "graphic-design"),
                        new JobSubcategory(design, "Product Design", "product-design"),
                        new JobSubcategory(design, "Motion Design", "motion-design"),
                        new JobSubcategory(design, "3D Design", "3d-design")
                )
        );


    }
}
