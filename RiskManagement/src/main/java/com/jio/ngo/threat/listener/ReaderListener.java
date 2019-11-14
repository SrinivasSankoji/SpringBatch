package com.jio.ngo.threat.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jio.ngo.threat.dto.EmployeeDTO;

@Component
public class ReaderListener implements ItemReadListener<EmployeeDTO>
{
	
	@Autowired
	ExecutionContext executionContext;
	
	@Override
	public void beforeRead() 
	{
		//List<Object[]> result=(List<Object[]>) executionContext.get("JsonData");
		//convertObjectToList(result);
	}

	@Override
	public void afterRead(EmployeeDTO employeeDTO) 
	{
		 System.out.println("After Reading :" + employeeDTO.toString());
	}

	@Override
	public void onReadError(Exception exception) 
	{
		 if(exception instanceof FlatFileParseException)
		 {
			 executionContext.putString("Error", ((FlatFileParseException) exception).getInput());
		 }
	}
	
	//@Bean(name="employeeData")
	public List<EmployeeDTO> convertObjectToList(List<Object[]> result)
	{
		List<EmployeeDTO> employeeData=new ArrayList<EmployeeDTO>();
		for (Object[] object : result) 
		{
			EmployeeDTO employeeDTO=EmployeeDTO.builder()
					//.employeeId((int)object[0])
					.firstName(String.valueOf(object[1]))
					.lastName(String.valueOf(object[2]))
					.email(String.valueOf(object[3]))
					.age((int)object[4])
					.build();
			employeeData.add(employeeDTO);
		}
		return employeeData;
	}
	
}
