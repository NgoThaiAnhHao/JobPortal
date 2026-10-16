package jobportal.application.use_cases.profiles.jobseeker;

import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.application.mapper.JobSeekerProfileMapper;
import jobportal.application.use_cases.users.GetMyAccountUseCase;
import jobportal.application.utils.FileUtils;
import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.User;
import jobportal.domain.repository.JobSeekerProfileRepository;
import jobportal.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UpdateJobSeekerResumeUseCase {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final GetMyAccountUseCase getMyAccountUseCase;
    private final JobSeekerProfileMapper jobSeekerProfileMapper;

    public UpdateJobSeekerResumeUseCase(JobSeekerProfileRepository jobSeekerProfileRepository, GetMyAccountUseCase getMyAccountUseCase, JobSeekerProfileMapper jobSeekerProfileMapper) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.getMyAccountUseCase = getMyAccountUseCase;
        this.jobSeekerProfileMapper = jobSeekerProfileMapper;
    }

    @Transactional
    public JobSeekerProfileResponse execute(MultipartFile resumeFile) throws IOException {
        // Get my account
        User currentUser = getMyAccountUseCase.execute();

        // Get my profile
        JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findByUser(currentUser);

        // Resume
        String fileName = "";
        if (resumeFile != null && !resumeFile.isEmpty()) {

            // Validate file
            if (resumeFile.getSize() > 5 * 1024 * 1024) {
                throw new IllegalArgumentException("Resume must not exceed 5 MB");
            }

            if (!"application/pdf".equalsIgnoreCase(resumeFile.getContentType())) {
                throw new IllegalArgumentException("Resume must be a PDF file");
            }

            // Lưu file
            fileName = FileUtils.getFileName(resumeFile);
            jobSeekerProfile.setResumeUrl(fileName);
        }

        // Save to db
        JobSeekerProfile jobSeekerProfileSaved = jobSeekerProfileRepository.save(jobSeekerProfile);

        // Create dir
        String dir = "jobseeker/resumes/" + jobSeekerProfileSaved.getUserId();
        FileUtils.saveFile(dir, fileName, resumeFile);

        return jobSeekerProfileMapper.toResponse(
                jobSeekerProfileSaved
        );
    }
}
