package jobportal.domain.repository;

import jobportal.domain.entity.Job;

import java.util.List;

public interface JobRepository {
    List<Job> findAll();
}
