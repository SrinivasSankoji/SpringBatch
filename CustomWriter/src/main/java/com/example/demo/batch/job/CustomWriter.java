package com.example.demo.batch.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
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

import com.example.demo.batch.dto.OneLakhDTO;
import com.example.demo.batch.mapper.OneLakhRowMapper;
import com.example.demo.batch.model.OneLakh;
import com.example.demo.batch.processor.OneLakhProcessor;
import com.example.demo.batch.writer.OneLakhDBWriter;

@Configuration
@EnableBatchProcessing
public class CustomWriter 
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired	
	StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	OneLakhProcessor employeeProcessor;
	
	@Autowired
	OneLakhDBWriter oneLakhDBWriter;
	
    @Qualifier(value = "defaultReaderWriterJob")
    @Bean
    public Job defaultReaderWriterJob() throws Exception 
    {
        return jobBuilderFactory.get("defaultReaderWriterJob")
	            .start(step1DefaultReaderWriterJob())
	            .build();
    }

    @Bean
    public Step step1DefaultReaderWriterJob() throws Exception {
        return this.stepBuilderFactory.get("step1")
                .<OneLakhDTO, OneLakh>chunk(100)
                .reader(employeeReader())
                .processor(employeeProcessor)
                .writer(oneLakhDBWriter)
                .build();
    }

    @Bean
    @StepScope
    Resource inputFileResource(@Value("#{jobParameters[fileName]}") final String fileName) throws Exception {
        return new ClassPathResource(fileName);
    }

    @Bean
    @StepScope
    public FlatFileItemReader<OneLakhDTO> employeeReader() throws Exception {
        FlatFileItemReader<OneLakhDTO> reader = new FlatFileItemReader<>();
        reader.setResource(inputFileResource(null));
        reader.setLineMapper(new DefaultLineMapper<OneLakhDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("columnOne", "columnTwo", "columnThree");
                setDelimiter(",");
            }});
            setFieldSetMapper(new OneLakhRowMapper());
        }});
        return reader;
    }
}
