package com.jio.ngo.threat.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jio.ngo.threat.dto.EmployeeDTO;

@Component
public class EmployeeReader implements ItemReader<EmployeeDTO>
{
	@Autowired
	ExecutionContext executionContext;
	
	private int nextEmployeeIndex;
    private List<EmployeeDTO> employeeData;
    
    public EmployeeReader()
    {
    	nextEmployeeIndex=0;
    }

	@Override
	public EmployeeDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException 
	{
		if (employeeDataIsNotInitialized()) {
			employeeData = convertObjectToList();
        }
		
		EmployeeDTO nextEmployee = null;
        if (nextEmployeeIndex < employeeData.size()) 
        {
        	nextEmployee = employeeData.get(nextEmployeeIndex);
        	nextEmployeeIndex++;
        }
        return nextEmployee;
	}
	
	private boolean employeeDataIsNotInitialized() {
        return this.employeeData == null;
    }
	
	@SuppressWarnings("unchecked")
	public List<EmployeeDTO> convertObjectToList()
	{
		List<Object[]> result=(List<Object[]>) executionContext.get("JsonData");
		employeeData=new ArrayList<EmployeeDTO>();
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
