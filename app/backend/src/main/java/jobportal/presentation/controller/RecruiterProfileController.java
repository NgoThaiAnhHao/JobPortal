package jobportal.presentation.controller;

import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.application.dto.profiles.RecruiterProfileResponse;
import jobportal.application.use_cases.profiles.recruiter.GetRecruiterProfileUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiters")
public class RecruiterProfileController {

    private final GetRecruiterProfileUseCase getRecruiterProfileUseCase;

    public RecruiterProfileController(GetRecruiterProfileUseCase getRecruiterProfileUseCase) {
        this.getRecruiterProfileUseCase = getRecruiterProfileUseCase;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('RECRUITER')")
    public RecruiterProfileResponse getMyRecruiterProfile() {
        return getRecruiterProfileUseCase.execute();
    }
}
