package com.example.demo.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.batch.dto.OneLakhDTO;
import com.example.demo.batch.model.OneLakh;

@Component
public class OneLakhProcessor implements ItemProcessor<OneLakhDTO, OneLakh>
{
	@Override
	public OneLakh process(OneLakhDTO oneLakhDTO) throws Exception 
	{
		return OneLakh.builder()
			   .columnOne(oneLakhDTO.getColumnOne())
			   .columnTwo(oneLakhDTO.getColumnTwo())
			   .columnThree(oneLakhDTO.getColumnThree())
			   .build();
	}
}
