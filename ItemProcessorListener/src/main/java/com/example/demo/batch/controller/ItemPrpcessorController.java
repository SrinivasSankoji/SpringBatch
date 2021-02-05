package com.example.demo.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.batch.runner.ItemProcessorJobRunner;

@RestController
@RequestMapping("/run")
public class ItemPrpcessorController 
{
	@Autowired
	ItemProcessorJobRunner itemProcessorJob;

    @RequestMapping(value = "/job")
    public String runJob() {
    	itemProcessorJob.runBatchJob();
        return String.format("Job ItemProcessorJobRunner submitted successfully.");
    }
	

}
