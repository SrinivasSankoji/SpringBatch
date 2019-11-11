package com.example.demo.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.batch.runner.CustomWriterJobRunner;

@RestController
@RequestMapping("/run")
public class CustomController 
{
	@Autowired
	CustomWriterJobRunner defaultReaderWriterJobRunner;

    @RequestMapping(value = "/job")
    public String runJob() {
    	defaultReaderWriterJobRunner.runBatchJob();
        return String.format("Job DefaultReaderWriter submitted successfully.");
    }
	

}
