package com.example.demo.batch.controller;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.batch.runner.ItemReaderJobRunner;

@RestController
@RequestMapping("/run")
public class ItemReaderWriterController 
{
	@Autowired
	ItemReaderJobRunner itemReaderJob;
	
	@Autowired
	ExecutionContext executionContext;

    @RequestMapping(value = "/job")
    public String runJob() {
    	itemReaderJob.runBatchJob();
        return String.format("Job ItemReaderJob submitted successfully.");
    }
	

}
