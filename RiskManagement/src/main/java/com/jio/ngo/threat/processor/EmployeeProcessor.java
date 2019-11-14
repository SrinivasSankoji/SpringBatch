package com.jio.ngo.threat.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.jio.ngo.threat.dto.EmployeeDTO;
import com.jio.ngo.threat.model.Employee;

@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeDTO, Employee>
{
	@Override
	public Employee process(EmployeeDTO employeeDTO) throws Exception 
	{
		return Employee.builder()
			   .employeeId(employeeDTO.getEmployeeId())
			   .firstName(employeeDTO.getFirstName())
			   .lastName(employeeDTO.getLastName())
			   .email(employeeDTO.getEmail())
			   .age(employeeDTO.getAge())
			   .build();
	}
}
