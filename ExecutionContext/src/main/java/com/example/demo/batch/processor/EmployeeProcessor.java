package com.example.demo.batch.processor;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.batch.dto.EmployeeDTO;
import com.example.demo.batch.model.Employee;

@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeDTO, Employee>
{
	@Autowired
	ExecutionContext executionContext;
	
	@Override
	public Employee process(EmployeeDTO employeeDTO) throws Exception 
	{
		return Employee.builder()
			   .employeeId(employeeDTO.getEmployeeId()+executionContext.get("fileName"))
			   .firstName(employeeDTO.getFirstName())
			   .lastName(employeeDTO.getLastName())
			   .email(employeeDTO.getEmail())
			   .age(employeeDTO.getAge())
			   .build();
	}
}
