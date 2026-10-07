package jobportal.domain.repository;

import jobportal.domain.entity.RecruiterProfile;
import jobportal.domain.entity.User;

public interface RecruiterProfileRepository {

    boolean existsByUser(User user);

    void save(RecruiterProfile recruiterProfile);

    RecruiterProfile findByUser(User user);
}
