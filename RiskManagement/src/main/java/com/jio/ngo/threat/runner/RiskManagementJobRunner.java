package com.jio.ngo.threat.runner;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jio.ngo.threat.dto.AuthenticationRequest;

@Component
public class RiskManagementJobRunner 
{

	@Autowired
	JobLauncher simpleJobLauncher;
	
	@Autowired
	ExecutionContext executionContext;

	@Autowired
	Job riskManagementJob;
	
	public void runBatchJob(AuthenticationRequest authenticationRequest) 
	{
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addDate("date", new Date(), true);
		executionContext.put("JsonData", authenticationRequest.getJsonData());
		runJob(riskManagementJob, jobParametersBuilder.toJobParameters());
	}

	public void runJob(Job job, JobParameters parameters) 
	{
		try {
			JobExecution jobExecution = simpleJobLauncher.run(job, parameters);
			jobExecution.getStatus();
		} catch (JobExecutionAlreadyRunningException e) 
		{
		} catch (JobRestartException e) {
		} catch (JobInstanceAlreadyCompleteException e) {
		} catch (JobParametersInvalidException e) {
		}
	}
	

}
