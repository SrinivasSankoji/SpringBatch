package com.jio.ngo.threat.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jio.ngo.threat.model.Employee;
import com.jio.ngo.threat.repository.EmployeeRepository;

@Component
public class EmployeeWriter implements ItemWriter<Employee>
{
private static final Logger logger = LoggerFactory.getLogger(EmployeeWriter.class);
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void write(List<? extends Employee> EmployeeList) throws Exception 
	{
		employeeRepository.saveAll(EmployeeList);
	    logger.info("{} employees saved in database", EmployeeList.size());
		
	}

}
