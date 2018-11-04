package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JobStatus {

    @Id
    @GeneratedValue
    @Column(name = "job_status_id")
    private Integer jobStatusId;
    @Column(name = "job_status_text")
    private String jobStatusText;

    public JobStatus() {
    }

    public Integer getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(Integer jobStatusId) {
        this.jobStatusId = jobStatusId;
    }
    
    public String getJobStatusText() {
        return jobStatusText;
    }

    public void setJobStatusText(String jobStatusText) {
        this.jobStatusText = jobStatusText;
    }
}
