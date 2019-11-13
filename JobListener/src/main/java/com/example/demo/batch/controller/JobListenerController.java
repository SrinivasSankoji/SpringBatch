package com.example.demo.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.batch.runner.JobListenerJobRunner;

@RestController
@RequestMapping("/run")
public class JobListenerController 
{
	@Autowired
	JobListenerJobRunner jobListenerJobRunner;

    @RequestMapping(value = "/job")
    public String runJob() {
    	jobListenerJobRunner.runBatchJob();
        return String.format("Job JobListenerJobRunner submitted successfully.");
    }
	

}
