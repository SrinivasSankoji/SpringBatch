package com.example.demo.batch.listener;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.batch.dto.EmployeeDTO;

@Component
public class ReaderListener implements ItemReadListener<EmployeeDTO>
{
	
	@Autowired
	ExecutionContext executionContext;
	
	@Override
	public void beforeRead() 
	{
	}

	@Override
	public void afterRead(EmployeeDTO employeeDTO) 
	{
		if(employeeDTO.getEmployeeId().equalsIgnoreCase("E101"))
		{
			employeeDTO=null;
		}
	}

	@Override
	public void onReadError(Exception exception) 
	{
		 if(exception instanceof FlatFileParseException)
		 {
			 executionContext.putString("Error", ((FlatFileParseException) exception).getInput());
		 }
	}
	
}
