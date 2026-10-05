package jobportal.infrastructure.persistence.usertypes;

import jobportal.domain.entity.UserType;
import jobportal.domain.repository.UserTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserTypeRepositoryImpl implements UserTypeRepository {

    private final JpaUserTypeRepository jpaUserTypeRepository;

    public UserTypeRepositoryImpl(JpaUserTypeRepository jpaUserTypeRepository) {
        this.jpaUserTypeRepository = jpaUserTypeRepository;
    }

    @Override
    public List<UserType> findAll() {
        return jpaUserTypeRepository.findAll();
    }
}
