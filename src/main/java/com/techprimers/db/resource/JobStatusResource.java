package com.techprimers.db.resource;

import com.techprimers.db.model.JobStatus;
import com.techprimers.db.repository.JobStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/jobstatus")
public class JobStatusResource {

    @Autowired
    JobStatusRepository jobStatusRepository;

    @GetMapping(value = "/all")
    public List<JobStatus> getAll() {
        return jobStatusRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<JobStatus> persist(@RequestBody final JobStatus jobstatus) {
    	jobStatusRepository.save(jobstatus);
        return jobStatusRepository.findAll();
    }

}
