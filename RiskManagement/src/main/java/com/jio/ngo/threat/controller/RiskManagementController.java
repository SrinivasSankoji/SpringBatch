package com.jio.ngo.threat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jio.ngo.threat.dto.AuthenticationRequest;
import com.jio.ngo.threat.runner.RiskManagementJobRunner;

@RestController
@RequestMapping("/run")
public class RiskManagementController 
{
	@Autowired
	RiskManagementJobRunner riskManagementJobRunner;
	
	@PostMapping(value = "/job")
    public String runJob(@RequestBody AuthenticationRequest authenticationRequest) 
	{
		riskManagementJobRunner.runBatchJob(authenticationRequest);
        return String.format("Job DefaultReaderWriter submitted successfully.");
    }

}
