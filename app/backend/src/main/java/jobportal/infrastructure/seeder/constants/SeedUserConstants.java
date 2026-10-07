package jobportal.infrastructure.seeder.constants;

import jobportal.domain.entity.User;
import jobportal.domain.enums.ProviderEnum;

import java.time.LocalDateTime;

// Final class: Vì class này chỉ chứa hằng số
// Password for all accounts: @Test123
public final class SeedUserConstants {

    public static final User ADMIN = new User(
            "admin@gmail.com",
            "$2a$12$68NVxd//sMKyBwaKiqCh5OjDZoZX5HkUXukmwtUtUZS4i3tohPxrm",
            ProviderEnum.LOCAL,
            true,
            SeedUserTypeConstants.ADMIN_TYPE,
            LocalDateTime.now()
    );

    public static final User RECRUITER = new User(
            "recruiter@gmail.com",
            "$2a$12$68NVxd//sMKyBwaKiqCh5OjDZoZX5HkUXukmwtUtUZS4i3tohPxrm",
            ProviderEnum.LOCAL,
            true,
            SeedUserTypeConstants.RECRUITER_TYPE,
            LocalDateTime.now()
    );

    public static final User JOB_SEEKER = new User(
            "jobseeker@gmail.com",
            "$2a$12$68NVxd//sMKyBwaKiqCh5OjDZoZX5HkUXukmwtUtUZS4i3tohPxrm",
            ProviderEnum.LOCAL,
            true,
            SeedUserTypeConstants.JOB_SEEKER_TYPE,
            LocalDateTime.now()
    );

    public SeedUserConstants() {
    }
}
