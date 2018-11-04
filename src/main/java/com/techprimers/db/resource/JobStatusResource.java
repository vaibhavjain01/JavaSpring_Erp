package com.techprimers.db.resource;

import com.techprimers.db.model.Address;
import com.techprimers.db.model.JobStatus;
import com.techprimers.db.repository.EmployeePerformanceRepository;
import com.techprimers.db.repository.JobStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/jobstatus")
public class JobStatusResource {

    @Autowired
    private static JobStatusRepository jobStatusRepository;

    @GetMapping(value = "/all")
    public List<JobStatus> getAll() {
        return jobStatusRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<JobStatus> persist(@RequestBody final JobStatus jobstatus) {
    	jobStatusRepository.save(jobstatus);
        return jobStatusRepository.findAll();
    }

    public static void setRepo(JobStatusRepository inJobStatusRepository) {
    	jobStatusRepository = inJobStatusRepository;
    }
    
    public static JobStatus checkOrAddJobStatus(String jobStatus) {
    	JobStatus res = jobStatusRepository.findByJobStatusText(jobStatus);
		if(res == null) {
			JobStatus js = new JobStatus();
			js.setJobStatusText(jobStatus);
			jobStatusRepository.save(js);
			res = jobStatusRepository.findByJobStatusText(jobStatus);
		}
		return res;
	}
	
	public static Integer getJobStatusId(String jobStatus) {
		int jobStatusId = checkOrAddJobStatus(jobStatus).getJobStatusId();
		return jobStatusId;
	}
}
