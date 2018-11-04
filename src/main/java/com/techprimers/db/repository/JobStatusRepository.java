package com.techprimers.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techprimers.db.model.JobStatus;

public interface JobStatusRepository extends JpaRepository<JobStatus, Integer> {

}
