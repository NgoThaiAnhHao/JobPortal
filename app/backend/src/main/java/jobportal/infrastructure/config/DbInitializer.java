package jobportal.infrastructure.config;

import jobportal.infrastructure.seeder.UserSeeder;
import jobportal.infrastructure.seeder.UserTypeSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbInitializer implements CommandLineRunner {
    private final UserTypeSeeder userTypeSeeder;
    private final UserSeeder userSeeder;

    public DbInitializer(UserTypeSeeder userTypeSeeder, UserSeeder userSeeder) {
        this.userTypeSeeder = userTypeSeeder;
        this.userSeeder = userSeeder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userTypeSeeder.seed();
        userSeeder.seed();
    }
}
