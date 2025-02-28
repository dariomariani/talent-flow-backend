package com.dariom.persistence.repositories;

import com.dariom.persistence.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    @Query("SELECT j FROM JobEntity j WHERE j.applications IS EMPTY ORDER BY j.publishDate")
    List<JobEntity> findOpenJobsOrderedByPublishDate();
}
