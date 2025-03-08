package com.dariom.persistence.repositories;

import com.dariom.persistence.entities.JobEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Integer> {

    List<JobEntity> findByStatus(String status);

    List<JobEntity> findByStatus(String status, Sort sort);

}
