package jobportal.application.use_cases.profiles.jobseeker;

import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.application.mapper.JobSeekerProfileMapper;
import jobportal.application.use_cases.users.GetMyAccountUseCase;
import jobportal.application.utils.FileUtils;
import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.User;
import jobportal.domain.repository.JobSeekerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UpdateJobSeekerImageUseCase {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final GetMyAccountUseCase getMyAccountUseCase;
    private final JobSeekerProfileMapper jobSeekerProfileMapper;

    public UpdateJobSeekerImageUseCase(JobSeekerProfileRepository jobSeekerProfileRepository, GetMyAccountUseCase getMyAccountUseCase, JobSeekerProfileMapper jobSeekerProfileMapper) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.getMyAccountUseCase = getMyAccountUseCase;
        this.jobSeekerProfileMapper = jobSeekerProfileMapper;
    }

    public JobSeekerProfileResponse execute(MultipartFile imageFile) throws IOException {
        // Get my account
        User currentUser = getMyAccountUseCase.execute();

        // Get my profile
        JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findByUser(currentUser);

        // Resume
        String fileName = "";
        if (imageFile != null && !imageFile.isEmpty()) {

            // Validate file
            if (imageFile.getSize() > 5 * 1024 * 1024) {
                throw new IllegalArgumentException("Image must not exceed 5 MB");
            }

            if (!"image/png".equalsIgnoreCase(imageFile.getContentType())
                && !"image/jpeg".equalsIgnoreCase(imageFile.getContentType())) {
                throw new IllegalArgumentException("Image must be a PNG or JPEG or JPG file");
            }

            // Lưu file
            fileName = FileUtils.getFileName(imageFile);
            jobSeekerProfile.setProfileImage(fileName);
        }

        // Save to db
        JobSeekerProfile jobSeekerProfileSaved = jobSeekerProfileRepository.save(jobSeekerProfile);

        // Create dir
        String dir = "jobseeker/profile_image/" + jobSeekerProfileSaved.getUserId();
        FileUtils.saveFile(dir, fileName, imageFile);

        return jobSeekerProfileMapper.toResponse(
                jobSeekerProfileSaved
        );
    }
}
