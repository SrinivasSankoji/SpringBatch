package com.example.demo.batch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.demo.batch.dto.EmployeeDTO;

public class EmployeeRowMapper implements FieldSetMapper<EmployeeDTO>
{

	@Override
	public EmployeeDTO mapFieldSet(FieldSet fieldSet) throws BindException 
	{
		return EmployeeDTO.builder()
				.employeeId(fieldSet.readString("employeeId"))
				.firstName(fieldSet.readString("firstName"))
				.lastName(fieldSet.readString("lastName"))
				.email(fieldSet.readString("email"))
				.age(fieldSet.readInt("age"))
				.build();
	}
}
