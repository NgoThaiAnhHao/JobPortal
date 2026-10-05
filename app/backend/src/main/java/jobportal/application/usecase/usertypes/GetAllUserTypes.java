package jobportal.application.usecase.usertypes;

import jobportal.application.dto.usertypes.UserTypeResponse;
import jobportal.application.mapper.UserTypeMapper;
import jobportal.domain.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class GetAllUserTypes {

    private final UserTypeRepository userTypeRepository;
    private final UserTypeMapper userTypeMapper;

    public GetAllUserTypes(UserTypeRepository userTypeRepository, UserTypeMapper userTypeMapper) {
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
