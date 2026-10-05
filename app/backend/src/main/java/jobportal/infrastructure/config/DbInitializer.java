package jobportal.infrastructure.config;

import jobportal.infrastructure.seeder.UserTypeSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbInitializer implements CommandLineRunner {
    private final UserTypeSeeder userTypeSeeder;

    public DbInitializer(UserTypeSeeder userTypeSeeder) {
        this.userTypeSeeder = userTypeSeeder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userTypeSeeder.seed();
    }
}
