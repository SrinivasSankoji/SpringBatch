package com.example.demo.batch.reader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.example.demo.batch.dto.EmployeeDTO;

@Component
public class EmployeeJsonReader implements ItemReader<EmployeeDTO>
{
	private int nextEmployeeIndex;
    private List<EmployeeDTO> employeeData;
    
    EmployeeJsonReader() {
        initialize();
    }

	private void initialize() 
	{
		EmployeeDTO srinivas = EmployeeDTO.builder().employeeId(101)
								.firstName("Srinivas").lastName("Sankoji").age(32)
								.email("Srinivaschary.chary@Gmail.com").build();
		EmployeeDTO nandini = EmployeeDTO.builder().employeeId(102)
								.firstName("Nandini").lastName("Sankoji").age(26)
								.email("SiriSrinivas@Gmail.com").build();
		EmployeeDTO bhaumik = EmployeeDTO.builder().employeeId(103)
								.firstName("Bhaumik").lastName("Sankoji").age(02)
								.email("Bhaumikchary.chary@Gmail.com").build();
		employeeData = Collections.unmodifiableList(Arrays.asList(srinivas, nandini, bhaumik));
		nextEmployeeIndex = 0;
	}

	@Override
	public EmployeeDTO read() throws Exception, UnexpectedInputException, 
									 ParseException, NonTransientResourceException 
	{
		EmployeeDTO nextEmployee = null;
		 
        if (nextEmployeeIndex < employeeData.size()) {
        	nextEmployee = employeeData.get(nextEmployeeIndex);
        	nextEmployeeIndex++;
        }
 
        return nextEmployee;
	}

}
