package com.example.demo.batch.job;

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

import com.example.demo.batch.dto.EmployeeDTO;
import com.example.demo.batch.model.Employee;
import com.example.demo.batch.processor.EmployeeProcessor;
import com.example.demo.batch.reader.EmployeeJsonReader;
import com.example.demo.batch.writer.EmployeeDBWriter;

@EnableBatchProcessing
@Component
public class CustomReader 
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired	
	StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	EmployeeJsonReader employeeJsonReader;
	
	@Autowired
	EmployeeProcessor employeeProcessor;
	
	@Autowired
	EmployeeDBWriter employeeDBWriter;
	
    @Qualifier(value = "customReaderJob")
    @Bean
    public Job customReaderJob() throws Exception 
    {
        return jobBuilderFactory.get("customReaderJob")
	            .start(stepCustomReaderJob())
	            .build();
    }

    @Bean
    public Step stepCustomReaderJob() throws Exception {
        return this.stepBuilderFactory.get("step1")
                .<EmployeeDTO, Employee>chunk(1)
                .reader(employeeJsonReader)
                .processor(employeeProcessor)
                .writer(employeeDBWriter)
                .build();
    }
}
