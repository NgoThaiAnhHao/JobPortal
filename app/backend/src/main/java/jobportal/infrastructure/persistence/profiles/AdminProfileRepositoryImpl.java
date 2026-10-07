package jobportal.infrastructure.persistence.profiles;

import jobportal.domain.entity.AdminProfile;
import jobportal.domain.entity.User;
import jobportal.domain.exception.common.user.ProfileNotFoundException;
import jobportal.domain.repository.AdminProfileRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AdminProfileRepositoryImpl implements AdminProfileRepository {

    private final JpaAdminProfileRepository jpaAdminProfileRepository;

    public AdminProfileRepositoryImpl(JpaAdminProfileRepository jpaAdminProfileRepository) {
        this.jpaAdminProfileRepository = jpaAdminProfileRepository;
    }

    @Override
    public boolean existsByUser(User user) {
        return jpaAdminProfileRepository.existsByUser(user);
    }

    @Override
    public void save(AdminProfile adminProfile) {
        jpaAdminProfileRepository.save(adminProfile);
    }

    @Override
    public AdminProfile findByUser(User user) {
        return jpaAdminProfileRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new ProfileNotFoundException("Your admin profile not found.")
                );
    }
}
