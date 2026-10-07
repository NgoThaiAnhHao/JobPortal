package jobportal.infrastructure.persistence.companies;

import jobportal.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCompanyRepository extends JpaRepository<Company, Long> {
}
