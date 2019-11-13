package com.example.demo.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.batch.runner.SkipRecordJobRunner;

@RestController
@RequestMapping("/run")
public class SkipRecordController 
{
	@Autowired
	SkipRecordJobRunner skipRecordJobRunner;

    @RequestMapping(value = "/job")
    public String runJob() {
    	skipRecordJobRunner.runBatchJob();
        return String.format("Job SkipRecordJobRunner submitted successfully.");
    }
	

}
