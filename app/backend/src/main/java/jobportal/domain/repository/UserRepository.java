package jobportal.domain.repository;

import jobportal.domain.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findByEmail(String email);
}
