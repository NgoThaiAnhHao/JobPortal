package jobportal.presentation.controller;

import jobportal.application.dto.profiles.JobSeekerProfileResponse;
import jobportal.application.dto.profiles.ProfileResponse;
import jobportal.application.dto.profiles.RecruiterProfileResponse;
import jobportal.application.dto.user.UserResponse;
import jobportal.application.mapper.UserMapper;
import jobportal.application.use_cases.profiles.GetAdminProfileUseCase;
import jobportal.application.use_cases.profiles.GetRecruiterProfileUseCase;
import jobportal.application.use_cases.users.GetAllUsersUseCase;
import jobportal.application.use_cases.profiles.GetJobSeekerProfileUseCase;
import jobportal.application.use_cases.users.GetMyAccountUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetMyAccountUseCase getMyAccountUseCase;
    private final GetJobSeekerProfileUseCase getJobSeekerProfileUseCase;
    private final GetAdminProfileUseCase getAdminProfileUseCase;
    private final GetRecruiterProfileUseCase getRecruiterProfileUseCase;
    private final UserMapper userMapper;

    public UserController(GetAllUsersUseCase getAllUsersUseCase, GetMyAccountUseCase getMyAccountUseCase, GetJobSeekerProfileUseCase getJobSeekerProfileUseCase, GetAdminProfileUseCase getAdminProfileUseCase, GetRecruiterProfileUseCase getRecruiterProfileUseCase, UserMapper userMapper) {
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.getMyAccountUseCase = getMyAccountUseCase;
        this.getJobSeekerProfileUseCase = getJobSeekerProfileUseCase;
        this.getAdminProfileUseCase = getAdminProfileUseCase;
        this.getRecruiterProfileUseCase = getRecruiterProfileUseCase;
        this.userMapper = userMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return getAllUsersUseCase.execute();
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getMyAccount(){
        return userMapper.toResponse(getMyAccountUseCase.execute());
    }

    @GetMapping("/job-seeker/my-profile")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('JOB_SEEKER')")
    public JobSeekerProfileResponse getMyJobSeekerProfile(){
        return getJobSeekerProfileUseCase.execute();
    }

    @GetMapping("/recruiter/my-profile")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('RECRUITER')")
    public RecruiterProfileResponse getMyRecruiterProfile(){
        return getRecruiterProfileUseCase.execute();
    }

    @GetMapping("/admin/my-profile")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ProfileResponse getMyAdminProfile(){
        return getAdminProfileUseCase.execute();
    }
}
