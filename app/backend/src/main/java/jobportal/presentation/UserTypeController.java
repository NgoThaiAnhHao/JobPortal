package jobportal.presentation;

import jobportal.application.dto.usertypes.UserTypeResponse;
import jobportal.application.usecase.usertypes.GetAllUserTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usertypes")
public class UserTypeController {

    private final GetAllUserTypes getAllUserTypes;

    public UserTypeController(GetAllUserTypes getAllUserTypes) {
        this.getAllUserTypes = getAllUserTypes;
    }

    @GetMapping
    public List<UserTypeResponse> getAllUsersType() {
        return getAllUserTypes.execute();
    }
}
