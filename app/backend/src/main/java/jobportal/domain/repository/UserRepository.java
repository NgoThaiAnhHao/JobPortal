package jobportal.domain.repository;

import jobportal.domain.entity.User;
import jobportal.domain.entity.UserType;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findByEmail(String email);

    boolean isExistsEmail(String email);

    boolean isAdminExist(UserType userType);

    User save(User user);
}
