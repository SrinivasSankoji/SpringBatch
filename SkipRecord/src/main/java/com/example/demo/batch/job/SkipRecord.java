package com.example.demo.batch.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.example.demo.batch.dto.EmployeeDTO;
import com.example.demo.batch.mapper.EmployeeRowMapper;
import com.example.demo.batch.model.Employee;
import com.example.demo.batch.processor.EmployeeProcessor;
import com.example.demo.batch.skip.JobSkipPolicy;

@Configuration
@EnableBatchProcessing
public class SkipRecord 
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired	
	StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	EmployeeProcessor employeeProcessor;
	
    @Qualifier(value = "skipRecordJob")
    @Bean
    public Job skipRecordJob() throws Exception 
    {
        return jobBuilderFactory.get("skipRecordJob")
	            .start(step1SkipRecordJob())
	            .build();
    }

    @Bean
    public Step step1SkipRecordJob() throws Exception {
        return this.stepBuilderFactory.get("step1")
                .<EmployeeDTO, Employee>chunk(5)
                .reader(employeeReader())
                .processor(employeeProcessor)
                .writer(employeeDBWriterDefault())
                .faultTolerant().skipPolicy(skipPolicy())
                .build();
    }

    @Bean
    @StepScope
    Resource inputFileResource(@Value("#{jobParameters[fileName]}") final String fileName) throws Exception {
        return new ClassPathResource(fileName);
    }

    @Bean
    @StepScope
    public FlatFileItemReader<EmployeeDTO> employeeReader() throws Exception {
        FlatFileItemReader<EmployeeDTO> reader = new FlatFileItemReader<>();
        reader.setResource(inputFileResource(null));
        reader.setLineMapper(new DefaultLineMapper<EmployeeDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("employeeId", "firstName", "lastName", "email", "age");
                setDelimiter(",");
            }});
            setFieldSetMapper(new EmployeeRowMapper());
        }});
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<Employee> employeeDBWriterDefault() {
        JdbcBatchItemWriter<Employee> itemWriter = new JdbcBatchItemWriter<Employee>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("insert into employee (EMPLOYEE_ID, FIRSTNAME, LASTNAME, EMAIL, AGE) values (:employeeId, :firstName, :lastName, :email, :age)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
        return itemWriter;
    }
    
    @Bean
    public JobSkipPolicy skipPolicy()
    {
    	return new JobSkipPolicy();
    }

}
