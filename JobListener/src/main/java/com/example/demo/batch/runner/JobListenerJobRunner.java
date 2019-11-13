package com.example.demo.batch.runner;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.batch.util.Constants;

@Component
public class JobListenerJobRunner 
{
	private static final Logger logger = LoggerFactory.getLogger(JobListenerJobRunner.class);

	@Autowired
	JobLauncher jobListenerJobLauncher;

	@Autowired
	Job jobListenerJob;

	@Async
	public void runBatchJob() 
	{
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, "employees.csv");
		jobParametersBuilder.addDate("date", new Date(), true);
		runJob(jobListenerJob, jobParametersBuilder.toJobParameters());
	}

	public void runJob(Job job, JobParameters parameters) 
	{
		try {
			JobExecution jobExecution = jobListenerJobLauncher.run(job, parameters);
			jobExecution.getStatus();
		} catch (JobExecutionAlreadyRunningException e) {
			logger.info("Job with fileName={} is already running.",
					parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
		} catch (JobRestartException e) {
			logger.info("Job with fileName={} was not restarted.",
					parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
		} catch (JobInstanceAlreadyCompleteException e) {
			logger.info("Job with fileName={} already completed.",
					parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
		} catch (JobParametersInvalidException e) {
			logger.info("Invalid job parameters.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
		}
	}

}