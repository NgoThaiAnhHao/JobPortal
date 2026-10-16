package jobportal.application.use_cases.profiles.jobseeker;

import jobportal.application.use_cases.users.GetMyAccountUseCase;
import jobportal.domain.entity.JobSeekerProfile;
import jobportal.domain.entity.User;
import jobportal.domain.repository.JobSeekerProfileRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class GetJobSeekerResumeUseCase {

    private final GetMyAccountUseCase getMyAccountUseCase;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    public GetJobSeekerResumeUseCase(GetMyAccountUseCase getMyAccountUseCase, JobSeekerProfileRepository jobSeekerProfileRepository) {
        this.getMyAccountUseCase = getMyAccountUseCase;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }

    public Resource execute() throws MalformedURLException {
        // Get my account
        User currentUser = getMyAccountUseCase.execute();

        // Get my profile
        JobSeekerProfile jobSeekerProfile = jobSeekerProfileRepository.findByUser(currentUser);

        // Get dir
        String dir = "jobseeker/resumes/" + jobSeekerProfile.getUserId();

        // Get path: jobseeker/resumes/3
        Path directoryPath = Paths.get(dir);

        // Check path not exist => create new path
        if (!Files.exists(directoryPath)) {
            throw new RuntimeException("Path not found.");
        }

        // Get current paths: jobseeker/resumes/3/resume.pdf
        // normalize(): Chuẩn hóa đường dẫn bằng cách loại bỏ các thành phần dư thừa như . và ..
        Path filePath = directoryPath.resolve(jobSeekerProfile.getResumeUrl()).normalize();

        return new UrlResource(filePath.toUri());
    }
}
