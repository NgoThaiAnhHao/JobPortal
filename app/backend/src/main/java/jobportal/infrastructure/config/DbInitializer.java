package jobportal.infrastructure.config;

import jobportal.infrastructure.seeder.ProfileSeeder;
import jobportal.infrastructure.seeder.UserSeeder;
import jobportal.infrastructure.seeder.UserTypeSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbInitializer implements CommandLineRunner {
    private final UserTypeSeeder userTypeSeeder;
    private final UserSeeder userSeeder;
    private final ProfileSeeder profileSeeder;

    public DbInitializer(UserTypeSeeder userTypeSeeder, UserSeeder userSeeder, ProfileSeeder profileSeeder) {
        this.userTypeSeeder = userTypeSeeder;
        this.userSeeder = userSeeder;
        this.profileSeeder = profileSeeder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userTypeSeeder.seed();
        userSeeder.seed();
        profileSeeder.seed();
    }
}
