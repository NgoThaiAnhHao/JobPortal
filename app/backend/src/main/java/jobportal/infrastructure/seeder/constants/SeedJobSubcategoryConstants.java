package jobportal.infrastructure.seeder.constants;

import jobportal.domain.entity.JobSubcategory;

public final class SeedJobSubcategoryConstants {

    // IT & Software
    public static final JobSubcategory BACKEND_DEVELOPMENT = new JobSubcategory(
                SeedJobCategoryConstants.IT_AND_SOFTWARE,
                "Backend Development",
                "backend-development"
    );

    public static final JobSubcategory FRONTEND_DEVELOPMENT = new JobSubcategory(
            SeedJobCategoryConstants.IT_AND_SOFTWARE,
            "Frontend Development",
            "frontend-development"
    );

    public static final JobSubcategory DEV_OPS = new JobSubcategory(
            SeedJobCategoryConstants.IT_AND_SOFTWARE,
            "DevOps",
            "dev-ops"
    );

    // Marketing
    public static final JobSubcategory DIGITAL_MARKETING = new JobSubcategory(
            SeedJobCategoryConstants.MARKETING,
            "Digital Marketing",
            "digital-marketing"
    );

    public static final JobSubcategory CONTENT_MARKETING = new JobSubcategory(
            SeedJobCategoryConstants.MARKETING,
            "Content Marketing",
            "content-marketing"
    );

    public static final JobSubcategory SOCIAL_MEDIA_MARKETING = new JobSubcategory(
            SeedJobCategoryConstants.MARKETING,
            "Social Media Marketing",
            "social-media-marketing"
    );

    public static final JobSubcategory SEO = new JobSubcategory(
            SeedJobCategoryConstants.MARKETING,
            "SEO",
            "seo"
    );

    public static final JobSubcategory BRAND_MARKETING = new JobSubcategory(
            SeedJobCategoryConstants.MARKETING,
            "Brand Marketing",
            "brand-marketing"
    );

    // Design
    public static final JobSubcategory UI_UX_DESIGN = new JobSubcategory(
            SeedJobCategoryConstants.DESIGN,
            "UI/UX Design",
            "ui-ux-design"
    );

    public static final JobSubcategory GRAPHIC_DESIGN = new JobSubcategory(
            SeedJobCategoryConstants.DESIGN,
            "Graphic Design",
            "graphic-design"
    );

    public static final JobSubcategory PRODUCT_DESIGN = new JobSubcategory(
            SeedJobCategoryConstants.DESIGN,
            "Product Design",
            "product-design"
    );

    public static final JobSubcategory MOTION_DESIGN = new JobSubcategory(
            SeedJobCategoryConstants.DESIGN,
            "Motion Design",
            "motion-design"
    );

    public static final JobSubcategory DESIGN_3D = new JobSubcategory(
            SeedJobCategoryConstants.DESIGN,
            "3D Design",
            "3d-design"
    );

    public SeedJobSubcategoryConstants() {
    }
}
