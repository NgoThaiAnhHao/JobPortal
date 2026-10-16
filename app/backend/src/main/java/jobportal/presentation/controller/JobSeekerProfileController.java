package jobportal.presentation.controller;

import jakarta.validation.Valid;
import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.application.dto.profiles.UpdateJobSeekerProfileRequest;
import jobportal.application.use_cases.profiles.jobseeker.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/job-seekers")
public class JobSeekerProfileController {

    private final GetJobSeekerProfileUseCase getJobSeekerProfileUseCase;
    private final UpdateJobSeekerProfileUseCase updateJobSeekerProfileUseCase;
    private final UpdateJobSeekerResumeUseCase updateJobSeekerResumeUseCase;
    private final GetJobSeekerResumeUseCase getJobSeekerResumeUseCase;
    private final UpdateJobSeekerImageUseCase updateJobSeekerImageUseCase;
    private final GetJobSeekerImageUseCase getJobSeekerImageUseCase;

    public JobSeekerProfileController(GetJobSeekerProfileUseCase getJobSeekerProfileUseCase, UpdateJobSeekerProfileUseCase updateJobSeekerProfileUseCase, UpdateJobSeekerResumeUseCase updateJobSeekerResumeUseCase, GetJobSeekerResumeUseCase getJobSeekerResumeUseCase, UpdateJobSeekerImageUseCase updateJobSeekerImageUseCase, GetJobSeekerImageUseCase getJobSeekerImageUseCase) {
        this.getJobSeekerProfileUseCase = getJobSeekerProfileUseCase;
        this.updateJobSeekerProfileUseCase = updateJobSeekerProfileUseCase;
        this.updateJobSeekerResumeUseCase = updateJobSeekerResumeUseCase;
        this.getJobSeekerResumeUseCase = getJobSeekerResumeUseCase;
        this.updateJobSeekerImageUseCase = updateJobSeekerImageUseCase;
        this.getJobSeekerImageUseCase = getJobSeekerImageUseCase;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public JobSeekerProfileResponse getMyJobSeekerProfile() {
        return getJobSeekerProfileUseCase.execute();
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public JobSeekerProfileResponse updateMyJobSeekerProfile(
            @Valid @RequestBody UpdateJobSeekerProfileRequest updateJobSeekerProfile) {
        return updateJobSeekerProfileUseCase.execute(updateJobSeekerProfile);
    }

    @PutMapping("/me/resume")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public JobSeekerProfileResponse updateMyJobSeekerResume(
            @RequestParam("resume") MultipartFile resumeFile) throws IOException {
        return updateJobSeekerResumeUseCase.execute(resumeFile);
    }

    @GetMapping("/me/resume")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<Resource> getMyJobSeekerResume() throws MalformedURLException {
        Resource resource = getJobSeekerResumeUseCase.execute();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @PutMapping("/me/profile-image")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public JobSeekerProfileResponse updateMyJobSeekerProfileImage(
            @RequestParam("profile-img") MultipartFile imgFile) throws IOException {
        return updateJobSeekerImageUseCase.execute(imgFile);
    }

    @GetMapping("/me/profile-image")
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public ResponseEntity<Resource> getMyJobSeekerProfileImage() throws IOException {
        Resource resource = getJobSeekerImageUseCase.execute();

        MediaType mediaType = MediaType.IMAGE_PNG;
        if (!"image/png".equalsIgnoreCase(resource.getFile().toPath().toString())) {
            mediaType = MediaType.IMAGE_JPEG;
        }
        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    }


}
