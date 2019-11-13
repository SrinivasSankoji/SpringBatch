package com.example.demo.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.EmployeeRepository;
import com.example.demo.batch.model.Employee;

@Component
public class EmployeeDBWriter implements ItemWriter<Employee>
{
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDBWriter.class);
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void write(List<? extends Employee> EmployeeList) throws Exception 
	{
		employeeRepository.saveAll(EmployeeList);
	    logger.info("{} employees saved in database", EmployeeList.size());
		
	}
}
