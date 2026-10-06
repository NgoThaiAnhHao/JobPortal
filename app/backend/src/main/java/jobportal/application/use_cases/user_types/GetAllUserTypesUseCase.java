package jobportal.application.use_cases.user_types;

import jobportal.application.dto.usertypes.UserTypeResponse;
import jobportal.application.mapper.UserTypeMapper;
import jobportal.domain.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllUserTypesUseCase {

    private final UserTypeRepository userTypeRepository;
    private final UserTypeMapper userTypeMapper;

    public GetAllUserTypesUseCase(UserTypeRepository userTypeRepository, UserTypeMapper userTypeMapper) {
        this.userTypeRepository = userTypeRepository;
        this.userTypeMapper = userTypeMapper;
    }

    public List<UserTypeResponse> execute() {
        return userTypeRepository.findAll()
                .stream()
                .map(userTypeMapper::toResponse)
                .toList();
    }
}
