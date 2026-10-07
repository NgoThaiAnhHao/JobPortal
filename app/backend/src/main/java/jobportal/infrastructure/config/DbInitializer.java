package jobportal.infrastructure.config;

import jobportal.domain.entity.JobSubcategory;
import jobportal.infrastructure.seeder.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbInitializer implements CommandLineRunner {
    private final UserTypeSeeder userTypeSeeder;
    private final UserSeeder userSeeder;
    private final ProfileSeeder profileSeeder;
    private final CompanySeeder companySeeder;
    private final JobCategorySeeder jobCategorySeeder;
    private final JobSubCategorySeeder jobSubCategorySeeder;

    public DbInitializer(UserTypeSeeder userTypeSeeder, UserSeeder userSeeder, ProfileSeeder profileSeeder, CompanySeeder companySeeder, JobCategorySeeder jobCategorySeeder, JobSubCategorySeeder jobSubCategorySeeder) {
        this.userTypeSeeder = userTypeSeeder;
        this.userSeeder = userSeeder;
        this.profileSeeder = profileSeeder;
        this.companySeeder = companySeeder;
        this.jobCategorySeeder = jobCategorySeeder;
        this.jobSubCategorySeeder = jobSubCategorySeeder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userTypeSeeder.seed();
        userSeeder.seed();
        profileSeeder.seed();
        companySeeder.seed();
        jobCategorySeeder.seed();
        jobSubCategorySeeder.seed();
    }
}
