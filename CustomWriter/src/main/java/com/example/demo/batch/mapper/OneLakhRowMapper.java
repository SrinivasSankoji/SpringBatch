package com.example.demo.batch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.demo.batch.dto.OneLakhDTO;

public class OneLakhRowMapper implements FieldSetMapper<OneLakhDTO>
{

	@Override
	public OneLakhDTO mapFieldSet(FieldSet fieldSet) throws BindException 
	{
		return OneLakhDTO.builder()
				.columnOne(fieldSet.readInt("columnOne"))
				.columnTwo(fieldSet.readInt("columnTwo"))
				.columnThree(fieldSet.readInt("columnThree"))
				.build();
	}
}
