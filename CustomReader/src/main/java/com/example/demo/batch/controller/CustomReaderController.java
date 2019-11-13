package com.example.demo.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.batch.runner.CustomReaderJobRunner;

@RestController
@RequestMapping("/run")
public class CustomReaderController 
{
	@Autowired
	CustomReaderJobRunner customReaderJobRunner;

    @RequestMapping(value = "/job")
    public String runJob() {
    	customReaderJobRunner.runBatchJob();
        return String.format("Job CustomReader submitted successfully.");
    }
	

}
