package jobportal.presentation.controller;

import jobportal.application.dto.usertypes.UserTypeResponse;
import jobportal.application.use_cases.user_types.GetAllUserTypesUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usertypes")
public class UserTypeController {

    private final GetAllUserTypesUseCase getAllUserTypesUseCase;

    public UserTypeController(GetAllUserTypesUseCase getAllUserTypesUseCase) {
        this.getAllUserTypesUseCase = getAllUserTypesUseCase;
    }

    @GetMapping
    public List<UserTypeResponse> getAllUsersType() {
        return getAllUserTypesUseCase.execute();
    }
}
