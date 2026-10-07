package jobportal.domain.repository;

import jobportal.domain.entity.AdminProfile;
import jobportal.domain.entity.User;

public interface AdminProfileRepository {
    boolean existsByUser(User user);

    void save(AdminProfile adminProfile);

    AdminProfile findByUser(User user);
}
