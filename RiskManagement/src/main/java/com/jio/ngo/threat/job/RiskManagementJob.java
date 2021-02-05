package com.jio.ngo.threat.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.jio.ngo.threat.dto.EmployeeDTO;
import com.jio.ngo.threat.listener.JobListener;
import com.jio.ngo.threat.listener.ReaderListener;
import com.jio.ngo.threat.model.Employee;
import com.jio.ngo.threat.processor.EmployeeProcessor;
import com.jio.ngo.threat.reader.EmployeeReader;
import com.jio.ngo.threat.writer.EmployeeWriter;

@Component
@EnableBatchProcessing
public class RiskManagementJob 
{
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired	
	StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	EmployeeReader employeeReader;
	
	@Autowired
	EmployeeProcessor employeeProcessor;
	
	@Autowired
	EmployeeWriter employeeWriter;
	
	@Autowired
	JobListener jobListener;
	
	@Autowired
	ReaderListener readerListener;
	
	@Qualifier(value = "riskManagementJob")
    @Bean
    public Job customReaderJob() throws Exception 
    {
        return jobBuilderFactory.get("riskManagementJob")
        .start(stepCustomReaderJob())
        .listener(jobListener)
        .build();
    }

    @Bean
    public Step stepCustomReaderJob() throws Exception 
    {
        return this.stepBuilderFactory.get("step1")
        .<EmployeeDTO, Employee>chunk(1)
        .reader(employeeReader)
        .processor(employeeProcessor)
        .writer(employeeWriter)
        .listener(readerListener)
        .build();
    }
}
