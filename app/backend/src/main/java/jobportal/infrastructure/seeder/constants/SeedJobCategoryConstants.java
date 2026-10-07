package jobportal.infrastructure.seeder.constants;

import jobportal.domain.entity.JobCategory;

public final class SeedJobCategoryConstants {

    public static final JobCategory IT_AND_SOFTWARE = new JobCategory(
            "IT & Software",
            "it-software"
    );

    public static final JobCategory MARKETING = new JobCategory(
            "Marketing",
            "marketing"
    );

    public static final JobCategory DESIGN = new JobCategory(
            "Design",
            "design"
    );

    public SeedJobCategoryConstants() {
    }
}
