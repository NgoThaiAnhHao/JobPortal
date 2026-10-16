package jobportal.infrastructure.seeder;

import jobportal.application.utils.FileUtils;
import jobportal.domain.entity.JobSeekerProfile;
import jobportal.infrastructure.persistence.profiles.JpaAdminProfileRepository;
import jobportal.infrastructure.persistence.profiles.JpaJobSeekerProfileRepository;
import jobportal.infrastructure.persistence.profiles.JpaRecruiterProfileRepository;
import jobportal.infrastructure.seeder.constants.SeedProfileConstants;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ProfileSeeder {

    private final JpaAdminProfileRepository jpaAdminProfileRepository;
    private final JpaJobSeekerProfileRepository jpaJobSeekerProfileRepository;
    private final JpaRecruiterProfileRepository jpaRecruiterProfileRepository;

    public ProfileSeeder(JpaAdminProfileRepository jpaAdminProfileRepository, JpaJobSeekerProfileRepository jpaJobSeekerProfileRepository, JpaRecruiterProfileRepository jpaRecruiterProfileRepository) {
        this.jpaAdminProfileRepository = jpaAdminProfileRepository;
        this.jpaJobSeekerProfileRepository = jpaJobSeekerProfileRepository;
        this.jpaRecruiterProfileRepository = jpaRecruiterProfileRepository;
    }

    public void seed() throws IOException {
        if (jpaAdminProfileRepository.count() > 0
                || jpaRecruiterProfileRepository.count() > 0
                || jpaJobSeekerProfileRepository.count() > 0) {
            return;
        }

        // Save profiles
        jpaAdminProfileRepository.save(SeedProfileConstants.ADMIN_PROFILE);
        JobSeekerProfile jobseeker = jpaJobSeekerProfileRepository.save(SeedProfileConstants.JOB_SEEKER_PROFILE);
        jpaRecruiterProfileRepository.save(SeedProfileConstants.RECRUITER_PROFILE);

        // Run upload img
        seedProfileImage(jobseeker.getUserId());
    }

    private void seedProfileImage(Long jobSeekerId) throws IOException {
        // File ảnh mẫu
        Path source = Paths.get(
                "../../uploads/profile_img/jobseeker.png"
        ).toAbsolutePath().normalize();

        // Thư mục lưu ảnh thực tế
        Path targetDir = Paths.get(
                "jobseeker/profile_image/" + jobSeekerId
        ).toAbsolutePath().normalize();

        // Kiểm tra file mẫu tồn tại
        if (!Files.exists(source)) {
            throw new FileNotFoundException(
                    "Seed image not found: " + source
            );
        }

        // Tạo thư mục đích
        Files.createDirectories(targetDir);

        // Copy file ảnh
        Files.copy(
                source,
                targetDir.resolve("jobseeker.png"),
                StandardCopyOption.REPLACE_EXISTING
        );
    }
}
