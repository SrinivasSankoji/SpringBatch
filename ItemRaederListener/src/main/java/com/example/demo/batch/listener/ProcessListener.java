package com.example.demo.batch.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import com.example.demo.batch.dto.EmployeeDTO;
import com.example.demo.batch.model.Employee;

@Component
public class ProcessListener implements ItemProcessListener<EmployeeDTO, Employee>
{

	@Override
	public void beforeProcess(EmployeeDTO employeeDTO) 
	{
		
	}

	@Override
	public void afterProcess(EmployeeDTO employeeDTO, Employee employee) 
	{
		if(employeeDTO==null)
		{
			employee=null;
		}
		
	}

	@Override
	public void onProcessError(EmployeeDTO employeeDTO, Exception e) 
	{
		 System.out.println("On error :" + e.getMessage());
	}

}
