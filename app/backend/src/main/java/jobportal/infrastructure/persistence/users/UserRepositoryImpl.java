package jobportal.infrastructure.persistence.users;

import jobportal.domain.entity.User;
import jobportal.domain.entity.UserType;
import jobportal.domain.exception.common.authentication.UserNotFoundException;
import jobportal.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return jpaUserRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found.")
                );
    }

    @Override
    public boolean isExistsEmail(String email) {
        return jpaUserRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean isAdminExist(UserType userType) {
        return jpaUserRepository.existsByUserType(userType);
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }
}
