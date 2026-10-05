package jobportal.infrastructure.persistence.usertypes;

import jobportal.domain.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserTypeRepository extends JpaRepository<UserType, Integer> {
}
